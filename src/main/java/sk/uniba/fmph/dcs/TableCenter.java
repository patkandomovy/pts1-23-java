package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;

public class TableCenter extends TileSource{

    public TableCenter() {
        super();
        tiles.add(Tile.STARTING_PLAYER);
    }
    @Override
    public List<Tile> take(int idx) {
        List<Tile> takenTiles = new ArrayList<>();
        Tile tileType = tiles.get(idx);
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

    public void add(List<Tile> tiles){
        this.tiles.addAll(tiles);
    }
}
