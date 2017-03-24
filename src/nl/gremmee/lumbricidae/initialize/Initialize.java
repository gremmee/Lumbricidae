package nl.gremmee.lumbricidae.initialize;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import nl.gremmee.lumbricidae.ai.AIs;
import nl.gremmee.lumbricidae.ai.IArtificialIntelligence;
import nl.gremmee.lumbricidae.ai.MostLumbricidaePointsFirstAI;
import nl.gremmee.lumbricidae.core.DiceList;
import nl.gremmee.lumbricidae.core.Die;
import nl.gremmee.lumbricidae.core.Player;
import nl.gremmee.lumbricidae.core.Tile;
import nl.gremmee.lumbricidae.core.TileList;
import nl.gremmee.lumbricidae.rules.IRule;
import nl.gremmee.lumbricidae.rules.Rules;

/**
 * Initialize
 */
public class Initialize {
    private static final long SLEEP = 250;
    private static Initialize instance;
    private ArrayList<Player> playerList;
    private DiceList diceList;
    private TileList tileList;

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

    public void initializeAIs() {
        System.out.println("Initializing AIs");
        Set<IArtificialIntelligence> ais = AIs.getInstance().getAIs();
        Iterator<IArtificialIntelligence> iter = ais.iterator();
        int i = 0;
        while (iter.hasNext()) {
            IArtificialIntelligence ai = iter.next();
            System.out.print("Creating AI " + ai.getClass().getSimpleName() + "...");
            System.out.println("[OK]");
            repaint("Creating AI " + ++i);
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

    public TileList initializeTiles() {
        System.out.println("Initializing Tiles");
        tileList = new TileList();
        for (int i = 21; i <= 36; i++) {
            Tile tile = new Tile((((i - 1) / 4) - 4), i);
            System.out
                    .print("Creating Tile " + tile.getNumber() + " with lumbricidae " + tile.getLumbricidae() + "...");
            tileList.add(tile);
            System.out.println("[OK]");
            repaint("Creating Tile " + tile.getNumber() + "...");
        }
        return tileList;
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
