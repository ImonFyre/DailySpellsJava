package com.imonfyre.dailyspells.app_code.Classes;

import android.annotation.SuppressLint;
import android.content.Context;
import com.imonfyre.dailyspells.app_code.BonusSpells;
import com.imonfyre.dailyspells.app_code.PlayerCharacter;

public class Paladin extends MagicUser
{

	/**
	 * 
	 */
	// To Do: Remove Disease Implementation
	private static final long serialVersionUID = -1454378711082455311L;
	// PlayerCharacter playerCharacter;
	int DailyLayOnHands = 0, remainingLoH;
	int DailySmiteEvil = 0, remainingSE;
	int DailyTurnUndead = 0, remainingTU;
	int WeeklyRemoveDisease = 0, remainingRD;

	public Paladin()
	{
	}

	public Paladin(PlayerCharacter stats, int level)
	{
		SetLevel(level);
		SetClassName("Paladin");
		SetClassID(CharacterClass.Classes.Paladin.ordinal());
		// playerCharacter = stats;
		CalculateDailySpells(stats);
		/*
		 * List<String> abilities = new ArrayList<String>();
		 * abilities.add("Smite Evil"); if (GetLevel() > 1)
		 * abilities.add("Lay on Hands"); if (GetLevel() > 3) {
		 * abilities.add("Turn Undead"); abilities.add("1st Level Spells"); } if
		 * (GetLevel() > 7) abilities.add("2nd Level Spells"); if (GetLevel() >
		 * 10) abilities.add("3rd Level Spells"); if (GetLevel() > 14)
		 * abilities.add("4th Level Spells");
		 */
	}

	public Paladin LoadPaladin(PlayerCharacter stats, int Level, int ClassID, String AbilityState)
	{
		SetCharacterID(stats.GetCharacterID());
		SetLevel(Level);
		SetClassID(ClassID);
		SetClassName(Classes.values()[ClassID].toString());
		CalculateDailySpells(stats);
		LoadAbilityState(AbilityState);
		return this;
	}

	public boolean SavePaladin()
	{
		return true;
	}

	public void CalculateDailySpells(PlayerCharacter stats)
	{
		CalculateDailySmiteEvil();

		if (GetLevel() > 1)
			DailyLayOnHands = remainingLoH = PlayerCharacter.ComputeBonus(stats.GetCharisma()) * this.GetLevel();

		if (GetLevel() > 3)
		{
			DailyTurnUndead = remainingTU = PlayerCharacter.ComputeBonus(stats.GetCharisma()) + 3;

			// if (GetLevel() > 3)
			FillSpellSlots(stats);
		}
	}

	@Override
	public void NewDay(PlayerCharacter stats)
	{
		CalculateDailySpells(stats);
	}

	public void CalculateDailySmiteEvil()
	{
		switch (GetLevel())
		{
			case 1:
			case 2:
			case 3:
			case 4:
				DailySmiteEvil = remainingSE = 1;
				break;
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				DailySmiteEvil = remainingSE = 2;
				break;
			case 10:
			case 11:
			case 12:
			case 13:
			case 14:
				DailySmiteEvil = remainingSE = 3;
				break;
			case 15:
			case 16:
			case 17:
			case 18:
			case 19:
				DailySmiteEvil = remainingSE = 4;
				break;
			case 20:
				DailySmiteEvil = remainingSE = 5;
				break;
		}
	}

	public void CalculateWeeklyRemoveDisease()
	{

		switch (GetLevel())
		{
			case 6:
			case 7:
			case 8:
				WeeklyRemoveDisease = remainingRD = 1;
				break;
			case 9:
				DailySmiteEvil = remainingSE = 2;
				break;
			case 10:
			case 11:
			case 12:
			case 13:
			case 14:
				DailySmiteEvil = remainingSE = 3;
				break;
			case 15:
			case 16:
			case 17:
			case 18:
			case 19:
				DailySmiteEvil = remainingSE = 4;
				break;
			case 20:
				DailySmiteEvil = remainingSE = 5;
				break;
			default:
				break;
		}
	}

	public void FillSpellSlots(PlayerCharacter stats)
	{
		BonusSpells bs = ComputeExtraSpells(stats, spellType.Wisdom);
		CalculateL1Spells(stats, bs);
		CalculateL2Spells(stats, bs);
		CalculateL3Spells(stats, bs);
		CalculateL4Spells(stats, bs);

		this.SetRemainingL1(GetL1Spells());
		this.SetRemainingL2(GetL2Spells());
		this.SetRemainingL3(GetL3Spells());
		this.SetRemainingL4(GetL4Spells());
	}

