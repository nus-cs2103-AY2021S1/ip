import java.util.ArrayList;
import java.util.Scanner;

/** Deals with interactions with the user
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

    //Default Constructor
    public Ui() {
        // Using Scanner for reading inputs
        this.scanner = new Scanner(System.in);
    }

    public void displayGreetings() {
        System.out.println("\nHello, I'm Duke!");
        System.out.println("What can I help you with today?");
        System.out.println("\n" + lineBreaker);
        System.out.println();
    }

    public void displayFarewells() {
        System.out.print(outputBreaker);
        System.out.println("Bye! Hoped I helped!");
        System.out.println("\n" + lineBreaker);
    }

    public String readUserInput() {
        // Read user input
        return scanner.nextLine();
    }

    public void printOutputSymbol() {
        System.out.print(outputBreaker);
    }

    public void printLineBreaker() {
        System.out.print(lineBreaker);
    }

    public void showLoadingError() {
        System.out.println("... Who? Never mind. Er-hmm.");
    }

    public void printError(DukeException e) {
        System.out.println(e.getMessage());
    }

    /** Prints all the contents of the list in order **/
    public void printList(TaskList list) {
        System.out.print("Here is what I have! ^^\n");
        // Handles printing empty list
        if (list.isListEmpty()) {
            System.out.println("Whoops! I don't have anything of note yet...");
        } else {
            list.printAllTasks();
        }
    }
}
