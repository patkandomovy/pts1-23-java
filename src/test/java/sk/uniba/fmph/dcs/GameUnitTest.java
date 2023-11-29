package sk.uniba.fmph.dcs;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameUnitTest {
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
        //Testing for invalid arguments in method take.
        assertFalse("The first player in this round should be correct.", game.take(1, 1, 1, 1));
        assertFalse("The first player in this round should be correct.", game.take(2, 1, 1, 1));
        assertFalse("The source ID should be correct.", game.take(0, 7, 1, 1));
        assertFalse("The index should be correct.", game.take(0, 1, 5, 1));
        assertFalse("The destination ID should be correct.", game.take(0, 1, 1, 5));

        //Testing for states.
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

        assertTrue("Game should continue.", game.take(0, 1, 0, 0));
        assertEquals("Board should be correct after making a turn",
                "Pattern Line 1: I\n" +
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

        assertEquals("Board should be correct after making a turn.",
                "Pattern Line 1: I\n" +
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
        assertEquals("Bag should be correct after making a turn.", "Bag: R G G B G I L R I L I L L I G I R L L L B I L L G R R I G I G G B R B B R I I L L R I B B G R I R R B L L G G R G R B B G I G G L B G L I L G L I R I R B B B I\n", game.getState().get(Bag.class));
        assertEquals("Table area should be correct after making a turn.",
                "TableCenter: SBBL\n" +
                        "Factory: \n" +
                        "Factory: LRBB\n" +
                        "Factory: IRRL\n" +
                        "Factory: IRRB\n" +
                        "Factory: GGBG\n", game.getState().get(TableArea.class));
    }

}
