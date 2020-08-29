package seedu.duke;

import java.util.Scanner;

import seedu.duke.task.Task;

/**
 * Represents a group of methods that deal with interactions with the user.
 */
public class Ui {
    private static final String horizontalLine = "      ===================================";
    private static final String indentation = "      ";
    private Scanner scanner;

    /**
     * Class constructor.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Takes in one line of input from the user.
     *
     * @return the user's input
     * @throws DukeException if there is no more input
     */
    String readCommand() throws DukeException {
        if (this.scanner.hasNext()) {
            return scanner.nextLine().trim();
        } else {
            throw new DukeException("No next line");
        }
    }

    /**
     * Prints out a welcome message for the user.
     *//*
    public void showWelcome() {
        String logo = indentation + " ____        _        \n"
                + indentation + "|  _ \\ _   _| | _____ \n"
                + indentation + "| | | | | | | |/ / _ \\\n"
                + indentation + "| |_| | |_| |   <  __/\n"
                + indentation + "|____/ \\__,_|_|\\_\\___|\n";
        String message = "Hello from\n" + logo;
        System.out.println(horizontalLine);
        System.out.println(indentation + message);
        System.out.println(horizontalLine);
    }

    /**
     * Prints out an message to the user.
     *
     * @param message the message to be printed
     */
    public void showMessage(Message message) {
        System.out.println(horizontalLine);
        message.forEach(line -> System.out.println(indentation + line));
        System.out.println(horizontalLine);
    }
}
