package com.imonfyre.dailyspells.app_code;

import java.util.ArrayList;
import java.util.List;

import com.imonfyre.dailyspells.app_code.Classes.*;
import com.imonfyre.dailyspells.app_code.Classes.CharacterClass.Classes;
import com.imonfyre.dailyspells.app_code.PlayerCharacter.Races;

import android.content.ContentValues;
import android.content.Context; //import android.database.Cursor;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdaptor
{
	private static final String CHARACTER_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS Character (_id INTEGER PRIMARY KEY AUTOINCREMENT, CharacterName text not null, Race INTEGER NOT NULL);";
	private static final String STATS_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS Stats(_id INTEGER PRIMARY KEY, Str INTEGER, Dex INTEGER, Con INTEGER, Int INTEGER, Wis INTEGER, Cha INTEGER);";
	private static final String CLASS_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS Class(_id INTEGER PRIMARY KEY, ClassID INTEGER, Level INTEGER, AbilityState varchar(250));";
	// private static final String CLASSNAME_TABLE_CREATE =
	// "CREATE TABLE ClassName(ClassID INTEGER PRIMARY KEY AUTOIMCREMENT, Name varchar(250));";
	// private static final String TAG = "DBAdapter";

	private static final int DATABASE_VERSION = 3;
	private static final String DATABASE_NAME = "DailySpells";
	private static final String CHARACTER_TABLE = "Character";
	private static final String STATS_TABLE = "Stats";
	private static final String CLASS_TABLE = "Class";
	// private static final String CLASSNAME_TABLE = "ClassName";

	//private final Context context;

	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public DBAdaptor(Context ctx)
	{
		//this.context = ctx;
		DBHelper = new DatabaseHelper(ctx);
	}

	/**
	 * Opens a new connection to the database
	 * 
	 * @return A database adaptor object
	 * @throws SQLException
	 */
	public DBAdaptor open() throws SQLException
	{
		db = DBHelper.getWritableDatabase();
		return this;
	}

	/**
	 * Close the current database connection
	 */
	public void close()
	{
		DBHelper.close();
	}

	public boolean NewCharacter(PlayerCharacter newCharacter)
	{
		try
		{
			ContentValues initialValues = new ContentValues();
			// insert character name
			initialValues.put("CharacterName", newCharacter.GetName());
			initialValues.put("Race", newCharacter.GetRace().ordinal());
			long id = db.insert(CHARACTER_TABLE, null, initialValues);
			if (id < 0)
				return false;
			newCharacter.SetCharacterID((int) id);
			initialValues.clear();
			// insert character stats
			initialValues.put("_id", id);
			initialValues.put("Str", newCharacter.GetStrength());
			initialValues.put("Dex", newCharacter.GetDexterity());
			initialValues.put("Con", newCharacter.GetConstitution());
			initialValues.put("Int", newCharacter.GetIntelligence());
			initialValues.put("Wis", newCharacter.GetWisdom());
			initialValues.put("Cha", newCharacter.GetCharisma());
			db.insert(STATS_TABLE, null, initialValues);
			// insert initial class
			for (CharacterClass cc : newCharacter.GetClasses())
			{
				initialValues.clear();
				initialValues.put("_id", id);
				initialValues.put("ClassId", cc.GetClassID());
				initialValues.put("level", cc.GetLevel());
				initialValues.put("AbilityState", cc.SaveAbilityState());
				db.insert(CLASS_TABLE, null, initialValues);
			}
			return true;
		}
		catch (SQLException e)
		{
			return false;
		}
	}

	public boolean UpdateCharacter(PlayerCharacter stats)
	{
		try
		{
			ContentValues cv = new ContentValues();
			cv.put("str", stats.GetStrength());
			cv.put("dex", stats.GetDexterity());
			cv.put("con", stats.GetConstitution());
			cv.put("int", stats.GetIntelligence());
			cv.put("wis", stats.GetWisdom());
			cv.put("cha", stats.GetCharisma());
			db.update("Character", cv, "_id = ?", new String[] { Integer.toString(stats.GetCharacterID()) });
			for (CharacterClass cc : stats.GetClasses())
			{
				cv.clear();
				cv.put("level", cc.GetLevel());
				cv.put("abilityState", cc.AbilityState());
				db.update("Class", cv, "_id = ? and classid = ?", new String[] { Integer.toString(stats.GetCharacterID()), Integer.toString(cc.GetClassID()) });
			}

			return true;
		}
		catch (SQLException e)
		{
			return false;
		}
	}

	public PlayerCharacter LoadCharacterFromDB(int CharID)
	{

		String GetCharacterSQL = "select * from character c inner join stats s on c._id = s._id where c._id = ?";
		String GetClassesSQL = "select * from class where  _id = ?";
		PlayerCharacter pc = new PlayerCharacter();

		try
		{
			Cursor cur = db.rawQuery(GetCharacterSQL, new String[] { Integer.toString(CharID) });

			cur.moveToFirst();

			pc.SetCharacterID(cur.getInt(cur.getColumnIndex("_id")));
			pc.SetName(cur.getString(cur.getColumnIndex("CharacterName")));
			pc.SetStrength(cur.getInt(cur.getColumnIndex("Str")));
			pc.SetDexterity(cur.getInt(cur.getColumnIndex("Dex")));
			pc.SetConstitution(cur.getInt(cur.getColumnIndex("con")));
			pc.SetIntelligence(cur.getInt(cur.getColumnIndex("Int")));
			pc.SetWisdom(cur.getInt(cur.getColumnIndex("Wis")));
			pc.SetCharisma(cur.getInt(cur.getColumnIndex("Cha")));
			pc.SetRace(Races.values()[cur.getInt(cur.getColumnIndex("Race"))]);

			// get characters classes.
			cur = db.rawQuery(GetClassesSQL, new String[] { Integer.toString(CharID) });
			cur.moveToFirst();
			while (!cur.isAfterLast())
			{
				switch (Classes.values()[cur.getInt((cur.getColumnIndex("ClassID")))])
				{
					case Paladin:
						//Paladin p = new Paladin().LoadPaladin(stats, Level, ClassID, AbilityState)
						pc.AddClass(new Paladin().LoadPaladin(pc, cur.getInt(cur.getColumnIndex("Level")), cur.getInt(cur.getColumnIndex("ClassID")), cur.getString(cur.getColumnIndex("AbilityState"))));
						break;
					case Barbarian:
					case Bard:
					case Cleric:
					case Druid:
					case Fighter:
					case Monk:
					case Ranger:
					case Rogue:
					case Sorcerer:
					case Wizard:
						break;
				}
				cur.moveToNext();
			}
			cur.close();
			return pc;
		}
		catch (SQLException ex)
		{
			Log.e("LoadCharacterFromDB", "SQLException", ex);
			return null;
		}
	}

	public List<PlayerCharacter> getCharacterList()
	{
		List<PlayerCharacter> characters = new ArrayList<PlayerCharacter>();
		Cursor cur = db.query(CHARACTER_TABLE, new String[] { "_id" }, null, null, null, null, "_id");
		if (cur.getCount() > 0)
		{
			cur.moveToFirst();
			while (!cur.isAfterLast())
			{
				characters.add(LoadCharacterFromDB(cur.getInt(cur.getColumnIndex("_id"))));
				cur.moveToNext();
			}
		}
		return characters;
	}

	private static class DatabaseHelper extends SQLiteOpenHelper
	{

		DatabaseHelper(Context context)
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db)
		{
			db.beginTransaction();
			try
			{
				db.execSQL(CHARACTER_TABLE_CREATE);
				db.execSQL(STATS_TABLE_CREATE);
				db.execSQL(CLASS_TABLE_CREATE);

				db.setTransactionSuccessful();
			}
			catch (Exception e)
			{

				Log.e("OnCreate", e.toString());
			}
			finally
			{
				db.endTransaction();
			}
			// db.execSQL(ALBUM_TABLE_CREATE);
			// db.execSQL(GENRE_TABLE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			db.beginTransaction();
			try
			{
				db.execSQL(CHARACTER_TABLE_CREATE);
				db.execSQL(STATS_TABLE_CREATE);
				db.execSQL(CLASS_TABLE_CREATE);

				db.setTransactionSuccessful();
			}
			catch (Exception e)
			{

				Log.e("OnCreate", e.toString());
			}
			finally
			{
				db.endTransaction();
			}
			// db.execSQL(ALBUM_TABLE_CREATE);
			// db.execSQL(GENRE_TABLE_CREATE);
		}

	}

}