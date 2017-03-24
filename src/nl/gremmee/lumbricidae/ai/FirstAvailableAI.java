package nl.gremmee.lumbricidae.ai;

import nl.gremmee.lumbricidae.core.DieResult;
import nl.gremmee.lumbricidae.core.RollList;
import nl.gremmee.lumbricidae.core.Tile;
import nl.gremmee.lumbricidae.core.TileList;

/**
 * Gets the first available Tile which can be taken after a roll. Continue to
 * roll as long as this is possible (or until busted)
 */
public class FirstAvailableAI extends BaseAI implements IArtificialIntelligence {
    private boolean busted;

    /**
     * {@inheritDoc}
     */
    @Override
    public Long analyse(RollList aRollList, RollList aSaveList) {
        this.busted = false;

        int count = 0;
        int found = 0;
        Long max = new Long("0");
        for (DieResult dieResult : DieResult.values()) {
            if (!aSaveList.contains(dieResult.getResult())) {
                for (Long roll : aRollList) {
                    if (roll.equals(dieResult.getResult())) {
                        count++;
                    }
                }
                if (count >= found) {
                    found = count;
                    max = dieResult.getResult();
                }
                count = 0;
            }
        }
        return max;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBusted(RollList aRollList, RollList aSaveList) {
        if (this.busted) {
            System.out.println("Busted");
        }
        return this.busted;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean playOn(RollList aRollList, RollList aSaveList, TileList aTileList) {
        if (aRollList.isEmpty()) {
            return false;
        }
        boolean lumbricidae = false;
        // must have a Lumbricidae
        if (aSaveList.contains(new Long("6"))) {
            lumbricidae = true;
        }
        int points = 0;
        for (Long save : aSaveList) {
            if (save.intValue() == 6) {
                points += 5;
            } else {
                points += save.intValue();
            }
        }
        boolean found = true;
        if ((points > 20) && lumbricidae) {
            for (Tile tile : aTileList) {
                if (points >= tile.getNumber()) {
                    found = false;
                    break;
                }
            }
        }
        return found;
    }
}
