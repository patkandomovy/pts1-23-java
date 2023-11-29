package sk.uniba.fmph.dcs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class GameFinishedTest {
    @Test
    public void test_game_finish(){
        List<List<Optional<Tile>>> wall = new ArrayList<>();
        List<Optional<Tile>> line = new ArrayList<>();
        line.add(Optional.empty());
        line.add(Optional.of(Tile.BLACK));
        line.add(Optional.of(Tile.BLUE));
        line.add(Optional.of(Tile.GREEN));
        line.add(Optional.of(Tile.YELLOW));
        wall.add(line);
        assertEquals("Game should not be finished.", FinishRoundResult.NORMAL, GameFinished.gameFinished(wall));


        List<Optional<Tile>> line2 = new ArrayList<>();
        line2.add(Optional.of(Tile.RED));
        line2.add(Optional.of(Tile.BLACK));
        line2.add(Optional.of(Tile.BLUE));
        line2.add(Optional.of(Tile.GREEN));
        line2.add(Optional.of(Tile.YELLOW));
        wall.add(line2);
        assertEquals("Game should be finished.", FinishRoundResult.GAME_FINISHED, GameFinished.gameFinished(wall));
    }
}
