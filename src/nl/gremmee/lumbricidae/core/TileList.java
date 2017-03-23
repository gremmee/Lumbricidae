package nl.gremmee.lumbricidae.core;

import java.util.ArrayList;

public class TileList extends ArrayList<Tile> {

    private static final long serialVersionUID = -4817747652520081828L;

    /**
     * Creates a new TileList object. max content
     * 21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36
     */
    public TileList() {
    }

    public int getIndex(int aNumber) {
        for (int i = 0; i < this.size(); i++) {
            Tile tile = this.get(i);
            if (tile.getEnabled()) {
                if (tile.getNumber() == aNumber) {
                    System.out.println("Tile found with number " + aNumber);
                    return i;
                }
            }
        }
        System.out.println("Tile not found with number " + aNumber);
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
            for (Tile tile : this) {
                if (tile.getEnabled()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void putBackTile(Tile putBack) {
        System.out.println("Putting back tile " + putBack.getNumber());
        if (isLastTile(putBack)) {
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

    public void putTileOnPlayer(int aIndex, Player aPlayer) {
        aPlayer.putTileInList(this.get(aIndex));
        this.remove(aIndex);
    }

    private boolean isLastTile(Tile aPutBack) {
        for (int i = this.size() - 1; i > 0; i--) {
            if (this.get(i).getEnabled()) {
                return (this.get(i).getNumber() == aPutBack.getNumber());
            }
        }
        return false;
    }
}
