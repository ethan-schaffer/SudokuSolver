public class SudokuBoard {

    private SudokuTile[][] tiles;

    private SudokuTile[][] boxes;

    SudokuBoard() {
        tiles = new SudokuTile[9][9];
        for (int row : new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8}) {
            for (int col : new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8}) {
                tiles[row][col] = new SudokuTile(this, row, col);
            }
        }

        boxes = new SudokuTile[9][9];
        for (int boxRow : new int[]{0, 1, 2}) {
            for (int boxCol : new int[]{0, 1, 2}) {
                for (int innerRow : new int[]{0, 1, 2}) {
                    for (int innerCol : new int[]{0, 1, 2}) {
                        boxes[boxCol * 3 + boxRow][innerCol * 3 + innerRow] = tiles[boxRow * 3 + innerRow][boxCol * 3 + innerCol];
                    }
                }
            }
        }
    }

    void setValue(int value, int row, int column) {
        tiles[row][column].setValue(value);

        // Handles Vertical and Horizontals
        for (int val : new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8}) {
            tiles[val][column].removeValue(value);
            tiles[row][val].removeValue(value);
        }

        // Handles the Box
        int boxRow = row / 3;
        int boxCol = column / 3;

        for(SudokuTile tile : boxes[boxCol * 3 + boxRow]) {
            tile.removeValue(value);
        }

    }

    public boolean isSolved(){
        for (SudokuTile[] row : tiles) {
            for (SudokuTile tile : row) {
                if(tile.getValue() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (SudokuTile[] row : tiles) {
            for (SudokuTile tile : row) {
                stringBuilder.append(tile.toString() + "\n");
            }
        }
        return stringBuilder.toString();
    }

    String prettyOutput(){
        StringBuilder stringBuilder = new StringBuilder();
        for (SudokuTile[] row : tiles) {
            for (SudokuTile tile : row) {
                if(tile.getValue() != 0) {
                    stringBuilder.append(" " + tile.getValue());
                } else {
                    stringBuilder.append(" _");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
