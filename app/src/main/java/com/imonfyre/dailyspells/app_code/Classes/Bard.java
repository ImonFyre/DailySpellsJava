package com.imonfyre.dailyspells.app_code.Classes;

import java.util.ArrayList;
import com.imonfyre.dailyspells.app_code.BonusSpells;
import com.imonfyre.dailyspells.app_code.PlayerCharacter;

public class Bard extends MagicUser
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7364985696920322716L;
	PlayerCharacter playerCharacter;
	public Bard()
	{
	}

	public Bard(PlayerCharacter stats, int level)
	{
		this.SetLevel(level);
		this.SetClassName("Bard");
		this.SetClassID(CharacterClass.Classes.Bard.ordinal());
		playerCharacter = stats;
		CalculateDailySpells(playerCharacter);
		ArrayList<String> abilities = new ArrayList<String>();
		
	}
	public void CalculateDailySpells(PlayerCharacter stats)
	{
		FillSpellSlots(stats);
	}
	public void FillSpellSlots(PlayerCharacter stats)
	{
		BonusSpells bs = ComputeExtraSpells(stats, spellType.Charisma);
		//CalculateL0Spells(stats, bs);
		CalculateL1Spells(stats, bs);
		CalculateL2Spells(stats, bs);
		CalculateL3Spells(stats, bs);
		CalculateL4Spells(stats, bs);
		//CalculateL5Spells(stats, bs);
		//CalculateL6Spells(stats, bs);
		this.SetRemainingL1(GetL1Spells());
		this.SetRemainingL2(GetL2Spells());
		this.SetRemainingL3(GetL3Spells());
		this.SetRemainingL4(GetL4Spells());
	}

	private void CalculateL1Spells(PlayerCharacter stats, BonusSpells bs)
	{
		if (stats.GetCharisma() > 10)
		{
			switch (this.GetLevel())
			{
				case 2:
					SetL1Spells(0 + bs.GetFirstLevel());
				case 3:
					SetL1Spells(1 + bs.GetFirstLevel());
				case 4:
					SetL1Spells(2 + bs.GetFirstLevel());
				case 5:
				case 6:
				case 7:
				case 8:
				case 9:
				case 10:
				case 11:
				case 12:
				case 13:
				case 14:
					SetL1Spells(3 + bs.GetFirstLevel());
					break;
				case 15:
				case 16:
				case 17:
				case 18:
				case 19:
				case 20:
					SetL1Spells(4 + bs.GetFirstLevel());
					break;
				default:
					break;
			}
			this.SetRemainingL1(GetL1Spells());
		}

	}

	private void CalculateL2Spells(PlayerCharacter stats, BonusSpells bs)
	{
		if (stats.GetWisdom() > 11)
		{
			switch (this.GetLevel())
			{

				case 8:
				case 9:
					SetL2Spells(0 + bs.GetSecondLevel());
					break;
				case 10:
				case 11:
				case 12:
				case 13:
				case 14:
				case 15:
					SetL2Spells(1 + bs.GetSecondLevel());
					break;
				case 16:
				case 17:
				case 18:
					SetL2Spells(2 + bs.GetSecondLevel());
					break;
				case 19:
				case 20:
					SetL2Spells(3 + bs.GetSecondLevel());
					break;
				default:
					break;
			}
			this.SetRemainingL1(GetL2Spells());
		}
	}

	private void CalculateL3Spells(PlayerCharacter stats, BonusSpells bs)
	{
		if (stats.GetWisdom() > 12)
		{
			switch (this.GetLevel())
			{
				case 11:
					SetL3Spells(0 + bs.GetThirdLevel());
					break;
				case 12:
				case 13:
				case 14:
				case 15:
				case 16:
					SetL3Spells(1 + bs.GetThirdLevel());
					break;
				case 17:
				case 18:
					SetL3Spells(2 + bs.GetThirdLevel());
					break;
				case 19:
				case 20:
					SetL3Spells(3 + bs.GetThirdLevel());
					break;
				default:
					break;
			}
			this.SetRemainingL3(GetL3Spells());
		}
	}

	private void CalculateL4Spells(PlayerCharacter stats, BonusSpells bs)
	{
		if (stats.GetWisdom() > 13)
		{
			switch (this.GetLevel())
			{

				case 14:
					SetL4Spells(0 + bs.GetFourthLevel());
					break;
				case 15:
				case 16:

				case 17:
				case 18:
					SetL4Spells(1 + bs.GetFourthLevel());
					break;
				case 19:
					SetL4Spells(2 + bs.GetFourthLevel());
					break;
				case 20:
					SetL4Spells(3 + bs.GetFourthLevel());
					break;
				default:
					break;
			}
			this.SetRemainingL4(GetL4Spells());
		}
	}

}