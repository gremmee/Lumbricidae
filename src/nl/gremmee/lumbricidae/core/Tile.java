package nl.gremmee.lumbricidae.core;

public class Tile {

	private int lumbricidae;
	private int number;
	private boolean enabled;

	public Tile(int aLumbricidae, int aNumber) {
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
		return "Tile " + this.number + " " + this.enabled;
	}
}
