package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WallLine {
    private static ArrayList<Tile> getTilesOrder(int index) {
        Tile[] orderPattern = {Tile.BLUE, Tile.YELLOW, Tile.RED, Tile.BLACK, Tile.GREEN};
        ArrayList<Tile> tilesOrder = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            tilesOrder.add(orderPattern[(i + index) % 5]);
        }
        return tilesOrder;
    }

    private WallLine lineUp;
    private WallLine lineDown;
    private final ArrayList<Tile> tilesOrder;
    private final boolean[] tilesPlacement;

    public WallLine(int index) {
        tilesOrder = getTilesOrder(index);
        tilesPlacement = new boolean[tilesOrder.size()];
    }

    public WallLine(WallLine lineUp, WallLine lineDown, int index) {
        this.lineUp = lineUp;
        this.lineDown = lineDown;
        tilesOrder = getTilesOrder(index);
        tilesPlacement = new boolean[tilesOrder.size()];
    }

    public void setLineUp(WallLine wallLine) {
        lineUp = wallLine;
    }

    public void setLineDown(WallLine wallLine) {
        lineDown = wallLine;
    }

    public boolean canPutTile(Tile tile) {
        return !tilesPlacement[tilesOrder.indexOf(tile)];
    }

    public List<Optional<Tile>> getTiles() {
        List<Optional<Tile>> tiles = new ArrayList<>();
        for (int i = 0; i < tilesPlacement.length; i++) {
            tiles.add( tilesPlacement[i] ? Optional.of(tilesOrder.get(i)) : Optional.empty() );
        }
        return tiles;
    }

    public Points putTile(Tile tile) {
        if (!canPutTile(tile)) return new Points(0);

        int index = tilesOrder.indexOf(tile);
        tilesPlacement[index] = true;
        int countHorizontal = 1;
        int countVertical = 1;

        int i = 1;
        while (index - i >= 0 && tilesPlacement[index - i]) {
            countHorizontal++;
            i++;
        }
        i = 1;
        while (index + i < tilesPlacement.length && tilesPlacement[index + i]) {
            countHorizontal++;
            i++;
        }

        WallLine line = lineUp;
        while (line != null && line.tilesPlacement[index]) {
            countVertical++;
            line = line.lineUp;
        }
        line = lineDown;
        while (line != null && line.tilesPlacement[index]) {
            countVertical++;
            line = line.lineDown;
        }

        if (countHorizontal > 1 && countVertical > 1) return new Points(countHorizontal + countVertical);

        return new Points(countHorizontal + countVertical - 1);
    }

    public String state() {
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < tilesPlacement.length; i++) {
            str.append(String.format("%s: %s\n", tilesOrder.get(i).toString(), tilesPlacement[i] ? "placed" : "empty"));
        }
        return str.toString();
    }
}
