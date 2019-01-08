import java.util.ArrayList;

public class SudokuTile {
    private ArrayList<Integer> potentialValues;
    private int value;
    private SudokuBoard board;
    private int row, col;

    SudokuTile(SudokuBoard board, int row, int col) {
        value = 0;
        this.board = board;
        this.row = row;
        this.col = col;
        int[] allValues = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        potentialValues = new ArrayList<>();
        for (int value : allValues) {
            potentialValues.add(value);
        }
    }

    void removeValue(int value) {
        if(this.value != 0) {
            return;
        }
        int index = potentialValues.indexOf(value);
        if(index == -1) {
            return;
        }
        potentialValues.remove(index);
        if(potentialValues.size() == 1) {
            board.setValue(potentialValues.get(0), row, col);
        }
    }

    void setValue(int value){
        if(this.value != 0) {
            return;
        }
        this.value = value;
    }

    int getValue(){
        return value;
    }

    public String toString(){
        if(value != 0) {
            return "Value is: " + value;
        }
        return "Potential Values are: " + potentialValues.toString();
    }

}
