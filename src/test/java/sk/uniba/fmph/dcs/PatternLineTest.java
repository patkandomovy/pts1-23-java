package sk.uniba.fmph.dcs;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class PatternLineTest {
    private UsedTiles usedTiles;
    private PatternLine patternLine;
    private WallLine wallLine;
    private Floor floor;

    @Before
    public void setUp() {
        usedTiles = new UsedTiles();
        ArrayList<Points> pointPattern = new ArrayList<Points>();
        pointPattern.add(new Points(1));
        pointPattern.add(new Points(2));
        pointPattern.add(new Points(2));
        floor = new Floor(usedTiles, pointPattern);
        wallLine = new WallLine(1);
        patternLine = new PatternLine(1, usedTiles, wallLine, floor);
    }

    @Test
    public void test_pattern_line() {
        assertEquals("PatternLine should be empty when created.", "__\n", patternLine.state());
        assertEquals("PatternLine should have its desired capacity.", 3, patternLine.state().length());
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        tiles.add(Tile.RED);
        patternLine.put(tiles);
        assertEquals("PatternLine should contain a tile.", "_R\n", patternLine.state());

        ArrayList<Tile> tiles2 = new ArrayList<Tile>();
        tiles2.add(Tile.BLACK);
        patternLine.put(tiles2);
        assertEquals("PatternLine should not contain tiles with different colors", "_R\n", patternLine.state());
        assertEquals("Different color should be put on floor", "L", floor.state());

        ArrayList<Tile> tiles3 = new ArrayList<Tile>();
        tiles3.add(Tile.RED);
        tiles3.add(Tile.RED);
        tiles3.add(Tile.RED);
        patternLine.put(tiles3);
        assertEquals("PatternLine should be filled with red tiles.", "RR\n", patternLine.state());
        assertEquals("Remaining tiles should be put on floor.", "LRR", floor.state());

        ArrayList<Tile> tiles4 = new ArrayList<Tile>();
        tiles4.add(Tile.BLUE);
        tiles4.add(Tile.BLUE);
        patternLine.put(tiles4);
        assertEquals("PatternLine should be filled with red tiles.", "RR\n", patternLine.state());
        assertEquals("Remaining tiles should be put on floor.", "LRRBB", floor.state());

        patternLine.finishRound();
        floor.finishRound();
        assertEquals("Filled PatternLine should be empty after a finished round", "__\n", patternLine.state());
        assertEquals("Used tiles should contain tiles that we used", "RRLRRBB", usedTiles.state());

        ArrayList<Tile> tiles5 = new ArrayList<>();
        tiles5.add(Tile.BLACK);
        patternLine.put(tiles5);
        patternLine.finishRound();
        assertEquals("Not filled Pattern line should remain the same after a finished round", "_L\n", patternLine.state());
    }
}
