package nl.gremmee.lumbricidae.ai;

import nl.gremmee.lumbricidae.core.DieResult;
import nl.gremmee.lumbricidae.core.RollList;
import nl.gremmee.lumbricidae.core.TileList;

public class MostLumbricidaePointsFirstAI implements IArtificialIntelligence {
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
        if (aRollList.size() < 2) {

            return false;
        }
        return true;
    }
}
