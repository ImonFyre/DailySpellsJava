package com.imonfyre.dailyspells.app_code.Classes;

import com.imonfyre.dailyspells.app_code.PlayerCharacter;

public class Cleric extends MagicUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 36595087646505684L;

	public Cleric() {
	}

	public Cleric(PlayerCharacter stats, int level) {
		this.SetLevel(level);
		this.SetClassName("Cleric");
		this.SetClassID(CharacterClass.Classes.Cleric.ordinal());
	}
}