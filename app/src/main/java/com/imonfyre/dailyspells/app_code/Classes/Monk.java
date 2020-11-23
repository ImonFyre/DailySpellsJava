package com.imonfyre.dailyspells.app_code.Classes;

import com.imonfyre.dailyspells.app_code.PlayerCharacter;

public class Monk extends Melee {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8310517115505538199L;
	public Monk() {}
	public Monk(PlayerCharacter stats, int level) {
		this.SetLevel(level);
		this.SetClassName("Monk");
		this.SetClassID(CharacterClass.Classes.Monk.ordinal());
	}
	@Override
	public String AbilityState()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
