package nl.gremmee.lumbricidae.rules;

import nl.gremmee.lumbricidae.core.DieResult;
import nl.gremmee.lumbricidae.core.RollList;

/**
 * Must have a six (Lumbricidae) = 5 points
 */
public class LumbricidaeRule extends BaseRule implements IRule {

    @Override
    public boolean validate(RollList aRollList) {
        System.out.println(this.getClass().getSimpleName() + " validate");
        return contains(aRollList, DieResult.LUMBRICIDAE.getResult());
    }

    private boolean contains(RollList aRollList, Long aNumber) {
        for (Long roll : aRollList) {
            if (roll.equals(aNumber)) {
                return true;
            }
        }
        return false;
    }
}
