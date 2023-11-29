package sk.uniba.fmph.dcs;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UsedTilesTest {
    private UsedTiles usedTiles;

    @Before
    public void setUp() {
        usedTiles = new UsedTiles();
    }

    @Test
    public void test_used_tiles() {
        List<Tile> tiles = new ArrayList<>();
        tiles.add(Tile.BLUE);
        tiles.add(Tile.BLACK);

        assertEquals("Used tiles should be empty.", "", usedTiles.state());
        usedTiles.give(tiles);
        assertEquals("Used tiles should be filled.", "BL", usedTiles.state());
        assertEquals("Method takeAll should return all used tiles", tiles, usedTiles.takeAll());
        assertEquals("Used tiles should be empty.", "", usedTiles.state());
    }
}
