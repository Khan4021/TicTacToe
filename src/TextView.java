// TextView class is concerned with the output the users can view
// and contains methods specifying what is printed to the users.
// The class also uses the InputUtil class to deal with user
// input where required.
public final class TextView {
    public TextView() {
    }

    public final void displayNewGameMessage() {

        System.out.println("---- NEW GAME STARTED ----");
    }

    public final int askForMoveRow() {
        System.out.print("Enter row number for move: ");
        return InputUtil.readIntFromUser();
    }

    public final int askForMoveCol() {
        System.out.print("Enter column number for move: ");
        return InputUtil.readIntFromUser();
    }

    public final void printMoveInvalid() {
        System.out.println("Move invalid. Enter row and column number for unoccupied space represented by '-'.");
    }

    public final void printGameOver() {
        System.out.println("Game over!");
    }

    public final boolean askGiveUp() {
        String input;
        // Continue loop until satisfactory input is given
        do {
            System.out.print("Do you wish to give up? Enter Yes or No: ");
            input = InputUtil.readStringFromUser();
            if (input.equals("Yes")) {
                return true;
            }
            else if (input.equals("No")) {
                return false;
            }
        } while (true);
    }

    public final void declarePlayer1Wins() {
        System.out.println("Player 1 wins!");
    }

    public final void declarePlayer2Wins() {
        System.out.println("Player 2 wins");
    }

    public final void declareWhoseTurn(Model model) {
        if (model.isPlayer1Turn()) {
            System.out.println("It's player 1's turn.");
        }
        else {
            System.out.println("It's player 2's turn.");
        }
    }

    public final void printTurnNumber(Model model) {
        System.out.println(String.format("Turn: %d%n",model.getTurn()));
    }

    public final boolean askNewGame() {
        String input;
        // Loops until satisfying input is given
        do {
            System.out.print("Do you wish to play a new game? Enter Yes or No: ");
            input = InputUtil.readStringFromUser();
            if (input.equals("Yes")) {
                return true;
            }
            else if (input.equals("No")) {
                return false;
            }
        } while (true);
    }

    public final boolean askDefaultGame() {
        String input;
        // Loops until satisfying input is given
        do {
            System.out.print("Do you wish to play the default game? Enter Yes or No: ");
            input = InputUtil.readStringFromUser();
            if (input.equals("Yes")) {
                return true;
            }
            else if (input.equals("No")) {
                return false;
            }
        } while (true);
    }

    public final int askForNrRows(Model model) {
        int input;
        // Loops until number of rows given is deemed valid
        do {
            System.out.print("How many rows should table have? Enter integer no less than 3. Default is 3: ");
            input = InputUtil.readIntFromUser();
            if (model.isValidNrRows(input)) {
                return input;
            }
        } while (true);
    }

    public final int askForNrCols(Model model) {
        int input;
        // Loops until number of columns given is deemed valid
        do {
            System.out.print("How many columns should table have? Enter integer no less than 3. Default is 3: ");
            input = InputUtil.readIntFromUser();
            if (model.isValidNrCols(input)) {
                return input;
            }
        } while (true);
    }

    public final int askForNrWin(Model model) {
        int input;
        // Loops until given number of consecutive symbols needed for win is valid
        do {
            System.out.println("How many consecutive symbols to win game?");
            System.out.println("Default is 3 and number should be at most the length of smallest side of table.");
            System.out.print("Enter integer no less than 3: ");
            input = InputUtil.readIntFromUser();
            if (model.isValidNrWin(input)) {
                return input;
            }
        } while (true);
    }

    public final boolean askChangeSymbols() {
        String input;
        // Loops until given satisfactory input
        do {
            System.out.println("Default symbol for Player 1 is X and O for Player 2.");
            System.out.print("Do you wish to change symbols? Enter Yes or No: ");
            input = InputUtil.readStringFromUser();
            if (input.equals("Yes")) {
                return true;
            }
            else if (input.equals("No")) {
                return false;
            }
        } while (true);
    }

    public final void displayBoard(Model model) {
        int nrRows = model.getNrRows();
        int nrCols = model.getNrCols();

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int i = 0; i < nrRows; i++) {
            // Add row numbers to display of board
            sb.append(String.format("%d ",i+1));
            for (int j = 0; j < nrCols; j++) {
                // Display each cell of board
                sb.append(model.getState()[i][j]);
            }
            sb.append("\n");
        }
        sb.append("\n");
        // This is to add column numbers to the bottom of the board to aid the users.
        // A couple of spaces added in front of column numbers to align with board.
        sb.append("  ");
        for (int j = 0; j < nrCols; j++ ) {
            sb.append(j + 1);
        }
        sb.append("\n");

        // Then print out the assembled String.
        System.out.println(sb);
    }
}
