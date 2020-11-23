package com.imonfyre.dailyspells.app_code;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.imonfyre.dailyspells.app_code.Classes.CharacterClass;

import android.content.Context;

public class PlayerCharacter implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6884987936225024170L;
	private String _name;
	private int _strength, _dexterity, _constitution, _wisdom, _intelligence, _charisma, _characterID;
	private List<CharacterClass> classes = new ArrayList<CharacterClass>();
	private Races _race;

	public PlayerCharacter()
	{
	}

	public PlayerCharacter(String name, int Str, int Dex, int Con, int Wis, int Int, int Cha, Races Race)
	{
		_name = name;
		_strength = Str;
		_dexterity = Dex;
		_constitution = Con;
		_wisdom = Wis;
		_intelligence = Int;
		_charisma = Cha;
		_race = Race;
		addRacialStats(_race);
	}

	public String GetName()
	{
		return _name;
	}

	public void SetName(String Name)
	{
		_name = Name;
	}

	public int GetStrength()
	{
		return _strength;
	}

	public void SetStrength(int str)
	{
		_strength = str;
	}

	public int GetDexterity()
	{
		return _dexterity;
	}

	public void SetDexterity(int dex)
	{
		_dexterity = dex;
	}

	public int GetWisdom()
	{
		return _wisdom;
	}

	public void SetWisdom(int wis)
	{
		_wisdom = wis;
	}

	public int GetIntelligence()
	{
		return _intelligence;
	}

	public void SetIntelligence(int Int)
	{
		_intelligence = Int;
	}

	public int GetCharisma()
	{
		return _charisma;
	}

	public void SetCharisma(int cha)
	{
		_charisma = cha;
	}

	public void SetClasses(ArrayList<CharacterClass> classes)
	{
		this.classes = classes;
	}

	public List<CharacterClass> GetClasses()
	{
		return classes;
	}

	public void AddClass(CharacterClass cc)
	{
		this.classes.add(cc);
	}

	public void SetCharacterID(int _characterID)
	{
		this._characterID = _characterID;
	}

	public int GetCharacterID()
	{
		return _characterID;
	}

	public void SetRace(Races Race)
	{
		this._race = Race;
	}

	public Races GetRace()
	{
		return _race;
	}

	public static boolean SaveNewPC(Context context, PlayerCharacter NewCharacter)
	{
		DBAdaptor db = new DBAdaptor(context);
		db.open();
		boolean result = db.NewCharacter(NewCharacter);
		db.close();
		return result;
	}

	public void UpdateClass(CharacterClass update)
	{
		CharacterClass oldCC = new CharacterClass();
		for (CharacterClass icc : classes)
		{
			if (update.GetClassName() == icc.GetClassName())
			{
				oldCC = icc;
			}
		}
		oldCC.GetAbilityList();
	}

	public int GetCharacterLevel()
	{
		int level = 0;
		for (CharacterClass icc : classes)
		{
			level += icc.GetLevel();
		}
		return level;
	}

	public static int ComputeBonus(int Score)
	{
		if (Score < 3)
			return -4;
		return Score >= 10 ? (int) Math.floor((double)(Score - 10) / 2) : (int) Math.ceil((double)(Score - 10) / 2);
	}

	public int GetConstitution()
	{
		return _constitution;
	}

	public void SetConstitution(int _constitution)
	{
		this._constitution = _constitution;
	}

	private void addRacialStats(Races race)
	{
		switch (race)
		{
			case Dwarf:
				_constitution += 2;
				_charisma -= 2;
				break;
			case Elf:
				_dexterity += 2;
				_constitution -= 2;
				break;
			case Gnome:
				_constitution += 2;
				_strength -= 2;
				break;
			case Half_Orc:
				_strength += 2;
				_intelligence -= 2;
				_charisma -= 2;
				break;
			case Halfing:
				_dexterity += 2;
				_strength -= 2;
				break;
			case Human:
			case Half_Elf:
			default:
				break;
		}
	}

	public enum SkillType
	{
		Charisma, Constitution, Dexterity, Intelligence, Strength, Wisdom
	}

	public enum Races
	{
		Dwarf, Elf, Gnome, Half_Elf, Half_Orc, Halfing, Human
	}
}
