package duke;

import duke.exception.DukeException;

import java.util.Scanner;

/**
 * Encapsulates the UI class
 */
public class Ui {

    private Scanner sc;

    /**
     * Creates a new UI
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints a dotted line
     */
    public void showLine() {
        System.out.println("------------");
    }

    /**
     * Prints the bye message
     */
    public void showBye() {
        System.out.println("Bye!");
    }

    /**
     * Returns the input line
     * @return User input line
     */
    public String getLine() {
        return sc.nextLine();
    }

    /**
     * Prints the message
     * @param str Message to be printed
     */
    public void printMessage(String str) {
        System.out.println(str);
    }

    /**
     * Prints the welcome message to Duke
     */
    public void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    /**
     * Prints the error message
     * @param ex Error to be printed
     */
    public void printError(DukeException ex) {
        System.out.println(ex);
    }
}
