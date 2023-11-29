package sk.uniba.fmph.dcs;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TableCenterTest {
    private TableCenter tableCenter;

    @Before
    public void setUp() {
        tableCenter = new TableCenter();
    }

    @Test
    public void test_table_center() {
        List<Tile> tiles = new ArrayList<>();
        tiles.add(Tile.BLUE);
        tiles.add(Tile.BLACK);

        assertEquals("Table center should contain only starting player tile", "S", tableCenter.state());
        tableCenter.add(tiles);
        assertEquals("Table center should contain added tiles and starting player tile", "SBL", tableCenter.state());
        tableCenter.take(0);
        assertEquals("Table center should contain all but first tile.", "BL", tableCenter.state());
        tableCenter.take(0);
        assertEquals("Table center should contain all but first tile.", "L", tableCenter.state());
        tableCenter.take(0);
        assertEquals("Table center should be empty.", "", tableCenter.state());

        tableCenter.startNewRound();
        assertEquals("Table center should contain starting player tile.", "S", tableCenter.state());

    }
}
