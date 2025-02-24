package search;

import java.util.Arrays;

public class PuzzleState extends State {
    private int[] value;
    
    public PuzzleState(int[] value) {
        super(Arrays.toString(value));
        this.value = value;
    }

    public int[] getValue() {
        return value;
    }

    public void setValue(int[] value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PuzzleState state = (PuzzleState) obj;
        return Arrays.equals(value, state.value);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(value);
    }
}
