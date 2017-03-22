package nl.gremmee.lumbricidae.core;

public class Stone {

	private int lumbricidae;
	private int number;
	private boolean enabled;

	public Stone(int aLumbricidae, int aNumber) {
		this.lumbricidae = aLumbricidae;
		this.number = aNumber;
		this.enabled = true;
	}

	public int getLumbricidae() {
		return this.lumbricidae;
	}

	public int getNumber() {
		return this.number;
	}

	public boolean getEnabled() {
		return this.enabled;
	}

	public void setdisabled() {
		this.enabled = false;
	}

	@Override
	public String toString() {
		return "Stone " + this.number + " " + this.enabled;
	}
}
