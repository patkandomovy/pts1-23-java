package sk.uniba.fmph.dcs;

import java.util.List;

public class BoardsObserver implements Observer {
    private final Game game;

    public BoardsObserver(Game game) {
        this.game = game;
    }

    @Override
    public void update() {
        List<Board> boards = game.getBoards();
        for (int i = 0; i < boards.size(); i++) {
            String state = boards.get(i).state();
            System.out.println("Board " + i + ": \n" + state);
        }
    }
}
