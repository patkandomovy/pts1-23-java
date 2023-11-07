package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.Collection;

public abstract class TileSource {
    private ArrayList<Tile> tiles;

    public TileSource() {
        tiles = new ArrayList<>();
    }

    public Collection<Tile> take(Tile tileType) {
        Collection<Tile> takenTiles = new ArrayList<>();
        for (Tile tile : tiles) {
            if (tile == tileType) {
                takenTiles.add(tile);
            }
        }
        tiles.removeAll(takenTiles);
        return takenTiles;
    }

    public boolean isEmpty() {
        return tiles.isEmpty();
    }

    public void startNewRound() {
        //TODO
    }

    public String state() {
        //TODO
        return null;
    }
}
