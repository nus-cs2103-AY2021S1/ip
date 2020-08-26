import java.util.Scanner;

/**
 * Deals with interactions with the user
 * */
public class Ui {
    /** Constants **/
    private final String outputBreaker = ">>> ";
    private final String lineBreaker = "--.--.--.--.--.--.--.--.--.--.--." +
            "--.--.--.--.--.--.--.--.--.--.--";
    // Text Images
    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    // Other variable
    private Scanner scanner;

    /**
     * Constructors.
     */
    public Ui() {
        // Using Scanner for reading inputs
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints Duke's greetings to user.
     */
    public void displayGreetings() {
        System.out.println("\nHello, I'm Duke!");
        System.out.println("What can I help you with today?");
        System.out.println("\n" + lineBreaker);
        System.out.println();
    }

    /**
     * Print's Duke's farewell to user.
     */
    public void displayFarewells() {
        System.out.print(outputBreaker);
        System.out.println("Bye! Hoped I helped!");
        System.out.println("\n" + lineBreaker);
    }

    /**
     * Takes in input from user.
     *
     * @return User input as a String.
     */
    public String readUserInput() {
        // Read user input
        return scanner.nextLine();
    }

    /**
     * Prints Duke's symbols before response.
     */
    public void printOutputSymbol() {
        System.out.print(outputBreaker);
    }

    /**
     * Prints a line.
     */
    public void printLineBreaker() {
        System.out.println(lineBreaker);
        System.out.println();
    }

    /**
     * Prints error message for when Duke fails to load save file.
     */
    public void showLoadingError() {
        System.out.println("... Who? Never mind. Er-hmm.");
        System.out.println();
    }

    /**
     * Prints error messages from DukeException.
     */
    public void printError(DukeException e) {
        System.out.println(e.getMessage());
        System.out.println();
    }

    /**
     * Prints all tasks within a TaskList (if any).
     * Else prints a list is empty message.
     *
     * @param list TaskList of Duke.
     */
    public void printList(TaskList list) {
        System.out.print("Here is what I have! ^^\n");
        if (list.isListEmpty()) {
            // Handles printing empty list
            System.out.println("Whoops! I don't have anything of note yet...");
        } else {
            list.printAllTasks();
        }
        System.out.println();
    }
}
