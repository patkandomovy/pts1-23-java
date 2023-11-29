package sk.uniba.fmph.dcs;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class WallLineTest {
    private WallLine wallLine;

    @Before
    public void setUp() {
        wallLine = new WallLine(null, null, 4);
    }

    @Test
    public void test_wall_line() {
        List<Optional<Tile>> line = new ArrayList<>();
        line.add(Optional.empty());
        line.add(Optional.empty());
        line.add(Optional.empty());
        line.add(Optional.empty());
        line.add(Optional.empty());

        assertEquals("Wall line should be empty.",
                "I: empty\n" +
                        "R: empty\n" +
                        "L: empty\n" +
                        "G: empty\n" +
                        "B: empty\n", wallLine.state());

        assertEquals("No tiles should be returned from an empty wall.", line, wallLine.getTiles());
        assertTrue("We should be able to place a tile.", wallLine.canPutTile(Tile.YELLOW));
        assertEquals("One tile should be worth 1 point.", wallLine.putTile(Tile.YELLOW), new Points(1));
        assertFalse("We should not be able to place a tile that had already been placed on wall.", wallLine.canPutTile(Tile.YELLOW));

        assertEquals("Wall line should contain a yellow tile.", "I: placed\n" +
                "R: empty\n" +
                "L: empty\n" +
                "G: empty\n" +
                "B: empty\n", wallLine.state());

        line.set(0, Optional.of(Tile.YELLOW));
        assertEquals("Yellow tile should be returned from our wall.", line, wallLine.getTiles());


    }
}
