package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game extends Observable implements GameInterface {
    private final Map<Class<?>, String> state = new HashMap<Class<?>, String>() {{
        put(Bag.class, "not initialized");
        put(TableArea.class, "not initialized");
        put(Board.class, "not initialized");
    }};
    private final Bag bag;
    private final TableCenter tableCenter;
    private final TableArea tableArea;
    private final List<Board> boards;
    private final UsedTiles usedTiles;
    private final List<TileSource> factories;
    private final int numPlayers;
    private int turn;
    private int newRoundFirstTurn;
    private boolean gameFinished;
    private final List<Points> finalPointsList;
    private int numOfFactories;


    public Game(int numPlayers) {
        this.usedTiles = new UsedTiles();
        this.boards = new ArrayList<>();
        this.tableCenter = new TableCenter();
        this.numPlayers = numPlayers;
        this.tableArea = new TableArea(tableCenter);
        //Let the seed be 123 for better testing
        this.bag = new Bag(123, usedTiles);
        this.factories = new ArrayList<>();
        this.turn = 0;
        this.gameFinished = false;
        this.finalPointsList = new ArrayList<>();

        if (numPlayers == 2) {
            for (int i = 0; i < 5; i++) {
                factories.add(new Factory(bag, tableCenter));
                numOfFactories = 5;
            }
        } else if (numPlayers == 3) {
            for (int i = 0; i < 7; i++) {
                factories.add(new Factory(bag, tableCenter));
                numOfFactories = 7;
            }
        } else if (numPlayers == 4) {
            for (int i = 0; i < 9; i++) {
                factories.add(new Factory(bag, tableCenter));
                numOfFactories = 9;
            }
        } else {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < numPlayers; i++) {
            boards.add(new Board(usedTiles));
        }


        tableArea.addFactories(factories);
        tableArea.startNewRound();
    }

    public List<Board> getBoards() {
        return boards;
    }

    @Override
    public boolean take(int playerId, int sourceId, int idx, int destinationIdx) {
        if (gameFinished) return false;
        if (playerId != turn) return false;
        if (sourceId > numOfFactories + 1 || sourceId < 0) return false;
        if (destinationIdx > 4 || destinationIdx < 0) return false;

        Board currentBoard = boards.get(playerId);
        List<Tile> tiles = tableArea.take(sourceId, idx);
        if (tiles.isEmpty()) return false;
        if (tiles.contains(Tile.STARTING_PLAYER)) newRoundFirstTurn = playerId;
        currentBoard.put(destinationIdx, tiles);


        if (tableArea.isRoundEnd()) {
            FinishRoundResult finishRoundResult = FinishRoundResult.NORMAL;
            for (Board board : boards) {
                FinishRoundResult boardResult = board.finishRound();
                if (boardResult == FinishRoundResult.GAME_FINISHED) {
                    finishRoundResult = FinishRoundResult.GAME_FINISHED;
                }
            }

            if (finishRoundResult == FinishRoundResult.GAME_FINISHED) {
                gameFinished = true;
                for (Board board : boards) {
                    board.endGame();
                    finalPointsList.add(board.getPoints());
                }
                int winningPlayerNum = 0;
                int winningPlayerPoints = boards.get(0).getPoints().getValue();

                for (int i = 1; i < numPlayers; i++) {
                    if (boards.get(i).getPoints().getValue() > winningPlayerPoints) {
                        winningPlayerNum = i;
                        winningPlayerPoints = boards.get(i).getPoints().getValue();
                    }
                }

                System.out.println("The winner is player " + winningPlayerNum + " with " + winningPlayerPoints + " points");

                notifyObservers();
                return true;

            } else {
                tableArea.startNewRound();
                turn = newRoundFirstTurn;
                notifyObservers();
                return true;
            }
        } else {
            if (turn == numPlayers - 1) turn = 0;
            else turn++;
            notifyObservers();
            return true;
        }

    }

    public Map<Class<?>, String> getState() {
        if (gameFinished) {
            state.put(Bag.class, "Game is finished.");
            state.put(TableArea.class, "Game is finished.");
            for (Board board : boards) state.put(board.getClass(), board.state());
            return state;
        } else {
            state.put(Bag.class, bag.state());
            state.put(TableArea.class, tableArea.state());
            for (Board board : boards) state.put(board.getClass(), board.state());
            return state;
        }
    }

}
