package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PatternLine {
    private final WallLine correspondingWallLine;
    private final int capacity;
    private final List<Tile> tiles;
    private final Floor floor;
    private final UsedTilesGiveInterface usedTiles;

    public PatternLine(int index, UsedTilesGiveInterface usedTiles, WallLine wallLine, Floor floor) {
        this.capacity = index + 1;
        this.correspondingWallLine = wallLine;
        this.tiles = new ArrayList<>();
        this.floor = floor;
        this.usedTiles = usedTiles;
    }
    public void put(List<Tile> tiles) {
        if(tiles.contains(Tile.STARTING_PLAYER)){
            List<Tile> startingPlayer = new ArrayList<>();
            startingPlayer.add(Tile.STARTING_PLAYER);
            floor.put(startingPlayer);
            tiles.remove(Tile.STARTING_PLAYER);
        }
        if(!correspondingWallLine.canPutTile(tiles.get(0))){
            floor.put(tiles);
            return;
        }

        if(!this.tiles.isEmpty() && this.tiles.get(0) != tiles.get(0)){
            floor.put(tiles);
            return;
        }

        while(!tiles.isEmpty() && this.tiles.size() < capacity){
            this.tiles.add(tiles.remove(0));
        }

        if(!tiles.isEmpty()) floor.put(tiles);
    }

    public Points finishRound() {
        if(this.tiles.size() == capacity){
            Points points = correspondingWallLine.putTile(this.tiles.get(0));
            this.usedTiles.give(tiles);
            this.tiles.clear();
            return new Points(points.getValue());
        } else{
            return new Points(0);
        }
    }

    public String state() {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < capacity - tiles.size(); i++){
            sb.append("_");
        }

        for(Tile tile : tiles){
            sb.append(tile);
        }

        sb.append("\n");

        return sb.toString();
    }
}
