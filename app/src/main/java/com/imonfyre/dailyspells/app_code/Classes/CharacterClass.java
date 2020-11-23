package com.imonfyre.dailyspells.app_code.Classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.imonfyre.dailyspells.app_code.PlayerCharacter;
import com.imonfyre.dailyspells.app_code.Classes.Paladin.Abilities;

public class CharacterClass implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2892107768407534064L;
	private int _level, _classID, _characterID;
	private String _className;
	private List<String> abilityList = new ArrayList<String>();

	public int GetLevel()
	{
		return _level;
	}

	public void SetLevel(int level)
	{
		_level = level;
	}

	protected void SetClassName(String className)
	{
		_className = className;
	}

	public String GetClassName()
	{
		return _className;
	}

	protected void SetAbilityList(List<String> abilityList)
	{
		this.abilityList = abilityList;
	}

	public List<String> GetAbilityList()
	{
		return abilityList;
	}

	public void NewDay(PlayerCharacter stats)
	{
	}

	protected void SetClassID(int _classID)
	{
		this._classID = _classID;
	}

	public int GetClassID()
	{
		return _classID;
	}

	public String AbilityState()
	{
		return null;
	}

	public void SetCharacterID(int _characterID)
	{
		this._characterID = _characterID;
	}

	public int GetCharacterID()
	{
		return _characterID;
	}
	public int handleAbilityClick(Abilities a, Context ctx)
	{
		return -1;
	}
	public enum Classes
	{
		Barbarian, Bard, Cleric, Druid, Fighter, Monk, Paladin, Ranger, Rogue, Sorcerer, Wizard
	}
	public String AbilityState(PlayerCharacter playerCharacter)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String SaveAbilityState()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
