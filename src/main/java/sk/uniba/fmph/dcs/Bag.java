package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Bag {
    private final List<Tile> tiles;
    private final UsedTiles usedTiles;
    private final Random random;

    public Bag(long seed, UsedTiles usedTiles) {
        this.tiles = new ArrayList<>();
        this.usedTiles = usedTiles;
        this.random = new Random(seed);

        for (int i = 0; i < 20; i++) {
            tiles.add(Tile.RED);
            tiles.add(Tile.BLUE);
            tiles.add(Tile.YELLOW);
            tiles.add(Tile.GREEN);
            tiles.add(Tile.BLACK);
        }

        Collections.shuffle(tiles, random);
    }

    public List<Tile> take(int count) {
        if (count >= tiles.size()) {
            tiles.addAll(usedTiles.takeAll());
            Collections.shuffle(tiles, random);
        }

        List<Tile> takenTiles = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            takenTiles.add(tiles.remove(0));
        }

        return takenTiles;
    }

    public String state() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bag:");
        for (Tile tile : tiles) {
            sb.append(" " + tile);
        }
        sb.append("\n");
        return sb.toString();
    }
}
