package sk.uniba.fmph.dcs;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class FactoryTest {
    private Factory factory;
    private Bag bag;
    private TableCenter tableCenter;
    private UsedTiles usedTiles;

    @Before
    public void setUp() {
        usedTiles = new UsedTiles();
        bag = new Bag(123, usedTiles);
        tableCenter = new TableCenter();
        factory = new Factory(bag, tableCenter);
    }

    @Test
    public void test_factory() {
        assertEquals("Factory should be empty when created.", 0, factory.tiles.size());
        factory.startNewRound();
        assertEquals("Factory should be filled with tiles when a new round is started.", 4, factory.tiles.size());
        assertEquals("Factory should be pseudorandom with its seed.", "IBBL", factory.state());
        List<Tile> takenTiles = factory.take(1);
        List<Tile> takenTilesTest = new ArrayList<>();
        takenTilesTest.add(Tile.BLUE);
        takenTilesTest.add(Tile.BLUE);
        assertEquals("Correct tiles should be taken from the factory.", takenTilesTest, takenTiles);
        assertEquals("Tiles that were not taken should be put into table center.", "SIL", tableCenter.state());
        System.out.println(factory.state());
        assertEquals("Factory should be empty after taking tiles.", "", factory.state());
        factory.startNewRound();
        assertEquals("Factory should be filled with tiles when a new round is started", 4, factory.tiles.size());
        assertEquals("Factory should be pseudorandom with its seed.", "LRBB", factory.state());
    }

}