	private void CalculateL1Spells(PlayerCharacter stats, BonusSpells bs)
	{
		if (stats.GetWisdom() > 10)
		{
			switch (this.GetLevel())
			{

				case 4:
				case 5:
					SetL1Spells(0 + bs.GetFirstLevel());
					break;
				case 6:
				case 7:
				case 8:
				case 9:
				case 10:
				case 11:
				case 12:
				case 13:
					SetL1Spells(1 + bs.GetFirstLevel());
					break;
				case 14:
				case 15:
				case 16:
				case 17:
					SetL1Spells(2 + bs.GetFirstLevel());
					break;
				case 18:
				case 19:
				case 20:
					SetL1Spells(3 + bs.GetFirstLevel());
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

	@SuppressLint("DefaultLocale")
	public String SaveAbilityState()
	{
		// Smite Evil : LoH : Turn Undead : spell level 1 : spell level 2 :
		// spell level 3 : spell level 4
		String abilities = String.format("%d:%d:%d:%d:%d:%d:%d", remainingSE, remainingLoH, remainingTU, this.GetRemainingL1(), this.GetRemainingL2(), this.GetRemainingL3(), this.GetRemainingL4());
		return abilities;
	}

	public void LoadAbilityState(String abilityState)
	{
		if (abilityState != null && abilityState.length() > 0)
		{
			String[] abilities = abilityState.split("\\:");
			if (abilities.length == 7)
			{
				remainingSE = abilities[0] == "" ? 0 : Integer.parseInt(abilities[0]);
				remainingLoH = abilities[1] == "" ? 0 : Integer.parseInt(abilities[1]);
				remainingTU = abilities[2] == "" ? 0 : Integer.parseInt(abilities[2]);
				this.SetRemainingL1(abilities[3] == "" ? 0 : Integer.parseInt(abilities[3]));
				this.SetRemainingL2(abilities[4] == "" ? 0 : Integer.parseInt(abilities[4]));
				this.SetRemainingL3(abilities[5] == "" ? 0 : Integer.parseInt(abilities[5]));
				this.SetRemainingL4(abilities[6] == "" ? 0 : Integer.parseInt(abilities[6]));
			}
		}
	}

	@Override
	public String AbilityState(PlayerCharacter playerCharacter)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%s:%s:%d:%d", "SE", "Smite Evil", this.remainingSE, this.DailySmiteEvil));
		if (this.GetLevel() > 1 && PlayerCharacter.ComputeBonus(playerCharacter.GetCharisma()) > 0)
			sb.append(String.format("||%s:%s:%d:%d", "LoH", "Lay on Hands", this.remainingLoH, this.DailyLayOnHands));

		if (GetLevel() > 3)
		{
			sb.append(String.format("||%s:%s:%d:%d", "TU", "Turn Undead", this.remainingTU, this.DailyTurnUndead));
		}
		if (PlayerCharacter.ComputeBonus(playerCharacter.GetWisdom()) > 0)
		{
			if (GetLevel() > 3 && playerCharacter.GetWisdom() > 10)
			{
				sb.append(String.format("||%s:%s:%d:%d", "L1", "1st Level Spells", this.GetRemainingL1(), GetL1Spells()));
			}
			if (GetLevel() > 7 && playerCharacter.GetWisdom() > 11)
				sb.append(String.format("||%s:%s:%d:%d", "L2", "2nd Level Spells", this.GetRemainingL2(), GetL2Spells()));

			if (GetLevel() > 10 && playerCharacter.GetWisdom() > 12)
				sb.append(String.format("||%s:%s:%d:%d", "L3", "3rd Level Spells", this.GetRemainingL3(), GetL3Spells()));

			if (GetLevel() > 13 && playerCharacter.GetWisdom() > 13)
				sb.append(String.format("||%s:%s:%d:%d", "L4", "4th Level Spells", this.GetRemainingL4(), GetL4Spells()));
		}
		return sb.toString();
	}

	public int handleAbilityClick(Abilities a, Context ctx)
	{
		switch (a)
		{
			case SE:
				// Toast.makeText(ctx, "Smite Evil", Toast.LENGTH_SHORT).show();
				// this.remainingSE--;
				return remainingSE > 0 ? --remainingSE : 0;
			case TU:
				// Toast.makeText(ctx, "Turn Undead",
				// Toast.LENGTH_SHORT).show();
				return remainingTU > 0 ? --this.remainingTU : 0;
			case LoH:
				// Toast.makeText(ctx, "Lay on Hands",
				// Toast.LENGTH_SHORT).show();
				return this.remainingLoH > 0 ? --this.remainingLoH : 0;
			case L1:
				// Toast.makeText(ctx, "Spells Level 1",
				// Toast.LENGTH_SHORT).show();
				if (this.GetRemainingL1() > 0)
				{
					this.SetRemainingL1(this.GetRemainingL1() - 1);
					return this.GetRemainingL1();
				}
				else
					return this.GetRemainingL1();
			case L2:
				// Toast.makeText(ctx, "Spells Level 2",
				// Toast.LENGTH_SHORT).show();
				if (this.GetRemainingL2() > 0)
				{
					this.SetRemainingL1(this.GetRemainingL2() - 1);
					return this.GetRemainingL2();
				}
				else
					return this.GetRemainingL2();
			case L3:
				// Toast.makeText(ctx, "Spells Level 3",
				// Toast.LENGTH_SHORT).show();
				if (this.GetRemainingL2() > 0)
				{
					this.SetRemainingL3(this.GetRemainingL3() - 1);
					return this.GetRemainingL3();
				}
				else
					return this.GetRemainingL3();
			case L4:
				// Toast.makeText(ctx, "Spells Level 4",
				// Toast.LENGTH_SHORT).show();
				if (this.GetRemainingL4() > 0)
				{
					this.SetRemainingL1(this.GetRemainingL4() - 1);
					return this.GetRemainingL4();
				}
				else
					return this.GetRemainingL4();
			default:
				return -1;
		}
	}

	public enum Abilities
	{
		SE, TU, LoH, L1, L2, L3, L4
	}

}