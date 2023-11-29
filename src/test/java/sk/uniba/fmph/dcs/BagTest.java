package sk.uniba.fmph.dcs;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BagTest {
    private Bag bag;
    private UsedTiles usedTiles;

    @Before
    public void setUp() {
        usedTiles = new UsedTiles();
        bag = new Bag(123, usedTiles);
    }

    @Test
    public void test_bag() {
        assertEquals("Bag should contain pseudorandomized tiles.", "Bag: I B B L L R B B I R R L I R R B G G B G R G G B G I L R I L I L L I G I R L L L B I L L G R R I G I G G B R B B R I I L L R I B B G R I R R B L L G G R G R B B G I G G L B G L I L G L I R I R B B B I\n", bag.state());
        assertEquals("Used tiles should be empty", "", usedTiles.state());
        List<Tile> takenTilesTest = new ArrayList<>();
        takenTilesTest.add(Tile.YELLOW);
        takenTilesTest.add(Tile.BLUE);
        takenTilesTest.add(Tile.BLUE);
        takenTilesTest.add(Tile.BLACK);
        assertEquals("Taken tiles from the bag should be correct.", takenTilesTest, bag.take(4));
        assertEquals("Bag should remove the tiles that had been taken.", "Bag: L R B B I R R L I R R B G G B G R G G B G I L R I L I L L I G I R L L L B I L L G R R I G I G G B R B B R I I L L R I B B G R I R R B L L G G R G R B B G I G G L B G L I L G L I R I R B B B I\n", bag.state());

        //In real world scenario, used takenTiles would contain all 100 tiles. We will test it with only 4 tiles so that our tests are not so long.
        usedTiles.give(takenTilesTest);
        bag.take(96);
        assertEquals("Bag should be refilled and pseudorandomized after taking all tiles.", "Bag: L I B R\n", bag.state());
    }
}
