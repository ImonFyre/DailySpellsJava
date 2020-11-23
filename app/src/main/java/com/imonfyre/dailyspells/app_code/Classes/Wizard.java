package com.imonfyre.dailyspells.app_code.Classes;

import com.imonfyre.dailyspells.app_code.PlayerCharacter;

public class Wizard extends MagicUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 342650506672995431L;

	public Wizard() {
	}

	public Wizard(PlayerCharacter stats, int level) {
		this.SetLevel(level);
		this.SetClassName("Wizard");
		this.SetClassID(CharacterClass.Classes.Wizard.ordinal());
	}
}