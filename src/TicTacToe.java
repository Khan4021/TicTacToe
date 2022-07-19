// TicTacToe class uses Controller, Model and TextView classes
// to instantiate and start a game.
public final class TicTacToe {

    public static void main(String[] args) {
        Model model = new Model();
        TextView view = new TextView();
        Controller controller = new Controller(model, view);
        controller.startSession();
    }
}
