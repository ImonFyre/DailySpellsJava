package com.imonfyre.dailyspells.app_code;

public class BonusSpells {
	private int _zeroLevel = 0;
	private int _firstLevel = 0;
	private int _secondLevel = 0;
	private int _thirdLevel= 0;
	private int _fourthLevel = 0;
	private int _fifthLevel = 0;
	private int _sixthLevel = 0;
	private int _seventhLevel = 0;
	private int _eighthLevel = 0;
	private int _ninthLevel = 0;
	
	public BonusSpells(){}
	
	public BonusSpells(int One,int Two, int Three, int Four, int Five, int Six, int Seven, int Eight, int Nine)
	{
		this._firstLevel = One;
		this._secondLevel = Two;
		this._thirdLevel = Three;
		this._fourthLevel = Four;
		this._fifthLevel = Five;
		this._sixthLevel = Six;
		this._seventhLevel = Seven;
		this._eighthLevel = Eight;
		this._ninthLevel = Nine;
	}
	
	public void SetZeroLevel(int _zeroLevel) {
		this._zeroLevel = _zeroLevel;
	}

	public int GetZeroLevel() {
		return _zeroLevel;
	}

	public void SetFirstLevel(int _firstLevel) {
		this._firstLevel = _firstLevel;
	}

	public int GetFirstLevel() {
		return _firstLevel;
	}

	public void SetSecondLevel(int _secondLevel) {
		this._secondLevel = _secondLevel;
	}

	public int GetSecondLevel() {
		return _secondLevel;
	}

	public void SetThirdLevel(int _thirdLevel) {
		this._thirdLevel = _thirdLevel;
	}

	public int GetThirdLevel() {
		return _thirdLevel;
	}

	public void SetFourthLevel(int _fourthLevel) {
		this._fourthLevel = _fourthLevel;
	}

	public int GetFourthLevel() {
		return _fourthLevel;
	}

	public void SetFifthLevel(int _fifthLevel) {
		this._fifthLevel = _fifthLevel;
	}

	public int GetFifthLevel() {
		return _fifthLevel;
	}

	public void SetSixthLevel(int _sixthLevel) {
		this._sixthLevel = _sixthLevel;
	}

	public int GetSixthLevel() {
		return _sixthLevel;
	}

	public void SetSeventhLevel(int _seventhLevel) {
		this._seventhLevel = _seventhLevel;
	}

	public int GetSeventhLevel() {
		return _seventhLevel;
	}

	public void SetEightLevel(int _eighthLevel) {
		this._eighthLevel = _eighthLevel;
	}

	public int GetEightLevel() {
		return _eighthLevel;
	}

	public void SetNinthLevel(int _ninthLevel) {
		this._ninthLevel = _ninthLevel;
	}

	public int GetNinthLevel() {
		return _ninthLevel;
	}
}
