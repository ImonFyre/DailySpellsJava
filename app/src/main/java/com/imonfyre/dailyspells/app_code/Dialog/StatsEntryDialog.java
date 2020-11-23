package com.imonfyre.dailyspells.app_code.Dialog;

import com.imonfyre.dailyspells.R;
import com.imonfyre.dailyspells.app_code.Classes.CharacterClass;
import com.imonfyre.dailyspells.app_code.Classes.CharacterClass.Classes;
import com.imonfyre.dailyspells.app_code.OnCharacterEditListener;
import com.imonfyre.dailyspells.app_code.PlayerCharacter;
import com.imonfyre.dailyspells.app_code.PlayerCharacter.Races;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class StatsEntryDialog extends Dialog
{

	private OnStatsEntryDialog osed;
	private Context context;
	private PlayerCharacter _PC = null;
	private boolean _edit;
	public StatsEntryDialog(Context context, OnStatsEntryDialog osed)
	{
		super(context);
		this.context = context;
		this.osed = osed;
	}

	public StatsEntryDialog(Context context, OnCharacterEditListener osed, PlayerCharacter PC)
	{
		super(context);
		this.context = context;
		this.osed = osed;
		_PC = PC;
	}

	public interface OnStatsEntryDialog
	{
		public void CharFields(String name, int Str, int Dex, int Con, int Int, int Wis, int Cha, CharacterClass.Classes PCclass, PlayerCharacter.Races Race, int Level);
		public void setPlayerCharacter(PlayerCharacter pc);
		public void setContext(Context ctx);
		public void setEdit(boolean edit);
	}

	public void setPlayerCharacter(PlayerCharacter pc)
	{
		_PC = pc;
	}
	public void setContext(Context ctx)
	{
		context = ctx;
	}
	public void setEdit(boolean edit)
	{
		_edit = edit;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		if(_PC != null)
			setContentView(R.layout.edit_character_dialog);
		else
			setContentView(R.layout.new_character_dialog);
		Button btnOk = (Button) findViewById(R.id.okNewCharacter);
		final TextView tvName = (TextView) findViewById(R.id.CharacterName);
		final TextView tvStr = (TextView) findViewById(R.id.StrengthInput);
		final TextView tvDex = (TextView) findViewById(R.id.DexterityInput);
		final TextView tvCon = (TextView) findViewById(R.id.ConstitutionInput);
		final TextView tvInt = (TextView) findViewById(R.id.IntelligenceInput);
		final TextView tvWis = (TextView) findViewById(R.id.WisdomInput);
		final TextView tvCha = (TextView) findViewById(R.id.CharismaInput);
		final TextView tvLevel = (TextView)findViewById(R.id.LevelView);
		final Spinner spinClass = (Spinner) findViewById(R.id.Classes);
		final Spinner spinRace = (Spinner) findViewById(R.id.Races);
		final Button butLevelDown = (Button)findViewById(R.id.LevelDown);
		final Button butLevelUp = (Button)findViewById(R.id.LevelUp);
		// Set the classes ddl
		ArrayAdapter<Classes> aaClass = new ArrayAdapter<Classes>(this.context, R.layout.custom_spinner, Classes.values());
		aaClass.setDropDownViewResource(R.layout.custom_spinner_list);
		spinClass.setAdapter(aaClass);
			
		//spinClass.setAdapter(new ArrayAdapter<Classes>().createFromResource(this, R.array.classes_array, )
		// set the races ddl
		ArrayAdapter<Races> aaRace = new ArrayAdapter<Races>(this.context, R.layout.custom_spinner, Races.values());
		aaRace.setDropDownViewResource(R.layout.custom_spinner_list);
		spinRace.setAdapter(aaRace);
		
		
		if(_PC != null)
		{
			tvName.setText(_PC.GetName()); 
			 tvStr.setText(Integer.toString(_PC.GetStrength()));
			 tvDex.setText(Integer.toString(_PC.GetDexterity()));
			 tvCon.setText(Integer.toString(_PC.GetConstitution()));
			 tvInt.setText(Integer.toString(_PC.GetIntelligence()));
			 tvWis.setText(Integer.toString(_PC.GetWisdom())); 
			 tvCha.setText(Integer.toString(_PC.GetCharisma()));
			 //tvLevel.setText(Integer.toString(_PC.)
			 spinRace.setSelection( _PC.GetRace().ordinal());
			 
			 spinClass.setSelection( _PC.GetClasses().get(0).GetClassID());
			  
			 
		
		}
		btnOk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0)
			{
				if (FieldCheck(tvName, tvStr, tvDex, tvCon, tvInt, tvWis, tvCha, Integer.parseInt(tvLevel.getText().toString())))
				{
					osed.CharFields(tvName.getText().toString(), 
							Integer.parseInt(tvStr.getText().toString()), 
							Integer.parseInt(tvDex.getText().toString()), 
							Integer.parseInt(tvCon.getText().toString()),
							Integer.parseInt(tvInt.getText().toString()), 
							Integer.parseInt(tvWis.getText().toString()), 
							Integer.parseInt(tvCha.getText().toString()), 
							Classes.values()[spinClass.getSelectedItemPosition()],
							Races.values()[spinRace.getSelectedItemPosition()], Integer.parseInt(tvLevel.getText().toString()));
				}
				else
				{
					CharSequence text = "Empty Value in Character Entry";
					Toast.makeText(context, text, Toast.LENGTH_LONG).show();
					return;
				}
				dismiss();
			}
		});
		butLevelDown.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View arg0)
			{
				int tvLev = Integer.parseInt( tvLevel.getText().toString());
				if (tvLev > 1)
				{
					tvLev--;
					tvLevel.setText(Integer.toString(tvLev));
				}
			}
			
		});
		butLevelUp.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View arg0)
			{
				int tvLev = Integer.parseInt( tvLevel.getText().toString());
				if (tvLev < 20)
				{
					tvLev++;
					tvLevel.setText(Integer.toString(tvLev));
				}
			}
			
		});
		// setup handler for the Cancel button
		Button btnCancel = (Button) findViewById(R.id.cancelNewCharacter);
		btnCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0)
			{
				dismiss();
			}
		});
	}

	private boolean FieldCheck(TextView tvName, TextView tvStr, TextView tvDex, TextView tvCon, TextView tvInt, TextView tvWis, TextView tvCha, int level)
	{
		if (tvName.getText() == "" || tvStr.getText() == "" || tvDex.getText() == "" || tvCon.getText() == "" || tvInt.getText() == "" || tvWis.getText() == "" || tvCha.getText() == "")
			return false;
		if (tvName.length() < 1 || tvStr.length() < 1 || tvDex.length() < 1 || tvCon.length() < 1 || tvInt.length() < 1 || tvWis.length() < 1 || tvCha.length() < 1)
			return false;
		return true;
	}
}
