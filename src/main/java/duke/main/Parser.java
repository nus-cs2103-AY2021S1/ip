package duke.main;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.exception.EmptyDateException;
import duke.exception.EmptyIndexException;
import duke.exception.EmptyTaskException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateFormatException;
import duke.exception.InvalidIndexException;
import duke.exception.InvalidPriorityException;



/**
 * Reads and parses the user input.
 */
public class Parser {
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Reads next line of input from the user.
     *
     * @return The next line that user inputs.
     */
    public static String readNextLine() {
        return sc.nextLine();
    }

    /**
     * Checks whether there is still input coming in.
     *
     * @return True if there is still next line and false otherwise.
     */
    public static boolean hasNextLine() {
        return sc.hasNext();
    }

    /**
     * Returns the command keyword in the user input.
     *
     * @param userInput The string represents user's input.
     * @return The keyword command.
     */
    public static String getCommand(String userInput) {
        String[] components = userInput.split(" ", 2);
        return components[0];
    }

    /**
     * Returns the arguments of the user's input.
     *
     * @param userInput The string represents the user's input.
     * @return The arguments followed the command keyword.
     * @throws EmptyTaskException when there is no task followed the command.
     */
    public static String getArgs(String userInput) throws EmptyTaskException {
        String[] components = userInput.split(" ", 2);
        if (components.length == 1) {
            throw new EmptyTaskException();
        } else {
            return components[1];
        }
    }

    /**
     * Gets the task index for done and delete command.
     *
     * @param userInput The string represents the user's input.
     * @return The task index.
     * @throws InvalidIndexException
     */
    public static int getIndexTask(String userInput) throws EmptyIndexException {
        String[] components = userInput.split(" ", 2);
        if (components.length < 2) {
            throw new EmptyIndexException();
        } else {
            return Integer.parseInt(components[1]);
        }
    }

    /**
     * Returns the task's description from the user input.
     *
     * @param userInput The string represents the user's input.
     * @return The task's description.
     * @throws EmptyTaskException
     */
    public static String findDescription(String userInput) throws EmptyTaskException {
        String[] components = userInput.split("/");
        if (components[0].length() == 0) {
            throw new EmptyTaskException();
        } else {
            return components[0];
        }
    }

    /**
     * Returns the date and/or time for deadline and event tasks.
     *
     * @param userInput The string represents user's input.
     * @param keyword Keyword to distinguish deadline and event tasks.
     * @return The date and/or time for the task.
     * @throws EmptyDateException
     */
    public static String findTime(String userInput, String keyword) throws EmptyDateException {
        String[] components = userInput.split("/" + keyword + " ");
        if (components.length < 2) {
            throw new EmptyDateException();
        } else {
            String timeAndPriority = components[1];
            String time = timeAndPriority.split(" /priority")[0];
            return time;
        }
    }

    /**
     * Checks whether the given date in the correct format of YYYY-MM-DD HH:mm (time is optional).
     *
     * @param time The input date time.
     * @return True if the input date time is of correct format.
     * @throws InvalidDateFormatException when the date keyed in is in a wrong format.
     */
    public static boolean isValidDate(String time, boolean hasTime) throws InvalidDateFormatException {
        String correctFormatNoDate = "yyyy-MM-dd";
        String correctFormatWithDate = "yyyy-MM-dd HH:mm";
        try {
            if (hasTime) {
                LocalDateTime.parse(time, DateTimeFormatter.ofPattern(correctFormatWithDate));
            } else {
                LocalDate.parse(time, DateTimeFormatter.ofPattern(correctFormatNoDate));
            }
            return true;
        } catch (DateTimeException err) {
            throw new InvalidDateFormatException(false);
        }
    }

    /**
     * Checks whether the user's input, other than containing date, contains time or not.
     *
     * @param time The input date time.
     * @return True if there is time included, false otherwise.
     */
    public static boolean hasTime(String time) {
        String[] components = time.split(" ");
        return components.length == 2;
    }

    /**
     * Checks whether user input priority to the task.
     *
     * @param userInput The input from the user.
     * @return True if user indicates the priority of the task, false otherwise.
     */
    public static boolean hasPriority(String userInput) {
        String[] components = userInput.split("/priority ");
        return components.length > 1;
    }

    private static boolean isValidPriority(String priority) {
        return priority.equals("high") || priority.equals("medium") || priority.equals("low");
    }

    /**
     * Returns the string priority of the task.
     *
     * @param userInput The input from the user.
     * @return The string representation of priority level.
     * @throws InvalidPriorityException When the input priority is not either high, medium or low.
     */
    public static String getPriority(String userInput) throws InvalidPriorityException {
        String[] components = userInput.split("/priority ");
        String priority = components[1];
        if (isValidPriority(priority)) {
            return priority;
        } else {
            throw new InvalidPriorityException();
        }
    }

    /**
     * Checks whether the user wants to sort the task list based on the priority of the task.
     *
     * @param userInput The input from the user.
     * @return True if user wants to sort based on priority, false otherwise.
     * @throws InvalidCommandException When the command is invalid.
     */
    public static boolean isSortedByPriority(String userInput) throws InvalidCommandException {
        String[] components = userInput.split(" ", 2);
        if (components.length == 1) {
            return false;
        } else if (components[1].equals("priority")) {
            return true;
        } else {
            throw new InvalidCommandException();
        }
    }
}
