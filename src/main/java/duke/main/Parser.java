package main.java.duke.main;

import java.util.Scanner;

import main.java.duke.exception.EmptyDateException;
import main.java.duke.exception.EmptyTaskException;
import main.java.duke.exception.InvalidDateFormatException;
import main.java.duke.exception.InvalidIndexException;

/**
 * Reads and parses the user input.
 */
public class Parser {
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Reads next line of input from the user.
     * @return The next line that user inputs.
     */
    public static String readNextLine() {
        return sc.nextLine();
    }

    /**
     * Checks whether there is still input coming in.
     * @return True if there is still next line and false otherwise.
     */
    public static boolean hasNextLine() {
        return sc.hasNext();
    }

    /**
     * Returns the command keyword in the user input.
     * @param userInput The string represents user's input.
     * @return The keyword command.
     */
    public static String getCommand(String userInput) {
        String[] arr = userInput.split(" ", 2);
        return arr[0];
    }

    /**
     * Returns the arguments of the user's input.
     * @param userInput The string represents the user's input.
     * @return The arguments.
     * @throws EmptyTaskException
     */
    public static String getArgs(String userInput) throws EmptyTaskException {
        String[] arr = userInput.split(" ", 2);
        if (arr.length == 1) {
            throw new EmptyTaskException();
        } else {
            return arr[1];
        }
    }

    /**
     * Gets the task index for done and delete command.
     * @param userInput The string represents the user's input.
     * @return The task index.
     * @throws InvalidIndexException
     */
    public static int getIndexTask(String userInput) throws InvalidIndexException {
        String[] arr = userInput.split(" ", 2);
        if (arr.length < 2) {
            throw new InvalidIndexException();
        } else {
            return Integer.parseInt(arr[1]);
        }
    }

    /**
     * Returns the task's description from the user input.
     * @param userInput The string represents the user's input.
     * @return The task's description.
     * @throws EmptyTaskException
     */
    public static String findDescription(String userInput) throws EmptyTaskException {
        String[] arr = userInput.split("/");
        if (arr[0].length() == 0) {
            throw new EmptyTaskException();
        } else {
            return arr[0];
        }
    }

    /**
     * Returns the date and/or time for deadline and event tasks.
     * @param userInput The string represents user's input.
     * @param keyword Keyword to distinguish deadline and event tasks.
     * @return The date and/or time for the task.
     * @throws EmptyDateException
     */
    public static String findTime(String userInput, String keyword) throws EmptyDateException {
        String[] arr = userInput.split("/" + keyword + " ");
        if (arr.length < 2) {
            throw new EmptyDateException();
        } else {
            return arr[1];
        }
    }

    /**
     * Checks whether the given date in the correct format of YYYY-MM-DD HH:mm (time is optional)
     * @param time The input date time.
     * @return True if the input date time is of correct format.
     * @throws InvalidDateFormatException
     */
    public static boolean isValidDate(String time) throws InvalidDateFormatException {
        String[] stringArr = time.split("-");
        if (stringArr.length != 3) {
            throw new InvalidDateFormatException(false);
        }
        return true;
    }

    /**
     * Checks whether the user's input, other than containing date, contains time or not.
     * @param time The input date time.
     * @return True if there is time included, false otherwise.
     */
    public static boolean hasTime(String time) {
        String[] stringArr = time.split(" ");
        return stringArr.length == 2;
    }
}
