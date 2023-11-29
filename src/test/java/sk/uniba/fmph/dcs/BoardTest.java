package sk.uniba.fmph.dcs;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BoardTest {
    private Board board;
    private UsedTilesGiveInterface usedTiles;

    @Before
    public void setUp() {
        usedTiles = new UsedTiles();
        board = new Board(usedTiles);
    }

    @Test
    public void test_board() {
        assertEquals("Board should be properly initialized.",
                "Pattern Line 1: _\n" +
                        "Pattern Line 2: __\n" +
                        "Pattern Line 3: ___\n" +
                        "Pattern Line 4: ____\n" +
                        "Pattern Line 5: _____\n" +
                        "\n" +
                        "Wall Line 1: B: empty\n" +
                        "I: empty\n" +
                        "R: empty\n" +
                        "L: empty\n" +
                        "G: empty\n" +
                        "Wall Line 2: G: empty\n" +
                        "B: empty\n" +
                        "I: empty\n" +
                        "R: empty\n" +
                        "L: empty\n" +
                        "Wall Line 3: L: empty\n" +
                        "G: empty\n" +
                        "B: empty\n" +
                        "I: empty\n" +
                        "R: empty\n" +
                        "Wall Line 4: R: empty\n" +
                        "L: empty\n" +
                        "G: empty\n" +
                        "B: empty\n" +
                        "I: empty\n" +
                        "Wall Line 5: I: empty\n" +
                        "R: empty\n" +
                        "L: empty\n" +
                        "G: empty\n" +
                        "B: empty\n\n\n", board.state());

        ArrayList<Tile> tiles1 = new ArrayList<>();
        tiles1.add(Tile.RED);
        tiles1.add(Tile.RED);
        tiles1.add(Tile.RED);
        board.put(1, tiles1);
        assertEquals("Tiles should be put on the desired pattern line if it is possible. Remaining tiles should be put on floor.",
                "Pattern Line 1: _\n" +
                        "Pattern Line 2: RR\n" +
                        "Pattern Line 3: ___\n" +
                        "Pattern Line 4: ____\n" +
                        "Pattern Line 5: _____\n" +
                        "\n" +
                        "Wall Line 1: B: empty\n" +
                        "I: empty\n" +
                        "R: empty\n" +
                        "L: empty\n" +
                        "G: empty\n" +
                        "Wall Line 2: G: empty\n" +
                        "B: empty\n" +
                        "I: empty\n" +
                        "R: empty\n" +
                        "L: empty\n" +
                        "Wall Line 3: L: empty\n" +
                        "G: empty\n" +
                        "B: empty\n" +
                        "I: empty\n" +
                        "R: empty\n" +
                        "Wall Line 4: R: empty\n" +
                        "L: empty\n" +
                        "G: empty\n" +
                        "B: empty\n" +
                        "I: empty\n" +
                        "Wall Line 5: I: empty\n" +
                        "R: empty\n" +
                        "L: empty\n" +
                        "G: empty\n" +
                        "B: empty\n" +
                        "\n" +
                        "R\n", board.state());

        ArrayList<Tile> tiles2 = new ArrayList<>();
        tiles2.add(Tile.YELLOW);
        board.put(0, tiles2);

        ArrayList<Tile> tiles3 = new ArrayList<>();
        tiles3.add(Tile.BLACK);
        tiles3.add(Tile.BLACK);
        tiles3.add(Tile.BLACK);
        board.put(2, tiles3);

        ArrayList<Tile> tiles4 = new ArrayList<>();
        tiles4.add(Tile.BLUE);
        board.put(2, tiles4);

        ArrayList<Tile> tiles5 = new ArrayList<>();
        tiles5.add(Tile.BLUE);
        tiles5.add(Tile.BLUE);
        tiles5.add(Tile.BLUE);
        board.put(3, tiles5);

        assertEquals("Tiles should be put on the desired lane if it is possible. Remaining Tiles should be put on the floor.",
                "Pattern Line 1: I\n" +
                        "Pattern Line 2: RR\n" +
                        "Pattern Line 3: LLL\n" +
                        "Pattern Line 4: _BBB\n" +
                        "Pattern Line 5: _____\n" +
                        "\n" +
                        "Wall Line 1: B: empty\n" +
                        "I: empty\n" +
                        "R: empty\n" +
                        "L: empty\n" +
                        "G: empty\n" +
                        "Wall Line 2: G: empty\n" +
                        "B: empty\n" +
                        "I: empty\n" +
                        "R: empty\n" +
                        "L: empty\n" +
                        "Wall Line 3: L: empty\n" +
                        "G: empty\n" +
                        "B: empty\n" +
                        "I: empty\n" +
                        "R: empty\n" +
                        "Wall Line 4: R: empty\n" +
                        "L: empty\n" +
                        "G: empty\n" +
                        "B: empty\n" +
                        "I: empty\n" +
                        "Wall Line 5: I: empty\n" +
                        "R: empty\n" +
                        "L: empty\n" +
                        "G: empty\n" +
                        "B: empty\n" +
                        "\n" +
                        "RB\n", board.state());

        board.finishRound();
        assertEquals("Board should finish the round properly.",
                "Pattern Line 1: _\n" +
                        "Pattern Line 2: __\n" +
                        "Pattern Line 3: ___\n" +
                        "Pattern Line 4: _BBB\n" +
                        "Pattern Line 5: _____\n" +
                        "\n" +
                        "Wall Line 1: B: empty\n" +
                        "I: placed\n" +
                        "R: empty\n" +
                        "L: empty\n" +
                        "G: empty\n" +
                        "Wall Line 2: G: empty\n" +
                        "B: empty\n" +
                        "I: empty\n" +
                        "R: placed\n" +
                        "L: empty\n" +
                        "Wall Line 3: L: placed\n" +
                        "G: empty\n" +
                        "B: empty\n" +
                        "I: empty\n" +
                        "R: empty\n" +
                        "Wall Line 4: R: empty\n" +
                        "L: empty\n" +
                        "G: empty\n" +
                        "B: empty\n" +
                        "I: empty\n" +
                        "Wall Line 5: I: empty\n" +
                        "R: empty\n" +
                        "L: empty\n" +
                        "G: empty\n" +
                        "B: empty\n\n\n", board.state());

        assertEquals("Points should be calculated properly.", 1, board.getPoints().getValue());

        //We will now put tiles on the board until the game has finished, while testing board's points.
        ArrayList<Tile> tiles6 = new ArrayList<>();
        tiles6.add(Tile.BLUE);
        board.put(0, tiles6);
        board.finishRound();
        assertEquals("Points should be calculated properly.", 3, board.getPoints().getValue());

        ArrayList<Tile> tiles7 = new ArrayList<>();
        tiles7.add(Tile.GREEN);
        tiles7.add(Tile.GREEN);
        board.put(1, tiles7);
        board.finishRound();
        assertEquals("Points should be calculated properly.", 6, board.getPoints().getValue());

        ArrayList<Tile> tiles8 = new ArrayList<>();
        tiles8.add(Tile.RED);
        board.put(0, tiles8);
        board.finishRound();
        assertEquals("Points should be calculated properly.", 9, board.getPoints().getValue());

        ArrayList<Tile> tiles9 = new ArrayList<>();
        tiles9.add(Tile.BLACK);
        board.put(0, tiles9);
        board.finishRound();
        assertEquals("Points should be calculated properly.", 15, board.getPoints().getValue());

        ArrayList<Tile> tiles10 = new ArrayList<>();
        tiles10.add(Tile.GREEN);
        board.put(0, tiles10);
        FinishRoundResult result = board.finishRound();
        assertEquals("Points should be calculated properly.", 20, board.getPoints().getValue());

        assertEquals("Game should be finished.", FinishRoundResult.GAME_FINISHED, result);
        board.endGame();
        assertEquals("Final points should be calculated properly.", 22, board.getPoints().getValue());
    }
}
