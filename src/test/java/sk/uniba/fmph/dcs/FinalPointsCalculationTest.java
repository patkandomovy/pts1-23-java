package sk.uniba.fmph.dcs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class FinalPointsCalculationTest {
    @Test
    public void test_f_p_c() {
        List<List<Optional<Tile>>> wall = new ArrayList<>();
        List<Optional<Tile>> line1 = new ArrayList<>();
        line1.add(Optional.empty());
        line1.add(Optional.empty());
        line1.add(Optional.empty());
        line1.add(Optional.empty());
        line1.add(Optional.empty());

        List<Optional<Tile>> line2 = new ArrayList<>();
        line2.add(Optional.empty());
        line2.add(Optional.empty());
        line2.add(Optional.empty());
        line2.add(Optional.empty());
        line2.add(Optional.empty());

        List<Optional<Tile>> line3 = new ArrayList<>();
        line3.add(Optional.empty());
        line3.add(Optional.empty());
        line3.add(Optional.empty());
        line3.add(Optional.empty());
        line3.add(Optional.empty());

        List<Optional<Tile>> line4 = new ArrayList<>();
        line4.add(Optional.empty());
        line4.add(Optional.empty());
        line4.add(Optional.empty());
        line4.add(Optional.empty());
        line4.add(Optional.empty());

        List<Optional<Tile>> line5 = new ArrayList<>();
        line5.add(Optional.empty());
        line5.add(Optional.empty());
        line5.add(Optional.empty());
        line5.add(Optional.empty());
        line5.add(Optional.empty());

        wall.add(line1);
        wall.add(line2);
        wall.add(line3);
        wall.add(line4);
        wall.add(line5);

        assertEquals("Zero points should be returned for an empty wall.", new Points(0), FinalPointsCalculation.getPoints(wall));
        line1.set(0, Optional.of(Tile.BLUE));
        line1.set(1, Optional.of(Tile.RED));
        line1.set(2, Optional.of(Tile.YELLOW));
        line1.set(3, Optional.of(Tile.GREEN));
        line1.set(4, Optional.of(Tile.BLACK));
        assertEquals("Two points should be returned for a horizontal line.", new Points(2), FinalPointsCalculation.getPoints(wall));

        line2.set(0, Optional.of(Tile.RED));
        line3.set(0, Optional.of(Tile.YELLOW));
        line4.set(0, Optional.of(Tile.GREEN));
        line5.set(0, Optional.of(Tile.BLACK));
        assertEquals("Nine points should be returned for a horizontal line with a vertical line(2 + 7)", new Points(9), FinalPointsCalculation.getPoints(wall));

        line2.set(1, Optional.of(Tile.BLUE));
        line3.set(2, Optional.of(Tile.BLUE));
        line4.set(3, Optional.of(Tile.BLUE));
        line5.set(4, Optional.of(Tile.BLUE));
        assertEquals("19 points should be returned for a horizontal line with a vertical line and completed colors(2 + 7 + 10)", new Points(19), FinalPointsCalculation.getPoints(wall));
    }
}
