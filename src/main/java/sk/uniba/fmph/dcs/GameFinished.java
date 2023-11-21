package sk.uniba.fmph.dcs;

import java.util.List;
import java.util.Optional;

import static sk.uniba.fmph.dcs.FinalPointsCalculation.isCompleted;

public class GameFinished {
    public static FinishRoundResult gameFinished(List<List<Optional<Tile>>> wall) {
        for(List<Optional<Tile>> row : wall) {
            if (isCompleted(row)) {
                return FinishRoundResult.GAME_FINISHED;
            }
        }

        return FinishRoundResult.NORMAL;
    }
}
