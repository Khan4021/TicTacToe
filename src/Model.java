import java.util.ArrayList;
import java.util.List;

// The Model class contains all the information concerning
// the state of the game and methods dealing in the logic
// of the game.
public final class Model {
    // ===========================================================================
    // ================================ CONSTANTS ================================
    // ===========================================================================
    // The most common version of TicTacToe has 3 rows and 3 columns.
    public static final int DEFAULT_NR_ROWS = 3;
    public static final int DEFAULT_NR_COLS = 3;
    public static final int DEFAULT_NR_WIN = 3;

    // Free space in board shown by "-".
    private static final String emptySymbol = "-";

    // ========================================================================
    // ================================ FIELDS ================================
    // ========================================================================
    // The size of the TicTacToe table.
    private int nrRows;
    private int nrCols;
    private int nrWin;

    private boolean player1Turn;
    private int turn;
    private boolean gameOver;
    private boolean playNewGame;
    private List<String> playerSymbols;

    // The state of the table.
    private String [][] state;

    // CONSTRUCTOR
    public Model() {
        // Initialise the table size to its default values.
        nrRows = DEFAULT_NR_ROWS;
        nrCols = DEFAULT_NR_COLS;
        nrWin = DEFAULT_NR_WIN;

        player1Turn = true;
        turn = 1;
        gameOver = false;
        playNewGame = true;
        // Initialise first player to be crosses and second player to be noughts
        playerSymbols = new ArrayList<>();
        playerSymbols.add(0,"X");
        playerSymbols.add(1,"O");

        state = new String [nrRows][nrCols];
        for (int i = 0; i < nrRows; i++) {
            for (int j = 0; j < nrCols; j++) {
                state[i][j] = emptySymbol;
            }
        }
    }

    // MODEL INTERACTIONS

    // Initialise board using model's dimensions.
    public void createBoard() {
        state = new String [nrRows][nrCols];
        for (int i = 0; i < nrRows; i++) {
            for (int j = 0; j < nrCols; j++) {
                state[i][j] = emptySymbol;
            }
        }
    }

    // Move is represented by an array of size 2 where first entry
    // is row number and second entry is column number.
    public boolean isMoveValid(int[] move) {
        if (move[0] <= nrRows && move[0] > 0) {
            if (move[1] <= nrCols && move[1] > 0) {
                // Row numbers and column numbers are not zero-indexed so
                // must account for this when used in arrays.
                if (state[move[0] - 1][move[1] - 1].equals(emptySymbol)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void makeMove(int[] move) {
        if (player1Turn) {
            state[move[0] - 1][move[1] - 1] = playerSymbols.get(0);
        }
        else {
            state[move[0] - 1][move[1] - 1] = playerSymbols.get(1);
        }
    }

    public boolean isTableFull() {
        // Loop through board until empty space found
        for (int i = 0; i < nrRows; i++) {
            for (int j = 0; j < nrRows; j++) {
                if (state[i][j].equals(emptySymbol)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Board must have at least 3 rows.
    public boolean isValidNrRows(int nrRows) {
        if (nrRows > 2) {
            return true;
        }
        else {
            return false;
        }
    }

    // Board must have at least 3 columns.
    public boolean isValidNrCols(int nrCols) {
        if (nrCols > 2) {
            return true;
        }
        else {
            return false;
        }
    }

    // Number of consecutive symbols to win must be at least 3
    // and at most the length of the smallest side of the board.
    public boolean isValidNrWin(int nrWin) {
        if (nrWin <= nrRows && nrWin <= nrCols && nrWin > 2) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isGameWon() {
        // Directions consider possibility for vertical, horizontal and diagonal wins.
        int[][] directions = {{1,0},{0,1},{1,1},{-1,1}};
        // Loop through board until non-empty cell found.
        for (int i = 0; i < nrRows; i++) {
            for (int j = 0; j < nrCols; j++) {
                String symbol = state[i][j];
                if (playerSymbols.contains(symbol)) {
                    // Check if a winning streak starts from this cell by considering each direction.
                    for (int[] direction : directions) {
                        if (directionWinCheck(i,j,symbol,nrWin,direction)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // Recursive method that checks if given cell in board is part of winning
    // streak for a particular symbol in a particular direction.
    private boolean directionWinCheck(int row, int col, String symbol, int streakNumLeft, int[] direction) {
        if (state[row][col].equals(symbol)) {
            // Base case where last symbol in winning streak is found
            if (streakNumLeft == 1) {
                return true;
            }
            // Row falls out of bounds of board if direction followed further
            else if (row + direction[0] < 0 || row + direction[0] >= nrRows) {
                return false;
            }
            // Column falls out of bounds of board if direction followed further
            else if (col + direction[1] < 0 || col + direction[1] >= nrCols) {
                return false;
            }
            // Recursive case where direction used to check next cell in board to see if streak continues.
            else {
                int newRow = row + direction[0];
                int newCol = col + direction[1];
                return directionWinCheck(newRow,newCol,symbol,streakNumLeft - 1,direction);
            }
        }
        // Cell does not hold symbol in question so not part of streak for that symbol
        else {
            return false;
        }
    }

    // Method to reset model
    public void restart() {
        nrRows = DEFAULT_NR_ROWS;
        nrCols = DEFAULT_NR_COLS;
        nrWin = DEFAULT_NR_WIN;

        player1Turn = true;
        turn = 1;
        gameOver = false;
        playNewGame = true;
        // Initialise first player to be crosses and second player to be noughts
        playerSymbols.set(0,"X");
        playerSymbols.set(1,"O");

        createBoard();
    }

    private void changePlayer1Turn() {
        player1Turn = !player1Turn;
    }

    private void changeTurnNumber() {
        turn++;
    }

    public void changeTurn() {
        changePlayer1Turn();
        changeTurnNumber();
    }

    public void changePlayNewGame() {
        playNewGame = !playNewGame;
    }

    public void changeGameOver() {
        gameOver = !gameOver;
    }

    // Method to switch symbols for players
    public void changePlayerSymbols() {
        String changeSymbol = playerSymbols.get(0);
        playerSymbols.set(0,playerSymbols.get(1));
        playerSymbols.set(1,changeSymbol);
    }

    // GETTERS
    public int getNrRows() {
        return nrRows;
    }

    public int getNrCols() {
        return nrCols;
    }

    public int getNrWin() {
        return nrWin;
    }

    public boolean isPlayNewGame() {
        return playNewGame;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getTurn() {
        return turn;
    }

    public boolean isPlayer1Turn() {
        return player1Turn;
    }

    public String[][] getState() {
        return state;
    }

    public List<String> getPlayerSymbols() {
        return playerSymbols;
    }

    // SETTERS

    public void setNrCols(int nrCols) {
        this.nrCols = nrCols;
    }

    public void setNrRows(int nrRows) {
        this.nrRows = nrRows;
    }

    public void setNrWin(int nrWin) {
        this.nrWin = nrWin;
    }
}
