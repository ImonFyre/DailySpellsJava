package com.imonfyre.dailyspells.app_code.Classes;

import com.imonfyre.dailyspells.app_code.PlayerCharacter;

public class Barbarian extends Melee {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2878215901029883257L;
	public Barbarian() {}
	public Barbarian(PlayerCharacter stats, int level) {
		this.SetLevel(level);
		this.SetClassName("Barbarian");
		this.SetClassID(CharacterClass.Classes.Barbarian.ordinal());
	}
	@Override
	public String AbilityState()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
