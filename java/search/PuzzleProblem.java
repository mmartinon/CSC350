package search;

import java.util.*;

public class PuzzleProblem extends Problem{
    private int[] initialState;
    private int[] goalState;

    public PuzzleProblem(int[] initialState, int[] goalState) {
        super(new PuzzleState(initialState));
        this.initialState = initialState;
        this.goalState = goalState;
    }

    @Override
    public State getInitial() {
        return new PuzzleState(initialState);
    }
    
    @Override
    public boolean goalTest(State state) {
        return Arrays.equals(((PuzzleState) state).getValue(), goalState);
    }

    @Override
    public ArrayList<Tuple> successor(State state) {
        ArrayList<Tuple> successors = new ArrayList<>();
        int[] currentState = ((PuzzleState) state).getValue();
        int blankIndex = findBlank(currentState);

        // Define possible moves: up, left, down, right
        int[] moves = {-3, -1, 3, 1};
        for (int move : moves) {
            int newIndex = blankIndex + move;
            if (isValidMove(blankIndex, newIndex)) {
                int[] newState = currentState.clone();
                newState[blankIndex] = newState[newIndex];
                newState[newIndex] = 0;
                successors.add(new Tuple(new PuzzleAction(move), new PuzzleState(newState)));
            }
        }

        return successors;
    }

    @Override
    public double pathCost(double pathCost, State state, Action action, State nextState) {
        return pathCost + 1; // Each move has a cost of 1
    }

    public double heuristic(State state) {
        int[] currentState = ((PuzzleState) state).getValue();
        int distance = 0;
        for (int i = 0; i < currentState.length; i++) {
            if (currentState[i] != 0) {
                int goalIndex = findIndex(goalState, currentState[i]);
                distance += Math.abs(i / 3 - goalIndex / 3) + Math.abs(i % 3 - goalIndex % 3);
            }
        }
        return distance;
    }

    private int findBlank(int[] state) {
        if (state == null) {
            throw new IllegalArgumentException("State array cannot be null");
        }
        for (int i = 0; i < state.length; i++) {
            if (state[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    private boolean isValidMove(int blankIndex, int newIndex) {
        if (newIndex < 0 || newIndex >= 9) {
            return false;
        }
        if (blankIndex % 3 == 0 && newIndex % 3 == 2) {
            return false;
        }
        if (blankIndex % 3 == 2 && newIndex % 3 == 0) {
            return false;
        }
        return true;
    }

    private int findIndex(int[] state, int value) {
        for (int i = 0; i < state.length; i++) {
            if (state[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
