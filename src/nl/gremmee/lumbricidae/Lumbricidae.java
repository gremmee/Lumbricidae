package nl.gremmee.lumbricidae;

import java.util.List;

import nl.gremmee.lumbricidae.core.DiceList;
import nl.gremmee.lumbricidae.core.Player;
import nl.gremmee.lumbricidae.core.Stone;
import nl.gremmee.lumbricidae.core.StoneList;
import nl.gremmee.lumbricidae.initialize.Initialize;
import nl.gremmee.lumbricidae.rules.Rules;

public final class Lumbricidae {
	private static final int NUM_DICE = 8;
	private static final int NUM_PLAYERS = 2;
	private static final int NUM_STONES = 16;

	private static DiceList diceList;
	private static List<Player> playerList;
	private static StoneList masterStoneList;
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

	public static final StoneList getMasterStoneList() {
		return masterStoneList;
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
		max += NUM_STONES;
		max += NUM_PLAYERS;
		diceList = Initialize.getInstance().initializeDice(NUM_DICE);
		masterStoneList = Initialize.getInstance().initializeStone();
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
				System.out.println("PlayerStoneList = " + player.getStoneList().toString());
				int points = player.play();
				System.out.println("Points : " + points);
				boolean stonecollected = false;
				for (Player otherPlayer : playerList) {
					if (!otherPlayer.equals(player)) {
						if (otherPlayer.hasStones() && (otherPlayer.getTopStone().getNumber() == points)) {
							Stone topStone = otherPlayer.removeTopStoneFromList();
							player.putStoneInList(topStone);
							stonecollected = true;
							break;
						}
					}
				}
				if ((!stonecollected) && (points > masterStoneList.get(0).getNumber()) && !player.getBusted()) {
					int index = -1;
					do {
						if (index != -1) {
							masterStoneList.putStoneOnPlayer(index, player);
							if (masterStoneList.isEmpty()) {
								break GAME;
							}
							break;
						}
						while ((index == -1) && (points > 20)) {
							index = masterStoneList.getIndex(points--);
						}
					} while (index != -1);
				} else if (!stonecollected) {
					// busted
					Stone putBack = player.getPuttBack();
					if (putBack != null) {
						masterStoneList.putBackStone(putBack);
					}
				}
				System.out.println("MasterStoneList = " + masterStoneList.toString());
				System.out.println("PlayerStoneList = " + player.getStoneList().toString());
				player.setactive(false);
			}
		} while (!masterStoneList.isEmpty());
		Lumbricidae.setDone();
		System.out.println("Done...");
		int max = 0;
		for (Player player : playerList) {
			if (player.getTotalLumbricidae() > max) {
				max = player.getTotalLumbricidae();
			}
			System.out.println("Player " + player);
			System.out.println("PlayerStoneList = " + player.getStoneList().toString());
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
