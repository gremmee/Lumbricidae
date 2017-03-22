package nl.gremmee.lumbricidae.core;

import java.util.*;

public class StoneList extends ArrayList<Stone> {

	private static final long serialVersionUID = -4817747652520081828L;

	/**
	 * Creates a new StoneList object. max content
	 * 21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36
	 */
	public StoneList() {
	}

	public int getIndex(int aNumber) {
		for (int i = 0; i < this.size(); i++) {
			Stone stone = this.get(i);
			if (stone.getEnabled()) {
				if (stone.getNumber() == aNumber) {
					System.out.println("Stone found with number " + aNumber);
					return i;
				}
			}
		}
		System.out.println("Stone not found with number " + aNumber);
		return -1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		if (super.isEmpty()) {
			return true;
		} else {
			for (Stone stone : this) {
				if (stone.getEnabled()) {
					return false;
				}
			}
		}
		return true;
	}

	public void putBackStone(Stone putBack) {
		System.out.println("Putting back stone " + putBack.getNumber());
		if (isLastStone(putBack)) {
			this.add(putBack);
		} else {
			for (int i = this.size() - 1; i > 0; i--) {
				if (this.get(i).getEnabled() && (this.get(i).getNumber() >= putBack.getNumber())) {
					this.get(i).setdisabled();
					break;
				}
			}
			for (int i = 0; i <= (this.size() - 1); i++) {
				if (putBack.getNumber() < this.get(i).getNumber()) {
					this.add(i, putBack);
					break;
				}
			}
		}
	}

	public void putStoneOnPlayer(int aIndex, Player aPlayer) {
		aPlayer.putStoneInList(this.get(aIndex));
		this.remove(aIndex);
	}

	private boolean isLastStone(Stone aPutBack) {
		for (int i = this.size() - 1; i > 0; i--) {
			if (this.get(i).getEnabled()) {
				return (this.get(i).getNumber() == aPutBack.getNumber());
			}
		}
		return false;
	}
}
