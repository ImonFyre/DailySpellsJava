package com.imonfyre.dailyspells.app_code.Classes;

import com.imonfyre.dailyspells.app_code.PlayerCharacter;

public class Fighter extends Melee {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2150904435272576606L;
	public Fighter() {}
	public Fighter(PlayerCharacter stats, int level) {
		this.SetLevel(level);
		this.SetClassName("Fighter");
		this.SetClassID(CharacterClass.Classes.Fighter.ordinal());
	}
	@Override
	public String AbilityState()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
