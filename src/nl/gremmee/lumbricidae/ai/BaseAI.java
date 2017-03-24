package nl.gremmee.lumbricidae.ai;

public abstract class BaseAI<T> implements IArtificialIntelligence, Comparable<T> {
    private int priority;

    @Override
    public int compareTo(T o) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;

        return AFTER;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
