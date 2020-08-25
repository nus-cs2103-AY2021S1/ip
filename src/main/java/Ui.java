import exceptions.DukeException;

import java.util.Scanner;

public class Ui {
    Scanner sc = new Scanner(System.in);

    /**
     * Greets user immediately when Duke is executed.
     */
    public void greetings() {
        showLine();
        String greetings = "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(greetings);
        showLine();
    }

    /**
     * Bids farewell to user when Duke is terminated with 'bye' command.
     */
    public void goodbye() {
        // print goodbye message
        String goodbye = "Bye. Hope to see you again soon!";
        System.out.println(goodbye);
        showLine();
        // exits program
        System.exit(0);
    }

    /**
     * Throws DukeException when bad input is given by user to the program.
     *
     * @throws DukeException
     */
    public void badInput() throws DukeException {
        throw new DukeException(
                "I'm sorry, but I don't know what that means :(\n" +
                "\nHere's the command list:\n" +
                "1. list - show your current list of tasks\n" +
                "2. todo - creates a to-do task\n" +
                "3. event - creates an event task\n" +
                "4. deadline - creates a deadline task\n" +
                "5. done - marks task as done\n" +
                "6. delete - deletes task from the list");
    }

    /**
     * Prints a single line of dashes to separate consecutive inputs and outputs.
     */
    public void showLine() {
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Reads input from user to be processed by Parser.
     *
     * @return String command to be processed by Parser.
     */
    public String readCommand() throws DukeException {
        String input = null;
        input = sc.nextLine().trim(); // get rid of leading and trailing spaces
        return input;
    }
}
