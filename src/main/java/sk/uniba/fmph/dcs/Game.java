package sk.uniba.fmph.dcs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game extends Observable implements GameInterface {
    private final Map<Class<?>, String> state = new HashMap<Class<?>, String>() {{
       put(Bag.class, "not initialized");
       put(TableArea.class, "not initialized");
       put(Board.class, "not initialized");
    }};
    private Bag bag;
    private TableArea tableArea;
    private List<Board> boards;

    public Game(int numPlayers) {
        // TODO: konstruktor - vytvori vsetky komponenty na zaklade poctu hracov a inicializuje mapu 'state'
    }

    @Override
    public boolean take(int playerId, int sourceId, int idx, int destinationIdx) {
        // TODO:
        return false;
    }

    public Map<Class<?>, String> getState() {
        return state;
    }
}
