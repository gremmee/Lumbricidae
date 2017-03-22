package nl.gremmee.lumbricidae.core;

public enum DieResult {
	ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), LUMBRICIDAE("6");

	private Long result;

	private DieResult(String aResult) {
		this.result = new Long(aResult);
	}

	public Long getResult() {
		return this.result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		String s = super.toString();
		return s.substring(0, 1) + s.substring(1).toLowerCase();
	}
}
