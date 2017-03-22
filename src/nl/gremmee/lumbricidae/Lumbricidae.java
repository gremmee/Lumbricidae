package nl.gremmee.lumbricidae;

import java.util.List;

import nl.gremmee.lumbricidae.core.DiceList;
import nl.gremmee.lumbricidae.core.Player;
import nl.gremmee.lumbricidae.core.Tile;
import nl.gremmee.lumbricidae.core.TileList;
import nl.gremmee.lumbricidae.initialize.Initialize;
import nl.gremmee.lumbricidae.rules.Rules;

public final class Lumbricidae {
	private static final int NUM_DICE = 8;
	private static final int NUM_PLAYERS = 2;
	private static final int NUM_TILES = 16;

	private static DiceList diceList;
	private static List<Player> playerList;
	private static TileList masterTileList;
	private static boolean done = false;

	public static void main(String[] args) {
		long beginTime = System.currentTimeMillis();
		System.out.println("Starting application " + Lumbricidae.class.getSimpleName() + "...");
		System.out.println("Initializing...");
		initialize();
		System.out.println("Running...");
		game();
		System.out.println("Stopping application " + Lumbricidae.class.getSimpleName() + "...");
		long endTime = System.currentTimeMillis() - beginTime;
		System.out.println("Time (in ms): " + endTime);
	}

	private static void setDone() {
		done = true;
	}

	public static final List<Player> getPlayerList() {
		return playerList;
	}

	public static final TileList getMasterTileList() {
		return masterTileList;
	}

	public static final DiceList getDiceList() {
		return diceList;
	}

	/**
	 * Initialize the game
	 *
	 * @param aSplash
	 *            DOCUMENT ME!
	 */
	public static void initialize() {
		Initialize.getInstance();
		int max = Rules.getInstance().getRules().size();
		max += NUM_DICE;
		max += NUM_TILES;
		max += NUM_PLAYERS;
		diceList = Initialize.getInstance().initializeDice(NUM_DICE);
		masterTileList = Initialize.getInstance().initializeTiles();
		playerList = Initialize.getInstance().initializePlayers(NUM_PLAYERS);
	}

	public static final boolean isDone() {
		return done;
	}

	public static void game() {
		GAME: do {
			for (Player player : playerList) {
				player.setactive(true);
				System.out.println(player.getName() + " is playing");
				System.out.println("PlayerTileList = " + player.getTileList().toString());
				int points = player.play();
				System.out.println("Points : " + points);
				boolean tileCollected = false;
				for (Player otherPlayer : playerList) {
					if (!otherPlayer.equals(player)) {
						if (otherPlayer.hasTiles() && (otherPlayer.getTopTile().getNumber() == points)) {
							Tile topTile = otherPlayer.removeTopTileFromList();
							player.putTileInList(topTile);
							tileCollected = true;
							break;
						}
					}
				}
				if ((!tileCollected) && (points > masterTileList.get(0).getNumber()) && !player.getBusted()) {
					int index = -1;
					do {
						if (index != -1) {
							masterTileList.putTileOnPlayer(index, player);
							if (masterTileList.isEmpty()) {
								break GAME;
							}
							break;
						}
						while ((index == -1) && (points > 20)) {
							index = masterTileList.getIndex(points--);
						}
					} while (index != -1);
				} else if (!tileCollected) {
					// busted
					Tile putBack = player.getPuttBack();
					if (putBack != null) {
						masterTileList.putBackTile(putBack);
					}
				}
				System.out.println("MasterTileList = " + masterTileList.toString());
				System.out.println("PlayerTileList = " + player.getTileList().toString());
				player.setactive(false);
			}
		} while (!masterTileList.isEmpty());
		Lumbricidae.setDone();
		System.out.println("Done...");
		int max = 0;
		for (Player player : playerList) {
			if (player.getTotalLumbricidae() > max) {
				max = player.getTotalLumbricidae();
			}
			System.out.println("Player " + player);
			System.out.println("PlayerTileList = " + player.getTileList().toString());
			System.out.println("Lumbricidae " + player.getTotalLumbricidae());
		}
		for (Player player : playerList) {
			if (player.getTotalLumbricidae() == max) {
				player.setWinner();
				System.out.println(
						player + " is the winner with a total of " + player.getTotalLumbricidae() + " Lumbricidae!");
			}
		}
		System.out.println("Done!");
	}

}
