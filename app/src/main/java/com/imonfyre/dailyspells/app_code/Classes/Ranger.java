package com.imonfyre.dailyspells.app_code.Classes;

import com.imonfyre.dailyspells.app_code.PlayerCharacter;

public class Ranger extends MagicUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6182051993201086729L;

	public Ranger() {
	}

	public Ranger(PlayerCharacter stats, int level) {
		this.SetLevel(level);
		this.SetClassName("Ranger");
		this.SetClassID(CharacterClass.Classes.Ranger.ordinal());
	}
}