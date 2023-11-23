package sk.uniba.fmph.dcs;

public class BoardsObserver implements Observer {
    private final Game game;

    public BoardsObserver(Game game) {
        this.game = game;
    }

    @Override
    public void update() {
        String state = game.getState().get(Board.class);
        if (state != null) {
            System.out.println(state);
        }
    }
}
