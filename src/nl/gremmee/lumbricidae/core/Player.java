package nl.gremmee.lumbricidae.core;

import java.util.Iterator;
import java.util.Set;

import nl.gremmee.lumbricidae.Lumbricidae;
import nl.gremmee.lumbricidae.ai.IArtificialIntelligence;
import nl.gremmee.lumbricidae.rules.IRule;
import nl.gremmee.lumbricidae.rules.Rules;

public class Player {
	private boolean active;
	private boolean busted;
	private boolean winner;
	private String name;
	private int number;
	private TileList tileList;
	private Tile putBack;
	private DiceList diceList;
	private DiceList masterDiceList;
	private RollList rollList;
	private RollList saveList;
	private IArtificialIntelligence intelligence;

	public Player(int aNumber, String aName, IArtificialIntelligence aArtificialIntelligence, DiceList aDiceList) {
		this.active = false;
		this.winner = false;
		this.name = aName;
		this.number = aNumber;
		this.intelligence = aArtificialIntelligence;
		this.tileList = new TileList();
		this.diceList = new DiceList();
		this.masterDiceList = new DiceList();
		this.diceList.addAll(aDiceList);
		this.masterDiceList.addAll(aDiceList);
		this.rollList = new RollList();
		this.saveList = new RollList();

	}

	public IArtificialIntelligence getIntelligence() {
		return intelligence;
	}

	public Tile getPuttBack() {
		Tile tile = this.putBack;
		this.putBack = null;
		return tile;
	}

	public int getTotalLumbricidae() {
		int lumbricidae = 0;
		for (Tile tile : this.getTileList()) {
			lumbricidae += tile.getLumbricidae();
		}
		return lumbricidae;
	}

	public void putTileInList(Tile aTile) {
		this.tileList.add(aTile);
	}

	public Tile removeTopTileFromList() {
		if (!this.tileList.isEmpty()) {
			this.putBack = this.tileList.remove(this.tileList.size() - 1);
			return this.putBack;
		}
		return null;
	}

	public TileList getTileList() {
		return this.tileList;
	}

	public boolean hasTiles() {
		return !this.tileList.isEmpty();
	}

	public int getTileListSize() {
		return this.tileList.size();
	}

	public Tile getTopTile() {
		if (!this.tileList.isEmpty()) {
			return this.tileList.get(this.tileList.size() - 1);
		}
		return null;
	}

	@Override
	public boolean equals(Object obj) {
		Player otherPlayer = (Player) obj;
		return this.getNumber() == otherPlayer.getNumber();
	}

	public boolean getActive() {
		return this.active;
	}

	public void setactive(boolean aActive) {
		this.active = aActive;
	}

	public boolean getBusted() {
		return this.busted;
	}

	public String getName() {
		return this.name;
	}

	public boolean isWinner() {
		return this.winner;
	}

	public void setWinner() {
		this.winner = true;
	}

	public int getNumber() {
		return this.number;
	}

	@Override
	public String toString() {
		return "Player " + this.name;
	}

	public int play() {
		this.busted = false;
		this.rollList.clear();
		this.saveList.clear();
		this.diceList.clear();
		this.diceList.addAll(this.masterDiceList);
		int points = 0;
		int roll = 0;
		do {
			this.rollDice();
			System.out.println("Roll : " + ++roll);
			System.out.println("Player Rolllist = " + rollList.toString());
			Long result = this.getIntelligence().analyse(rollList, saveList);
			if (result != null) {
				saveDice(result.intValue());
				System.out.println("Player Savelist = " + saveList.toString());
			}
		} while (this.getIntelligence().playOn(rollList, saveList, Lumbricidae.getMasterTileList())
				&& !this.getIntelligence().isBusted(rollList, saveList));
		if (!this.getIntelligence().isBusted(rollList, saveList)) {
			// Rules
			Set<IRule> rules = Rules.getInstance().getRules();
			Iterator<IRule> iter = rules.iterator();
			while (iter.hasNext()) {
				if (!this.busted) {
					IRule rule = iter.next();
					if (!rule.validate(saveList)) {
						this.busted = true;
						break;
					}
				}
			}

			if (!this.busted) {
				// Points
				for (Long point : saveList) {
					if (point.intValue() == 6) {
						points += 5;
					} else {
						points += point;
					}
				}
				System.out.println("Points after play " + points);
			} else {
				// busted
				this.removeTopTileFromList();
			}
		} else {
			// busted
			this.removeTopTileFromList();
		}
		System.out.println("Player Rolllist = " + rollList.toString());
		System.out.println("Player Savelist = " + saveList.toString());
		return points;
	}

	public RollList rollDice() {
		this.rollList.clear();
		this.rollList.addAll(this.diceList.roll());
		return this.rollList;
	}

	public void saveDice(int aNumber) {
		for (int i = this.rollList.size() - 1; i >= 0; --i) {
			Long roll = this.rollList.get(i);
			if (roll.intValue() == aNumber) {
				this.saveList.add(roll);
				this.rollList.remove(i);
				this.diceList.remove(0);
			}
		}
	}
}
