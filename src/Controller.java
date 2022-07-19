// The Controller class specifies the order in which a game
// plays out and uses the Model and TextView classes to
// coordinate the game ordering.
public final class Controller {
    private final Model model;
    private final TextView view;

    public Controller(Model model, TextView view) {
        this.model = model;
        this.view = view;
    }

    public void startSession() {
        // Loop terminates when new game option following culmination of a game is rejected.
        do {
            view.displayNewGameMessage();

            // Check if player wants default game
            boolean playDefaultGame = view.askDefaultGame();
            // Take input concerning desired custom game and modify model accordingly
            if (!playDefaultGame) {
                int nrRows = view.askForNrRows(model);
                model.setNrRows(nrRows);

                int nrCols = view.askForNrCols(model);
                model.setNrCols(nrCols);

                // Important to ensure row and column numbers set before question on
                // number of consecutive symbols to win is asked
                int nrWin = view.askForNrWin(model);
                model.setNrWin(nrWin);

                // Recreate board in model to match dimensions specified.
                // Important to recreate board after new dimensions are set.
                model.createBoard();
            }

            // Check if player wants to play with different symbol
            boolean changeSymbols = view.askChangeSymbols();
            if (changeSymbols) {
                model.changePlayerSymbols();
            }

            // Main game loop carrying out a turn in one iteration.
            // Loop exits when there is either a winner or if the
            // board is full.
            do {
                // Print turn information and board
                view.printTurnNumber(model);
                view.declareWhoseTurn(model);
                view.displayBoard(model);

                // Check if player wants to give up
                boolean giveUp = view.askGiveUp();
                if (giveUp && model.isPlayer1Turn()) {
                    view.declarePlayer2Wins();
                    model.changeGameOver();
                }
                else if (giveUp && !model.isPlayer1Turn()) {
                    view.declarePlayer1Wins();
                    model.changeGameOver();
                }

                // Case where player did not give up on this turn
                else {
                    int[] move = new int[2];
                    // Prompt for move until valid move is provided as input
                    do {
                        move[0] = view.askForMoveRow();
                        move[1] = view.askForMoveCol();
                        if (!model.isMoveValid(move)) {
                            view.printMoveInvalid();
                        }
                    } while (!model.isMoveValid(move));
                    // Make move and display result of move
                    model.makeMove(move);
                    view.displayBoard(model);

                    // Check if game is won after move
                    if (model.isGameWon() && model.isPlayer1Turn()) {
                        view.declarePlayer1Wins();
                        model.changeGameOver();
                    }
                    else if (model.isGameWon() && !model.isPlayer1Turn()) {
                        view.declarePlayer2Wins();
                        model.changeGameOver();
                    }

                    model.changeTurn();
                }

            } while (!model.isGameOver() && !model.isTableFull());

            view.printGameOver();

            // Check if player wants to play a new game.
            // If new game is chosen, model is reset to default settings.
            boolean newGame = view.askNewGame();
            if (newGame) {
                model.restart();
            }
            else {
                model.changePlayNewGame();
            }

        } while (model.isPlayNewGame());
    }
}
