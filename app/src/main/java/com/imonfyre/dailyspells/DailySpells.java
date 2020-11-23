package com.imonfyre.dailyspells;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.imonfyre.dailyspells.app_code.OnCharacterEditListener;
import com.imonfyre.dailyspells.app_code.DBAdaptor;
import com.imonfyre.dailyspells.app_code.PlayerCharacter;
import com.imonfyre.dailyspells.app_code.Serializer;
import com.imonfyre.dailyspells.app_code.Classes.*;
import com.imonfyre.dailyspells.app_code.Dialog.StatsEntryDialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

//import android.widget.Toast;

public class DailySpells extends Activity
{
	/** Called when the activity is first created. */
	static final int DIALOG_NEW_CHARACTER_ID = R.layout.new_character_dialog;
	//private static final int MY_DIALOG = 0;
	View view;
	ListView lv;
	SimpleAdapter sa;
	List<HashMap<String, String>> fillMaps;
	Context DailySpellsContext = DailySpells.this;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		getActionBar().setHomeButtonEnabled(false);
		lv = (ListView) this.findViewById(R.id.CharacterList);
		// List Mapping
		String[] from = new String[] { "Name", "Race", "ClassLevel", "hiddenCharID" };
		int[] to = new int[] { R.id.Name, R.id.Race, R.id.ClassLevel, R.id.hiddenCharID };
		fillMaps = this.RefreshCharacterAdapterList();
		sa = new SimpleAdapter(this, fillMaps, R.layout.characterlist, from, to);
		lv.setAdapter(sa);
		// this.addContentView(view, view.getLayoutParams());
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
			{

				DBAdaptor db = new DBAdaptor(DailySpellsContext).open();
				TextView tvCharID = (TextView) view.findViewById(R.id.hiddenCharID);
				int charID = Integer.parseInt((String) tvCharID.getText());
				// Toast.makeText(DailySpellsContext, "Character ID: " + charID,
				// Toast.LENGTH_SHORT).show();
				PlayerCharacter selected = db.LoadCharacterFromDB(charID);
				db.close();
				//Toast.makeText(DailySpellsContext, selected.GetName() + " " + selected.GetCharacterLevel(), Toast.LENGTH_SHORT).show();
				Intent showCharacter = new Intent(DailySpellsContext, PC_display.class);
				showCharacter.putExtra("characterObject", Serializer.SerializePC(selected));
				startActivity(showCharacter);
				finish();
			}
		});
		lv.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long id)
			{
				DBAdaptor db = new DBAdaptor(DailySpellsContext).open();
				TextView tvCharID = (TextView) view.findViewById(R.id.hiddenCharID);
				int charID = Integer.parseInt((String) tvCharID.getText());
				// Toast.makeText(DailySpellsContext, "Character ID: " + charID,
				// Toast.LENGTH_SHORT).show();
				PlayerCharacter selected = db.LoadCharacterFromDB(charID);
				db.close();
				StatsEntryDialog sed = new StatsEntryDialog(DailySpellsContext, new OnCharacterEditListener(), selected);
				sed.show();
				return true;
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{

		switch (item.getItemId())
		{
			case R.id.menuNewCharacterButton:
				StatsEntryDialog sed = new StatsEntryDialog(this, new OnCharacterCreateListener());
				sed.show();
				break;
		}

		return true;
	}

	private class OnCharacterCreateListener implements StatsEntryDialog.OnStatsEntryDialog
	{

		@Override
		public void CharFields(String name, int Str, int Dex, int Con, int Int, int Wis, int Cha, CharacterClass.Classes PCclass, PlayerCharacter.Races Race, int Level)
		{
			//Toast.makeText(DailySpells.this, name, Toast.LENGTH_LONG).show();
			PlayerCharacter newPC = new PlayerCharacter(name, Str, Dex, Con, Int, Wis, Cha, Race);
			CharacterClass cc;
			switch (PCclass)
			{
				case Barbarian:
					cc = new Barbarian(newPC, Level);
					break;
				case Bard:
					cc = new Bard(newPC, Level);
					break;
				case Cleric:
					cc = new Cleric(newPC, Level);
					break;
				case Druid:
					cc = new Druid(newPC, Level);
					break;
				case Fighter:
					cc = new Fighter(newPC, Level);
					break;
				case Monk:
					cc = new Bard(newPC, Level);
					break;
				case Paladin:
					cc = new Paladin(newPC, Level);
					break;
				case Ranger:
					cc = new Ranger(newPC, Level);
					break;
				case Rogue:
					cc = new Rogue(newPC, Level);
					break;
				case Sorcerer:
					cc = new Sorcerer(newPC, Level);
					break;
				case Wizard:
					cc = new Wizard(newPC, Level);
					break;
				default:
					cc = new CharacterClass();
			}
			newPC.AddClass(cc);
			PlayerCharacter.SaveNewPC(DailySpells.this, newPC);
			Intent showCharacter = new Intent(DailySpellsContext, PC_display.class);
			showCharacter.putExtra("characterObject", Serializer.SerializePC(newPC));
			startActivity(showCharacter);
		}

		@Override
		public void setPlayerCharacter(PlayerCharacter pc)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setContext(Context ctx)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setEdit(boolean edit)
		{
			// TODO Auto-generated method stub
			
		}
	}

	@Override
	protected Dialog onCreateDialog(int id)
	{
		Dialog dialog = new Dialog(this);

		switch (id)
		{
			case DIALOG_NEW_CHARACTER_ID:

				break;
			default:
				dialog = null;
		}
		return dialog;
	}

	private List<HashMap<String, String>> RefreshCharacterAdapterList()
	{
		List<HashMap<String, String>> filler = new ArrayList<HashMap<String, String>>();
		DBAdaptor da = new DBAdaptor(this).open();
		//da.open();
		List<PlayerCharacter> lPC = da.getCharacterList();
		da.close();
		// add list of characters to main screen
		for (PlayerCharacter pc : lPC)
		{
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("Name", pc.GetName() + " ");

			map.put("Race", pc.GetRace().toString() + " ");
			StringBuilder sb = new StringBuilder();
			for (CharacterClass cc : pc.GetClasses())
			{
				{
					sb.append(String.format("%s(%d)/", cc.GetClassName(), cc.GetLevel()));
				}
			}
			if (sb.length() > 0)
				sb.deleteCharAt(sb.length() - 1);
			map.put("ClassLevel", sb.toString());
			map.put("hiddenCharID", pc.GetCharacterID() + "");
			filler.add(map);
		}

		return filler;
	}

}