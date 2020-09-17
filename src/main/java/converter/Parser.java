package converter;

import command.Mode;
import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

public class Parser {
    private static final String DELIMITER = " ";

    /**
     * Based on the input, return the correct command mode.
     *
     * @param input String input to interpret
     * @return Command Mode corresponding to input.
     * @throws DukeException thrown when no mode can be found for the input
     */
    public static Mode mode(String input) throws DukeException {
        String command = input.split(Parser.DELIMITER)[0].toUpperCase();
        try {
            return Mode.valueOf(command);
        } catch (IllegalArgumentException e) {
            throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Return the task corresponding to the input given
     *
     * @param input input to interpret
     * @return A type of task will be returned.
     * @throws DukeException thrown when no task can be returned for the corresponding task.
     */
    public static Task task(String input) throws DukeException {
        assert input.split(Parser.DELIMITER).length >= 2 : "At least 2 inputs";
        String command = input.split(Parser.DELIMITER)[0];

        switch (command) {
        case ToDo.COMMAND:
            return parseToDo(input);
        case Deadline.COMMAND:
            return parseDeadline(input);
        case Event.COMMAND:
            return parseEvent(input);
        default:
            throw new DukeException("\u2639 OOPS!!! I'm sorry, but this is an invalid command :-(");
        }
    }

    private static ToDo parseToDo(String input) {
        String name = input.split(ToDo.COMMAND)[1].trim();
        assert name.length() > 0 : "Empty Desc";
        return new ToDo(name);
    }

    private static Deadline parseDeadline(String input) throws DukeException {
        String name = input.split(Deadline.DELIMITER)[0].split(Deadline.COMMAND)[1].trim();
        String by = input.split(Deadline.DELIMITER)[1].trim();
        assert by.length() > 0 : "Empty Date";
        assert name.length() > 0 : "Empty Desc";
        return new Deadline(name, by);
    }

    private static Event parseEvent(String input) {
        String name = input.split(Event.DELIMITER)[0].split(Event.COMMAND)[1].trim();
        String at = input.split(Event.DELIMITER)[1].trim();
        assert at.length() > 0 : "Empty Date";
        assert name.length() > 0 : "Empty Desc";
        return new Event(name, at);

    }

    /**
     * Return specified order as an integer.
     *
     * @param input Raw command input as String.
     * @return Order number as Integer.
     * @throws DukeException when Raw command input given does not contain an integer.
     */
    public static int order(String input) throws DukeException {
        try {
            return Integer.parseInt(input.split(Parser.DELIMITER)[1]);

        } catch (NumberFormatException e) {
            throw new DukeException("\u2639 OOPS!!! Please enter an integer!");

        }

    }

    /**
     * Returns the sub name to search for.
     *
     * @param input Raw command input as String.
     * @return Sub name to search for.
     */
    public static String findName(String input) {
        return input.split(Parser.DELIMITER, 2)[1].trim();
    }

    /**
     * Return name of tag as String
     *
     * @param input Raw command input as String
     * @return Name of tag
     */
    public static String getTag(String input) {
        return input.split(Parser.DELIMITER, 3)[2].trim();
    }
}
