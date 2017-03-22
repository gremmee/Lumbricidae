package nl.gremmee.lumbricidae.rules;

public abstract class BaseRule<T> implements IRule, Comparable<T> {
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
