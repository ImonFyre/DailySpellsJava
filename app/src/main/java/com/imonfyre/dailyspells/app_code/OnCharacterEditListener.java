package com.imonfyre.dailyspells.app_code;

import android.content.Context;

import com.imonfyre.dailyspells.app_code.Classes.CharacterClass;
import com.imonfyre.dailyspells.app_code.Dialog.StatsEntryDialog;

public class OnCharacterEditListener implements StatsEntryDialog.OnStatsEntryDialog
{
	
	public OnCharacterEditListener(PlayerCharacter playerCharacter)
	{
		
	}

	public OnCharacterEditListener()
	{
		
	}

	@Override
	public void CharFields(String name, int Str, int Dex, int Con, int Int, int Wis, int Cha, CharacterClass.Classes PCclass, PlayerCharacter.Races Race, int Level)
	{
		/*
		 * //Toast.makeText(DailySpells.this, name,
		 * Toast.LENGTH_LONG).show(); PlayerCharacter newPC = new
		 * PlayerCharacter(name, Str, Dex, Con, Int, Wis, Cha, Race);
		 * CharacterClass cc; switch (PCclass) { case Barbarian: cc = new
		 * Barbarian(newPC, Level); break; case Bard: cc = new Bard(newPC,
		 * Level); break; case Cleric: cc = new Cleric(newPC, Level); break;
		 * case Druid: cc = new Druid(newPC, Level); break; case Fighter: cc
		 * = new Fighter(newPC, Level); break; case Monk: cc = new
		 * Bard(newPC, Level); break; case Paladin: cc = new Paladin(newPC,
		 * Level); break; case Ranger: cc = new Ranger(newPC, Level); break;
		 * case Rogue: cc = new Rogue(newPC, Level); break; case Sorcerer:
		 * cc = new Sorcerer(newPC, Level); break; case Wizard: cc = new
		 * Wizard(newPC, Level); break; default: cc = new CharacterClass();
		 * } //newPC.AddClass(cc);
		 * //PlayerCharacter.SaveNewPC(DailySpells.this, newPC); //Intent
		 * showCharacter = new Intent(pc_, PC_display.class);
		 * //showCharacter.putExtra("characterObject",
		 * Serializer.SerializePC(newPC)); //startActivity(showCharacter);
		 */
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
