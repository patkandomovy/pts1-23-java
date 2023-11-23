package sk.uniba.fmph.dcs;

public class BagObserver implements Observer {
    private final Game game;

    public BagObserver(Game game) {
        this.game = game;
    }

    @Override
    public void update() {
        String state = game.getState().get(Bag.class);
        if (state != null) {
            System.out.println(state);
        }
    }
}
