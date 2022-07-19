import java.util.Scanner;

// Class provides way of accessing user input using scanner
public final class InputUtil {
    private static final Scanner sc = new Scanner(System.in);

    // Constructor is private so other code cannot instantiate
    // the class as it should be used in a static context
    private InputUtil() {
    }

    /**
     * Waits for the user to type an integer on standard input.
     * The integer they entered is returned when they press enter.
     * If the input is not an integer, the user is asked to try again.
     *
     * @return An integer entered by the user.
     */
    public static int readIntFromUser() {
        do {
            if(sc.hasNextInt()) {
                int input = sc.nextInt();
                sc.nextLine();
                return input;
            }
            System.out.print("Please enter an integer: ");
            sc.nextLine();
        }
        while(true);
    }

    /**
     * Waits for the user to type on standard input.
     * The String they entered is returned when they press enter.
     *
     * @return A String entered by the user.
     */
    public static String readStringFromUser() {
        return sc.nextLine();
    }
}
