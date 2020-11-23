package com.imonfyre.dailyspells;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.imonfyre.dailyspells.app_code.OnCharacterEditListener;
import com.imonfyre.dailyspells.app_code.PlayerCharacter;
import com.imonfyre.dailyspells.app_code.PlayerCharacter.Races;
import com.imonfyre.dailyspells.app_code.Serializer;
import com.imonfyre.dailyspells.app_code.Classes.Barbarian;
import com.imonfyre.dailyspells.app_code.Classes.Bard;
import com.imonfyre.dailyspells.app_code.Classes.CharacterClass;
import com.imonfyre.dailyspells.app_code.Classes.CharacterClass.Classes;
import com.imonfyre.dailyspells.app_code.Classes.Cleric;
import com.imonfyre.dailyspells.app_code.Classes.Druid;
import com.imonfyre.dailyspells.app_code.Classes.Fighter;
import com.imonfyre.dailyspells.app_code.Classes.Paladin;
import com.imonfyre.dailyspells.app_code.Classes.Ranger;
import com.imonfyre.dailyspells.app_code.Classes.Rogue;
import com.imonfyre.dailyspells.app_code.Classes.Sorcerer;
import com.imonfyre.dailyspells.app_code.Classes.Wizard;
import com.imonfyre.dailyspells.app_code.Classes.Paladin.Abilities;
import com.imonfyre.dailyspells.app_code.Dialog.StatsEntryDialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class PC_display extends Activity
{
	String charID;
	PlayerCharacter playerCharacter;
	ListView lv;
	SimpleAdapter sa;
	Button NewDayButton;
	List<HashMap<String, String>> fillMaps;
	Context PC_display_Context = PC_display.this;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pc);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Intent incoming = getIntent();
		// charID = incoming.getStringExtra("characterID");
		playerCharacter = Serializer.DeserializePC((byte[]) incoming.getSerializableExtra("characterObject"));
		// this.addContentView(view, view.getLayoutParams());
		TextView strLabel = (TextView) this.findViewById(R.id.StrengthBonusLabel);
		strLabel.setText(String.format("%d / %s%d", playerCharacter.GetStrength(), playerCharacter.GetStrength() > 11 ? "+" : "", PlayerCharacter.ComputeBonus(playerCharacter.GetStrength())));

		TextView dexLabel = (TextView) this.findViewById(R.id.DexBonusLabel);
		dexLabel.setText(String.format("%d / %s%d", playerCharacter.GetDexterity(), playerCharacter.GetDexterity() > 11 ? "+" : "", PlayerCharacter.ComputeBonus(playerCharacter.GetDexterity())));

		TextView conLabel = (TextView) this.findViewById(R.id.ConBonusLabel);
		conLabel.setText(String.format("%d / %s%d", playerCharacter.GetConstitution(), playerCharacter.GetConstitution() > 11 ? "+" : "", PlayerCharacter.ComputeBonus(playerCharacter.GetConstitution())));

		TextView intLabel = (TextView) this.findViewById(R.id.IntBonusLabel);
		intLabel.setText(String.format("%d / %s%d", playerCharacter.GetIntelligence(), playerCharacter.GetIntelligence() > 11 ? "+" : "", PlayerCharacter.ComputeBonus(playerCharacter.GetIntelligence())));

		TextView wisLabel = (TextView) this.findViewById(R.id.WisBonusLabel);
		wisLabel.setText(String.format("%d / %s%d", playerCharacter.GetWisdom(), playerCharacter.GetWisdom() > 11 ? "+" : "", PlayerCharacter.ComputeBonus(playerCharacter.GetWisdom())));

		TextView chaLabel = (TextView) this.findViewById(R.id.ChaBonusLabel);
		chaLabel.setText(String.format("%d / %s%d", playerCharacter.GetCharisma(), playerCharacter.GetCharisma() > 11 ? "+" : "", PlayerCharacter.ComputeBonus(playerCharacter.GetCharisma())));

		setAbilityList();
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
			{
				TextView tvHidden = (TextView) view.findViewById(R.id.hiddenEventID);
				TextView tvClickedRemaining = (TextView) view.findViewById(R.id.RemainingUses);
				// Toast.makeText(PC_display_Context, tvHidden.getText(),
				// Toast.LENGTH_LONG).show();
				String[] process = ((String) tvHidden.getText()).split("\\:");
				// int classID =
				// ((CharacterClass)playerCharacter.GetClasses().toArray()[Integer.parseInt(process[0])]).GetClassID();
				CharacterClass cc = playerCharacter.GetClasses().get(Integer.parseInt(process[0]));
				tvClickedRemaining.setText(Integer.toString(cc.handleAbilityClick(Abilities.valueOf(process[1]), PC_display_Context)));

				// Toast.makeText(PC_display_Context, cc.GetClassName() ,
				// Toast.LENGTH_LONG).show();
				// TODO: decrement ability state if uses > 0

			}
		});
		NewDayButton = (Button) this.findViewById(R.id.NewDayButton);
		NewDayButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				for (CharacterClass cc : playerCharacter.GetClasses())
				{
					cc.NewDay(playerCharacter);
				}
				setAbilityList();
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.pc_options_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		StatsEntryDialog sed;
		switch (item.getItemId())
		{
			case android.R.id.home:
				// This is called when the Home (Up) button is pressed
				// in the Action Bar.
				Intent parentActivityIntent = new Intent(this, DailySpells.class);
				parentActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(parentActivityIntent);
				finish();
				return true;
			case R.id.menuLevelUpButton:
				sed = new StatsEntryDialog(this, new OnCharacterEditListener());
				sed.show();
				break;
			case R.id.menuPCDisplayEdit:
				sed = new StatsEntryDialog(this, new OnCharacterEditListener());
				sed.show();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void setAbilityList()
	{
		lv = (ListView) this.findViewById(R.id.AblilityListView);
		// List Mapping
		String[] from = new String[] { "AbilityName", "RemainingUses", "TotalAvailableUses", "hiddenEventID" };
		int[] to = new int[] { R.id.AbilityName, R.id.RemainingUses, R.id.TotalAvailableUses, R.id.hiddenEventID };
		fillMaps = this.RefreshCharacterAdapterList();
		sa = new SimpleAdapter(this, fillMaps, R.layout.ability_list, from, to);
		lv.setAdapter(sa);

	}

	private List<HashMap<String, String>> RefreshCharacterAdapterList()
	{
		List<HashMap<String, String>> filler = new ArrayList<HashMap<String, String>>();
		List<CharacterClass> lCC = playerCharacter.GetClasses();
		// add list of characters to main screen

		for (int i = 0; i < lCC.size(); i++)
		{
			String as = lCC.get(i).AbilityState(playerCharacter);
			for (String al : as.split("\\|\\|"))
			{
				String[] splitAl = al.split("\\:");
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("AbilityName", splitAl[1] + " ");
				map.put("RemainingUses", splitAl[2]);
				map.put("TotalAvailableUses", "/" + splitAl[3]);
				map.put("hiddenEventID", i + ":" + splitAl[0]);
				filler.add(map);
			}
		}

		return filler;
	}

	/*public class OnCharacterEditListener implements StatsEntryDialog.OnStatsEntryDialog
	{
		
		@Override
		public void CharFields(String name, int Str, int Dex, int Con, int Int, int Wis, int Cha, CharacterClass.Classes PCclass, PlayerCharacter.Races Race, int Level)
		{
			
			 * //Toast.makeText(DailySpells.this, name,
			 * Toast.LENGTH_LONG).show(); PlayerCharacter newPC = new
			 * PlayerCharacter(name, Str, Dex, Con, Int, Wis, Cha, Race);
			 * CharacterClass cc; switch (PCclass) { case Barbarian: cc = new
			 * Barbarian(newPC, Level); break; case Bard: cc = new Bard(newPC,
			 * Level); break; case Cleric: cc = new Cleric(newPC, Level); break;
			 * case Druid: cc = new Druid(newPC, Level); break; case Fighter: cc
			 * = new Fighter(newPC, Level); break; case Monk: cc = new
			 * Bard(newPC, Level); break; case Paladin: cc = new Paladin(newPC,
			 * Level); break; case Ranger: cc = new Ranger(newPC, Level); break;
			 * case Rogue: cc = new Rogue(newPC, Level); break; case Sorcerer:
			 * cc = new Sorcerer(newPC, Level); break; case Wizard: cc = new
			 * Wizard(newPC, Level); break; default: cc = new CharacterClass();
			 * } //newPC.AddClass(cc);
			 * //PlayerCharacter.SaveNewPC(DailySpells.this, newPC); //Intent
			 * showCharacter = new Intent(pc_, PC_display.class);
			 * //showCharacter.putExtra("characterObject",
			 * Serializer.SerializePC(newPC)); //startActivity(showCharacter);
			 
		}
	}*/
}
