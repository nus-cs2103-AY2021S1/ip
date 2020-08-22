import java.util.Scanner;

/**
 * Encapsulates the user interface which user uses to interact with Duke.
 */
public class UI {

    /**
     * Duke's logo.
     */
    private final String LOGO = " ____        _        \n"
                              + "|  _ \\ _   _| | _____ \n"
                              + "| | | | | | | |/ / _ \\\n"
                              + "| |_| | |_| |   <  __/\n"
                              + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Input system using Java's scanner.
     */
    private Scanner in = new Scanner(System.in);
    private final String DIVIDER = "______________________________________________";

    /**
     * Gives the user a prompt to input commands.
     *
     * @return The user's input.
     */
    public String prompt() {
        System.out.print(">> ");
        String response = in.nextLine();
        return response;
    }

    /**
     * Prints the given message between dividers.
     *
     * @param message Message to be printed.
     */
    public void print(String message) {
        System.out.println(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
    }

    /**
     * Greets the user.
     */
    public void greet() {
        String greeting = "Hi! I am\n" + LOGO + "\n" + "What can I do for you?";
        System.out.println(greeting);
    }

    /**
     * Exits the program with farewell message.
     */
    public void exit() {
        String goodbye = "Bye! Hope to see you again!";
        print(goodbye);
        System.exit(0);
    }
}
