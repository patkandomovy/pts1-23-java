package sk.uniba.fmph.dcs;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class Board {
    private Points points;
    private final WallLine rightSide;
    private final List<PatternLine> leftSide = new ArrayList<>();
    private final Floor floor;
    private final List<List<Optional<Tile>>> wall = new ArrayList<>();

    public Board(UsedTilesGiveInterface usedTiles) {
        points = new Points(0);

        ArrayList<WallLine> wall = new ArrayList<>();

        ArrayList<Points> pointPattern = new ArrayList<>();
        pointPattern.add(new Points(-1));
        pointPattern.add(new Points(-1));
        pointPattern.add(new Points(-2));
        pointPattern.add(new Points(-2));
        pointPattern.add(new Points(-2));
        pointPattern.add(new Points(-3));
        pointPattern.add(new Points(-3));

        floor = new Floor(new UsedTiles(), pointPattern);

        for (int i = 1; i <= 5; i++) {
            wall.add(new WallLine(i - 1));
            leftSide.add(new PatternLine(i - 1, usedTiles, wall.get(i - 1), floor));
        }
        rightSide = wall.get(0);

        for (int i = 0; i < wall.size(); i++) {
            if (i == 0) {
                wall.get(i).setLineUp(null);
                wall.get(i).setLineDown(wall.get(i + 1));
                continue;
            }

            if (i == wall.size() - 1) {
                wall.get(i).setLineDown(null);
                wall.get(i).setLineUp(wall.get(i - 1));
                continue;
            }

            wall.get(i).setLineUp(wall.get(i - 1));
            wall.get(i).setLineDown(wall.get(i + 1));
        }
    }


    public void put(int destinationIdx, List<Tile> tiles) {
        leftSide.get(destinationIdx).put(tiles);
    }

    public FinishRoundResult finishRound() {
        int sum = 0;

        for (PatternLine patternLine : leftSide) {
            sum += patternLine.finishRound().getValue();
        }

        points = new Points(points.getValue() + sum + floor.finishRound().getValue());

        WallLine line = rightSide;

        while (line.getLineDown() != null) {
            wall.add(line.getTiles());
            line = line.getLineDown();
        }

        return GameFinished.gameFinished(wall);
    }

    public void endGame() {
        points = new Points(points.getValue() + FinalPointsCalculation.getPoints(wall).getValue());
    }

    public Points getPoints() {
        return points;
    }

    public String state() {
        StringBuffer str = new StringBuffer();

        for (int i = 0; i < leftSide.size(); i++) {
            str.append("Pattern Line " + (i + 1) + ": " + leftSide.get(i).state());
        }
        str.append("\n");

        WallLine line = rightSide;
        int counter = 1;

        for (int i = 0; i < 5; i++) {
            str.append("Wall Line " + counter + ": " + line.state());
            line = line.getLineDown();
            counter++;
        }
        str.append("\n");

        str.append(floor.state());
        str.append("\n");

        return str.toString();
    }
}
