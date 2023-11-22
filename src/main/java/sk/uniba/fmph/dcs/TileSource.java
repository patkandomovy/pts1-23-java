package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class TileSource {
    protected ArrayList<Tile> tiles;

    protected TileSource() {
        tiles = new ArrayList<>();
    }

    public abstract List<Tile> take(Tile tileType);

    public boolean isEmpty() {
        return tiles.isEmpty();
    }
    public abstract void startNewRound();

    public String state() {
        String toReturn = "";
        for (final Tile tile : tiles) {
            toReturn += tile.toString();
        }
        return toReturn;
    }
}
