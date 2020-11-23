package com.imonfyre.dailyspells.app_code.Classes;

import com.imonfyre.dailyspells.app_code.PlayerCharacter;

public class Rogue extends Melee {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4708025694501265240L;
	public Rogue() {}
	public Rogue(PlayerCharacter stats, int level) {
		this.SetLevel(level);
		this.SetClassName("Rogue");
		this.SetClassID(CharacterClass.Classes.Rogue.ordinal());
	}
	@Override
	public String AbilityState()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
