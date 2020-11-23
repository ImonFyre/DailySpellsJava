package com.imonfyre.dailyspells.app_code.Classes;

import com.imonfyre.dailyspells.app_code.PlayerCharacter;

public class Druid extends MagicUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4602517924993975309L;

	public Druid() {
	}

	public Druid(PlayerCharacter stats, int level) {
		this.SetLevel(level);
		this.SetClassName("Druid");
		this.SetClassID(CharacterClass.Classes.Druid.ordinal());
	}
}