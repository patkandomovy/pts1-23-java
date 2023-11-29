package sk.uniba.fmph.dcs;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    private Game game;
    private Observer boardsObserver;
    private Observer tableAreaObserver;
    private Observer bagObserver;

    @Before
    public void setUp() {
        game = new Game(2);
        tableAreaObserver = new TableAreaObserver(game);
        bagObserver = new BagObserver(game);
        boardsObserver = new BoardsObserver(game);
    }

    @Test
    public void test_game() {
        //Uncomment for game progress
        /*game.registerObserver(bagObserver);
        game.registerObserver(tableAreaObserver);
        game.registerObserver(boardsObserver);
        game.notifyObservers();*/


        //Testing for invalid arguments in method take.
        assertFalse("The first player in this round should be correct.", game.take(1, 1, 1, 1));
        assertFalse("The first player in this round should be correct.", game.take(2, 1, 1, 1));
        assertFalse("The source ID should be correct.", game.take(0, 7, 1, 1));
        assertFalse("The index should be correct.", game.take(0, 1, 5, 1));
        assertFalse("The destination ID should be correct.", game.take(0, 1, 1, 5));


        assertEquals("Bag should be initialized properly.", "Bag: R G G B G I L R I L I L L I G I R L L L B I L L G R R I G I G G B R B B R I I L L R I B B G R I R R B L L G G R G R B B G I G G L B G L I L G L I R I R B B B I\n", game.getState().get(Bag.class));
        assertEquals("Board should be initialized properly.",
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
                        "B: empty\n" +
                        "\n\n", game.getBoards().get(0).state());

        assertEquals("Board should be initialized properly.",
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
                        "B: empty\n" +
                        "\n\n", game.getBoards().get(1).state());

        assertEquals("Table area should be initialized properly.",
                "TableCenter: S\n" +
                        "Factory: IBBL\n" +
                        "Factory: LRBB\n" +
                        "Factory: IRRL\n" +
                        "Factory: IRRB\n" +
                        "Factory: GGBG\n", game.getState().get(TableArea.class));

        //Playing the game.
        assertTrue("The game should continue.", game.take(0, 1, 1, 1));
        assertTrue("The game should continue.", game.take(1, 5, 1, 2));
        assertTrue("The game should continue.", game.take(0, 4, 1, 2));
        assertTrue("The game should continue.", game.take(1, 2, 0, 0));
        assertTrue("The game should continue.", game.take(0, 3, 2, 2));
        assertTrue("The game should continue.", game.take(1, 0, 3, 3));
        assertTrue("The game should continue.", game.take(0, 0, 0, 3));
        assertTrue("The game should continue.", game.take(1, 0, 0, 1));
        assertTrue("The game should continue.", game.take(0, 0, 0, 3));


        assertFalse("The first player in this round should be correct.", game.take(0, 0, 0, 0));
        assertTrue("The game should continue.", game.take(1, 5, 1, 4));
        assertTrue("The game should continue.", game.take(0, 1, 1, 2));
        assertTrue("The game should continue.", game.take(1, 3, 1, 4));
        assertTrue("The game should continue.", game.take(0, 2, 0, 2));
        assertTrue("The game should continue.", game.take(1, 0, 4, 2));
        assertTrue("The game should continue.", game.take(0, 4, 1, 1));
        assertTrue("The corresponding wall line should not contain similar color. Tiles are therefore put on floor.", game.take(1, 0, 3, 1));
        assertTrue("The game should continue.", game.take(0, 0, 0, 4));
        assertTrue("The game should continue.", game.take(1, 0, 1, 0));
        assertTrue("The game should continue.", game.take(0, 0, 0, 0));

        assertFalse("The first player in this round should be correct.", game.take(0, 0, 0, 0));
        assertTrue("The game should continue.", game.take(1, 4, 0, 2));
        assertTrue("The game should continue.", game.take(0, 1, 2, 1));
        assertTrue("The game should continue.", game.take(1, 3, 0, 1));
        assertTrue("The game should continue.", game.take(0, 2, 1, 4));
        assertTrue("The game should continue.", game.take(1, 5, 0, 0));
        assertTrue("The game should continue.", game.take(0, 0, 9, 0));
        assertTrue("The game should continue.", game.take(1, 0, 3, 4));
        assertTrue("The game should continue.", game.take(0, 0, 1, 2));
        assertTrue("The game should continue.", game.take(1, 0, 1, 3));
        assertTrue("The corresponding wall line should not contain similar color. Tiles are therefore put on floor.", game.take(0, 0, 0, 3));

        assertFalse("The first player in this round should be correct.", game.take(1, 0, 0, 0));
        assertTrue("The game should continue.", game.take(0, 5, 2, 2));
        assertTrue("The game should continue.", game.take(1, 3, 0, 2));
        assertTrue("The game should continue.", game.take(0, 4, 1, 1));
        assertTrue("The game should continue.", game.take(1, 2, 2, 2));
        assertTrue("The game should continue.", game.take(0, 1, 2, 3));
        assertTrue("The game should continue.", game.take(1, 0, 9, 0));
        assertTrue("The game should continue.", game.take(0, 0, 3, 4));
        assertTrue("The game should continue.", game.take(1, 0, 1, 1));
        assertTrue("The game should continue.", game.take(0, 0, 0, 0));
        assertTrue("The game should continue.", game.take(1, 0, 0, 4));

        assertFalse("The first player in this round should be correct.", game.take(0, 0, 0, 0));
        assertTrue("The game should continue.", game.take(1, 2, 0, 2));
        assertTrue("The game should continue.", game.take(0, 4, 1, 1));
        assertTrue("The game should continue.", game.take(1, 5, 1, 0));
        assertTrue("The game should continue.", game.take(0, 1, 1, 0));
        assertTrue("The game should continue.", game.take(1, 3, 2, 3));
        assertTrue("The game should continue.", game.take(0, 0, 7, 4));
        assertTrue("The game should continue.", game.take(1, 0, 2, 1));
        assertTrue("The game should continue.", game.take(0, 0, 1, 3));
        assertTrue("The game should continue.", game.take(1, 0, 0, 4));
        assertTrue("The corresponding wall line should not contain similar color. Tiles are therefore put on floor.", game.take(0, 0, 0, 2));

        assertFalse("The game should be finished.", game.take(0, 0, 0, 0));
        assertEquals("The points should be correct.", 41, game.getBoards().get(0).getPoints().getValue());
        assertEquals("The points should be correct.", 42, game.getBoards().get(1).getPoints().getValue());
    }

}
