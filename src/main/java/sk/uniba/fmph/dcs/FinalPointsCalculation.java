package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FinalPointsCalculation {

    public static Points getPoints(List<List<Optional<Tile>>> wall) {
        return new Points(calculatePointsForRow(wall) + calculatePointsForColumn(wall) + calculatePointsForColours(wall));
    }

    private static int calculatePointsForRow(List<List<Optional<Tile>>> wall) {
        int points = 0;

        for (List<Optional<Tile>> row : wall) {
            if (isCompleted(row)) {
                points += 2;
            }
        }

        return points;
    }

    private static int calculatePointsForColumn(List<List<Optional<Tile>>> wall) {
        int points = 0;
        List<List<Optional<Tile>>> columns = new ArrayList<>();

        for (int i = 0; i < wall.get(0).size(); i++) {
            columns.add(new ArrayList<>());
        }

        for (List<Optional<Tile>> row : wall) {
            for (int i = 0; i < row.size(); i++) {
                columns.get(i).add(row.get(i));
            }
        }

        for (List<Optional<Tile>> column : columns) {
            if (isCompleted(column)) {
                points += 7;
            }
        }

        return points;
    }

    private static int calculatePointsForColours(List<List<Optional<Tile>>> wall) {
        int points = 0;
        List<List<Optional<Tile>>> sameColours = new ArrayList<>();

        for (int i = 0; i < Tile.values().length; i++) {
            sameColours.add(new ArrayList<>());
        }

        for (int i = 0; i < Tile.values().length - 1; i++) {
            for (int j = 0; j < Tile.values().length - 1; j++) {
                sameColours.get((j + i) % Tile.values().length).add(wall.get(i).get(j));
            }
        }

        for (List<Optional<Tile>> colour : sameColours) {
            if (isCompleted(colour)) {
                points += 10;
            }
        }

        return points;
    }

    public static boolean isCompleted(List<Optional<Tile>> set) {
        for (Optional<Tile> element : set) {
            if (element.isEmpty()) {
                return false;
            }
        }

        return true;
    }
}
