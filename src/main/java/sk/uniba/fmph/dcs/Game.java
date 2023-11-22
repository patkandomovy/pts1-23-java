package sk.uniba.fmph.dcs;

import java.util.List;
import java.util.Map;

public class Game extends Observable implements GameInterface {
    private Map<String, String> componentsStates;
    private Bag bag;
    private TableArea tableArea;
    private List<Board> boards;
    // TODO: pridat zoznam stavov vsetkych komponentov

    @Override
    public boolean take(int playerId, int sourceId, int idx, int destinationIdx) {
        // TODO:
        return false;
    }
}
