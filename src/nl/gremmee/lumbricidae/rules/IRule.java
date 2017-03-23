package nl.gremmee.lumbricidae.rules;

import nl.gremmee.lumbricidae.core.RollList;

public interface IRule {
    public boolean validate(RollList aRollList);
}
