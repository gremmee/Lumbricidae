package nl.gremmee.lumbricidae.ai;

import nl.gremmee.lumbricidae.core.RollList;
import nl.gremmee.lumbricidae.core.TileList;

public interface IArtificialIntelligence {
    public Long analyse(RollList aRollList, RollList aSaveList);

    public boolean isBusted(RollList aRollList, RollList aSaveList);

    public boolean playOn(RollList aRollList, RollList aSaveList, TileList aTileList);
}
