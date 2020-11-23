package com.imonfyre.dailyspells.app_code.Classes;

import java.io.Serializable;

import com.imonfyre.dailyspells.app_code.BonusSpells;
import com.imonfyre.dailyspells.app_code.PlayerCharacter;

public class MagicUser extends CharacterClass implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1207699855817095270L;
	private int _zeroLevel = 0;
	private int _firstLevel = 0;
	private int _secondLevel = 0;
	private int _thirdLevel = 0;
	private int _fourthLevel = 0;
	private int _fifthLevel = 0;
	private int _sixthLevel = 0;
	private int _seventhLevel = 0;
	private int _eighthLevel = 0;
	private int _ninthLevel = 0;
	private int remainingL1, remainingL2, remainingL3, remainingL4, remainingL5, remainingL6, remainingL7, remainingL8, remainingL9;

	protected void SetLevel0Spells(int _zeroLevel)
	{
		this._zeroLevel = _zeroLevel;
	}

	public int GetL0SPells()
	{
		return _zeroLevel;
	}

	protected void SetL1Spells(int _firstLevel)
	{
		this._firstLevel = _firstLevel;
	}

	public int GetL1Spells()
	{
		return _firstLevel;
	}

	public int GetRemainingL1()
	{
		return remainingL1;
	}

	public void SetRemainingL1(int remainingL1)
	{
		this.remainingL1 = remainingL1;
	}

	public int GetL2Spells()
	{
		return _secondLevel;
	}

	protected void SetL2Spells(int _secondLevel)
	{
		this._secondLevel = _secondLevel;
	}

	public int GetRemainingL2()
	{
		return remainingL2;
	}

	public void SetRemainingL2(int remainingL2)
	{
		this.remainingL2 = remainingL2;
	}

	protected void SetL3Spells(int _thirdLevel)
	{
		this._thirdLevel = _thirdLevel;
	}

	public int GetRemainingL3()
	{
		return remainingL3;
	}

	public void SetRemainingL3(int remainingL3)
	{
		this.remainingL3 = remainingL3;
	}

	public int GetL3Spells()
	{
		return _thirdLevel;
	}

	protected void SetL4Spells(int _fourthLevel)
	{
		this._fourthLevel = _fourthLevel;
	}

	public int GetRemainingL4()
	{
		return remainingL4;
	}

	public void SetRemainingL4(int remainingL4)
	{
		this.remainingL4 = remainingL4;
	}

	public int GetL4Spells()
	{
		return _fourthLevel;
	}

	protected void SetL5Spells(int _fifthLevel)
	{
		this._fifthLevel = _fifthLevel;
	}

	public int GetL5Spells()
	{
		return _fifthLevel;
	}

	public int GetRemainingL5()
	{
		return remainingL5;
	}

	public void SetRemainingL5(int remainingL5)
	{
		this.remainingL5 = remainingL5;
	}

	protected void SetL6Spells(int _sixthLevel)
	{
		this._sixthLevel = _sixthLevel;
	}

	public int GetRemainingL6()
	{
		return remainingL6;
	}

	public void SetRemainingL6(int remainingL6)
	{
		this.remainingL6 = remainingL6;
	}

	public int GetL6Spells()
	{
		return _sixthLevel;
	}

	public int GetL7Spells()
	{
		return _seventhLevel;
	}

	protected void SetL7Spells(int _seventhLevel)
	{
		this._seventhLevel = _seventhLevel;
	}

	public int GetRemainingL7()
	{
		return remainingL7;
	}

	public void SetRemainingL7(int remainingL7)
	{
		this.remainingL7 = remainingL7;
	}

	protected void SeL8SPells(int _eighthLevel)
	{
		this._eighthLevel = _eighthLevel;
	}

	public int GetL8Spells()
	{
		return _eighthLevel;
	}

	public int GetRemainingL8()
	{
		return remainingL8;
	}

	public void SetRemainingL8(int remainingL8)
	{
		this.remainingL8 = remainingL8;
	}

	public int GetL9Spells()
	{
		return _ninthLevel;
	}

	public void SetL9Spells(int _ninthLevel)
	{
		this._ninthLevel = _ninthLevel;
	}

	public int GetRemainingL9()
	{
		return remainingL9;
	}

	public void SetRemainingL9(int remainingL9)
	{
		this.remainingL9 = remainingL9;
	}

	/**
	 * 
	 * 
	 * @param stats
	 * @param SpellLevel
	 * @param type
	 *            The type of Stat to base the bonus spells on.
	 * @return
	 */
	protected BonusSpells ComputeExtraSpells(PlayerCharacter stats, spellType Type)
	{
		BonusSpells extraSpells;
		switch (Type)
		{
			case Intelligence:
				if (stats.GetIntelligence() < 12)
					return new BonusSpells();
				else
					extraSpells = CalculateExtraSpells(PlayerCharacter.ComputeBonus(stats.GetIntelligence()));
				break;
			case Wisdom:
				if (stats.GetWisdom() < 12)
					return new BonusSpells();
				else
					extraSpells = CalculateExtraSpells(PlayerCharacter.ComputeBonus(stats.GetWisdom()));
				break;
			case Charisma:
				if (stats.GetCharisma() < 12)
					return new BonusSpells();
				else
					extraSpells = CalculateExtraSpells(PlayerCharacter.ComputeBonus(stats.GetCharisma()));
				break;
			default:
				extraSpells = new BonusSpells();
				break;
		}

		return extraSpells;
	}

	private BonusSpells CalculateExtraSpells(int computerBonus)
	{
		BonusSpells bs;
		switch (computerBonus)
		{
			case 1:
				bs = new BonusSpells(1, 0, 0, 0, 0, 0, 0, 0, 0);
				break;
			case 2:
				bs = new BonusSpells(1, 1, 0, 0, 0, 0, 0, 0, 0);
				break;
			case 3:
				bs = new BonusSpells(1, 1, 1, 0, 0, 0, 0, 0, 0);
				break;
			case 4:
				bs = new BonusSpells(1, 1, 1, 1, 0, 0, 0, 0, 0);
				break;
			case 5:
				bs = new BonusSpells(2, 1, 1, 1, 1, 0, 0, 0, 0);
				break;
			case 6:
				bs = new BonusSpells(2, 2, 1, 1, 1, 1, 0, 0, 0);
				break;
			default:
				bs = new BonusSpells();
				break;
		}
		return bs;
	}

	public enum spellType
	{
		Intelligence, Wisdom, Charisma
	}

	@Override
	public String AbilityState()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
