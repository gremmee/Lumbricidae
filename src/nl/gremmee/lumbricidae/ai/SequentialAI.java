package nl.gremmee.lumbricidae.ai;

import nl.gremmee.lumbricidae.core.DieResult;
import nl.gremmee.lumbricidae.core.RollList;
import nl.gremmee.lumbricidae.core.TileList;

public class SequentialAI extends BaseAI implements IArtificialIntelligence {
    private boolean busted;

    /**
     * {@inheritDoc}
     */
    @Override
    public Long analyse(RollList aRollList, RollList aSaveList) {
        this.busted = false;
        Long six = DieResult.LUMBRICIDAE.getResult();
        Long five = DieResult.FIVE.getResult();
        Long four = DieResult.FOUR.getResult();
        Long three = DieResult.THREE.getResult();
        Long two = DieResult.TWO.getResult();
        Long one = DieResult.ONE.getResult();
        // are there any 6s which are not in the savelist
        if (!contains(aSaveList, six) && (contains(aRollList, six))) {
            System.out.println("Analyse six");
            return six;
        }
        // are there any 5s which are not in the savelist
        else if (!contains(aSaveList, five) && (contains(aRollList, five))) {
            System.out.println("Analyse five");
            return five;
        }
        // are there any 4s which are not in the savelist
        else if (!contains(aSaveList, four) && (contains(aRollList, four))) {
            System.out.println("Analyse four");
            return four;
        }
        // are there any 3s which are not in the savelist
        else if (!contains(aSaveList, three) && (contains(aRollList, three))) {
            System.out.println("Analyse three");
            return three;
        }
        // are there any 2s which are not in the savelist
        else if (!contains(aSaveList, two) && (contains(aRollList, two))) {
            System.out.println("Analyse two");
            return two;
        }
        // are there any 1s which are not in the savelist
        else if (!contains(aSaveList, one) && (contains(aRollList, one))) {
            System.out.println("Analyse one");
            return one;
        }
        this.busted = true;
        return null;
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

    /**
     * <code>aNumber</code> in <code>aRollist</code>
     *
     * @param aRollList
     *            the rolllist
     * @param aNumber
     *            the number to be checked
     *
     * @return true if the number is in the rolllist.
     */
    private boolean contains(RollList aRollList, Long aNumber) {
        for (Long roll : aRollList) {
            if (roll.equals(aNumber)) {
                return true;
            }
        }
        return false;
    }
}
