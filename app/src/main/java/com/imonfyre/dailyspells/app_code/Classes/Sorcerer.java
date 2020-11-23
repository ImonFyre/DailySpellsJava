package com.imonfyre.dailyspells.app_code.Classes;

import com.imonfyre.dailyspells.app_code.PlayerCharacter;

public class Sorcerer extends MagicUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4713383607343128554L;

	public Sorcerer() {
	}

	public Sorcerer(PlayerCharacter stats, int level) {
		this.SetLevel(level);
		this.SetClassName("Sorcerer");
		this.SetClassID(CharacterClass.Classes.Sorcerer.ordinal());
	}
}