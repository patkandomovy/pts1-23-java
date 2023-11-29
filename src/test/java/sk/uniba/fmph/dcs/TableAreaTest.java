package sk.uniba.fmph.dcs;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TableAreaTest {
    private TableArea tableArea;
    private Bag bag;
    private TableCenter tableCenter;
    private Factory factory1;
    private Factory factory2;
    private UsedTiles usedTiles;

    @Before
    public void setUp() {
        usedTiles = new UsedTiles();
        bag = new Bag(123, usedTiles);
        tableCenter = new TableCenter();
        factory1 = new Factory(bag, tableCenter);
        factory2 = new Factory(bag, tableCenter);
        tableArea = new TableArea(tableCenter);
        tableArea.addFactories(Arrays.asList(factory1, factory2));
    }

    @Test
    public void test_table_area() {
        tableArea.startNewRound();
        assertEquals("Table Area should be correctly initialized.", "TableCenter: S\n" +
                "Factory: IBBL\n" +
                "Factory: LRBB\n", tableArea.state());
        assertFalse("Round is not finished.", tableArea.isRoundEnd());
        List<Tile> takenTiles1 = new ArrayList<>();
        takenTiles1.add(Tile.BLUE);
        takenTiles1.add(Tile.BLUE);
        assertEquals("Correct tiles should be taken from the table source.", takenTiles1, tableArea.take(1, 1));
        assertEquals("Factory should be empty after taking tiles from it.", "", factory1.state());

        List<Tile> takenTiles2 = new ArrayList<>();
        takenTiles2.add(Tile.BLUE);
        takenTiles2.add(Tile.BLUE);
        assertEquals("Correct tiles should be taken from the table source.", takenTiles2, tableArea.take(2, 2));
        assertEquals("Factory should be empty after taking tiles from it.", "", factory2.state());

        assertEquals("Table center should contain the remaining tiles.", "SILLR", tableCenter.state());

        List<Tile> takenTiles3 = new ArrayList<>();
        takenTiles3.add(Tile.STARTING_PLAYER);
        takenTiles3.add(Tile.BLACK);
        takenTiles3.add(Tile.BLACK);
        assertEquals("Correct tiles should be taken from the table source. The first player to take tiles from the table center also takes the starting player tile.",
                takenTiles3,
                tableArea.take(0, 2));
        assertEquals("Table center should contain the remaining tiles.", "IR", tableCenter.state());

        List<Tile> takenTiles4 = new ArrayList<>();
        takenTiles4.add(Tile.YELLOW);
        assertEquals("Correct tiles should be taken from the table source.", takenTiles4, tableArea.take(0, 0));

        tableArea.take(0, 0);
        assertEquals("All table sources should be empty.", "TableCenter: \n" +
                "Factory: \n" +
                "Factory: \n", tableArea.state());


        tableArea.startNewRound();
        assertEquals("New round should be correctly initialized.", "TableCenter: S\n" +
                "Factory: IRRL\n" +
                "Factory: IRRB\n", tableArea.state());
    }

}
