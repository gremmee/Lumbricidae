package nl.gremmee.lumbricidae.initialize;

import nl.gremmee.lumbricidae.ai.MostLumbricidaePointsFirstAI;
import nl.gremmee.lumbricidae.core.*;
import nl.gremmee.lumbricidae.rules.IRule;
import nl.gremmee.lumbricidae.rules.Rules;

import java.util.*;

/**
 * Initialize
 */
public class Initialize {
	private static final long SLEEP = 250;
	private static Initialize instance;
	private ArrayList<Player> playerList;
	private DiceList diceList;
	private StoneList stoneList;

	private Initialize() {
	}

	public static Initialize getInstance() {
		if (instance == null) {
			instance = new Initialize();
		}
		return instance;
	}

	public DiceList initializeDice(int aNumDice) {
		System.out.println("Initializing Dice");
		diceList = new DiceList();
		for (int i = 0; i < aNumDice; i++) {
			System.out.print("Creating Die " + (i + 1) + "...");
			Die die = new Die(6);
			diceList.add(die);
			System.out.println("[OK]");
			repaint("Creating Die " + (i + 1) + "...");
		}
		return diceList;
	}

	public void initializeRules() {
		System.out.println("Initializing Rules");
		Set<IRule> rules = Rules.getInstance().getRules();
		Iterator<IRule> iter = rules.iterator();
		int i = 0;
		while (iter.hasNext()) {
			IRule rule = iter.next();
			System.out.print("Creating Rule " + rule.getClass().getSimpleName() + "...");
			System.out.println("[OK]");
			repaint("Creating Rule " + ++i);
		}
	}

	public List<Player> initializePlayers(int aNumPlayers) {
		System.out.println("Initializing Players");
		playerList = new ArrayList<Player>();
		for (int i = 0; i < aNumPlayers; i++) {
			System.out.print("Creating Player " + (i + 1) + "...");
			Player player = new Player(i + 1, "Player " + (i + 1), new MostLumbricidaePointsFirstAI(), diceList);
			playerList.add(player);
			System.out.println("[OK]");
			repaint("Creating Player " + (i + 1) + "...");
		}
		return playerList;
	}

	public StoneList initializeStone() {
		System.out.println("Initializing Stones");
		stoneList = new StoneList();
		for (int i = 21; i <= 36; i++) {
			Stone stone = new Stone((((i - 1) / 4) - 4), i);
			System.out.print(
					"Creating Stone " + stone.getNumber() + " with lumbricidae " + stone.getLumbricidae() + "...");
			stoneList.add(stone);
			System.out.println("[OK]");
			repaint("Creating Stone " + stone.getNumber() + "...");
		}
		return stoneList;
	}

	private void repaint(String aMessage) {
		sleep();
	}

	private void sleep() {
		try {
			Thread.sleep(SLEEP);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
