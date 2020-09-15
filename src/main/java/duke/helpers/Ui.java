package duke.helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import duke.errors.DukeException;
import duke.errors.FileAbsentException;

/**
 * deals with interactions with the user
 */
public class Ui {
    private DukeException dukeException; //Contains the DukeException if it is thrown
    private Scanner sc; //Used to scan the user input

    /**
     * Constructor that assigns Scanner sc to Scanner object, to scan values given by user
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * constructor that assigns variables with respective values
     *
     * @param file assigned to variable file
     * @throws DukeException if file is absent at String file given
     */
    public Ui(String file) throws DukeException {
        try {
            sc = new Scanner(new File(file));
        } catch (FileNotFoundException f) {
            sc = new Scanner(System.in);
            throw new FileAbsentException(file);
        }

    }

    public void setDukeException(DukeException dukeException) {
        this.dukeException = dukeException;
    }
    /**
     * This prints out if there is an error when tasks are loaded
     */
    public void showLoadingError() {
        System.out.println(dukeException.getMessage());
    }
    /**
     * prints welcome message
     */
    public void showWelcome() {
        System.out.println("  ____________________________________________________________\n"
                + "  Hello! I'm Duke\n" + "  What can I do for you?");
    }

    /**
     * This prints the ____ for easier readability
     */
    public void showLine() {
        System.out.println("  ____________________________________________________________\n");
    }

    /**
     * This prints the next line of code to execute if it exists
     *
     * @return the string of command
     */
    public String readCommand() {
        if (sc.hasNextLine()) {
            return sc.nextLine();
        } else {
            return null;
        }
    }

    /**
     * Prints out the error
     *
     * @param s s is the error that is printed
     */
    public void showError(String s) {
        System.out.println(s);
    }
}
