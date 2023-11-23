package sk.uniba.fmph.dcs;

public class TableAreaObserver implements Observer {
    private final Game game;

    public TableAreaObserver(Game game) {
        this.game = game;
    }

    @Override
    public void update() {
        String state = game.getState().get(TableArea.class);
        if (state != null) {
            System.out.println(state);
        }
    }
}
