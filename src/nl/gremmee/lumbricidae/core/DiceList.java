package nl.gremmee.lumbricidae.core;

import java.util.ArrayList;

public class DiceList extends ArrayList<Die> {
    private static final long serialVersionUID = 1866660346615706168L;

    private RollList rollList;

    public DiceList() {
        rollList = new RollList();
    }

    public RollList getRollList() {
        return this.rollList;
    }

    public RollList roll() {
        rollList.clear();
        System.out.print("Rolling ");
        for (int i = 0; i < this.size(); i++) {
            Long roll = this.get(i).roll();
            rollList.add(roll);
            System.out.print(roll.intValue());
        }
        System.out.println();
        return rollList;
    }
}
