package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;

public class Factory extends TileSource {
    private final Bag bag;
    private final TableCenter tableCenter;

    public Factory(Bag bag, TableCenter tableCenter) {
        super();
        this.bag = bag;
        this.tableCenter = tableCenter;
    }

    @Override
    public List<Tile> take(int idx) {
        List<Tile> takenTiles = new ArrayList<>();
        if (idx >= tiles.size()) return new ArrayList<>();
        Tile tileType = tiles.get(idx);
        for (Tile tile : tiles) {
            if (tile == tileType) {
                takenTiles.add(tile);
            }
        }

        tiles.removeAll(takenTiles);
        tableCenter.add(tiles);
        tiles.clear();
        return takenTiles;
    }

    @Override
    public void startNewRound() {
        this.tiles.addAll(bag.take(4));
    }


}
