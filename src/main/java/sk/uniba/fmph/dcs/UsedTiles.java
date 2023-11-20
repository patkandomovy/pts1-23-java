package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UsedTiles implements UsedTilesGiveInterface{
    private List<Tile> tiles;
    public UsedTiles() {
        tiles = new ArrayList<>();
    }
    public void give(Collection<Tile> tile) {
        tiles.addAll(tile);
    }
    public List<Tile> takeAll(){
        tiles.remove(Tile.STARTING_PLAYER);
        List<Tile> tempTiles = new ArrayList<>(tiles);
        tiles.clear();
        return tempTiles;
    }
    public String state(){
        String toReturn = "";
        for (Tile tile : tiles) {
            toReturn += tile.toString();
        }
        return toReturn;
    }
}
