package search;

public class PuzzleAction extends Action {
    private int move;

    public PuzzleAction(int move) {
        super("Move " + move); // Use the move as part of the action name
        this.move = move;
    }

    public int getMove() {
        return move;
    }

    @Override
    public String toString() {
        return "PuzzleAction: " + move;
    }
}
