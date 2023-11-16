package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.Collection;

public class TableCenter extends TileSource{

    public TableCenter() {
        super();
        tiles.add(Tile.STARTING_PLAYER);
    }
    @Override
    public Collection<Tile> take(Tile tileType) {
        Collection<Tile> takenTiles = new ArrayList<>();
        if (tiles.contains(Tile.STARTING_PLAYER)) {
            takenTiles.add(Tile.STARTING_PLAYER);
        }
        for (Tile tile : tiles) {
            if (tile == tileType) {
                takenTiles.add(tile);
            }
        }
        tiles.removeAll(takenTiles);
        return takenTiles;
    }
    @Override
    public void startNewRound() {
        tiles.add(Tile.STARTING_PLAYER);
    }

    public void add(Collection<Tile> tiles){
        this.tiles.addAll(tiles);
    }
}
