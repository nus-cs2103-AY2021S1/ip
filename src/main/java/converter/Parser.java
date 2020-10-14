package converter;

import command.Mode;
import exception.DukeException;
import magic.MyString;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

public class Parser {
    private static final String SEP = MyString.CMD_DELIMITER;

    /**
     * Based on the input, return the correct command mode.
     *
     * @param input String input to interpret
     * @return Command Mode corresponding to input.
     * @throws DukeException thrown when no mode can be found for the input
     */
    public static Mode mode(String input) throws DukeException {
        String command = input.split(Parser.SEP)[0].toUpperCase();
        try {
            return Mode.valueOf(command);
        } catch (IllegalArgumentException e) {
            throw new DukeException(MyString.ERROR_INPUT_ERROR);
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
        assert input.split(Parser.SEP).length >= 2 : MyString.ERROR_INVALID_ARGUMENT;
        String command = input.split(Parser.SEP)[0];

        switch (command) {
        case MyString.CMD_TODO:
            return parseToDo(input);
        case MyString.CMD_DEADLINE:
            return parseDeadline(input);
        case MyString.CMD_EVENT:
            return parseEvent(input);
        default:
            throw new AssertionError(MyString.ERROR_INVALID_TASK);
        }
    }

    private static ToDo parseToDo(String input) {
        String name = input.split(MyString.CMD_TODO)[1].trim();
        assert name.length() > 0 : MyString.EMPTY_DESC;
        return new ToDo(name);
    }

    private static Deadline parseDeadline(String input) throws DukeException {
        String name = input.split(MyString.CMD_DEADLINE_BY)[0].split(MyString.CMD_DEADLINE)[1].trim();
        String by = input.split(MyString.CMD_DEADLINE_BY)[1].trim();
        assert by.length() > 0 : MyString.EMPTY_DESC;
        assert name.length() > 0 : MyString.EMPTY_DESC;
        return new Deadline(name, by);
    }

    private static Event parseEvent(String input) {
        String name = input.split(MyString.CMD_EVENT_AT)[0].split(MyString.CMD_EVENT)[1].trim();
        String at = input.split(MyString.CMD_EVENT_AT)[1].trim();
        assert at.length() > 0 : MyString.EMPTY_DESC;
        assert name.length() > 0 : MyString.EMPTY_DESC;
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
            return Integer.parseInt(input.split(Parser.SEP)[1]);

        } catch (NumberFormatException e) {
            throw new DukeException(MyString.ERROR_INTEGER);

        }

    }

    /**
     * Returns the sub name to search for.
     *
     * @param input Raw command input as String.
     * @return Sub name to search for.
     * @throws DukeException when input has too few arguments
     */
    public static String findName(String input) throws DukeException{
        if (input.split(Parser.SEP).length<2) {
            throw new DukeException (MyString.ERROR_INVALID_DESC);
        }
        return input.split(Parser.SEP, 2)[1].trim();
    }

    /**
     * Return name of tag as String
     *
     * @param input Raw command input as String
     * @return Name of tag
     * @throws DukeException when input has too few arguments
     */
    public static String getTag(String input) throws DukeException{
        if (input.split(Parser.SEP).length<3) {
            throw new DukeException (MyString.ERROR_INVALID_DESC);
        }
        return input.split(Parser.SEP, 3)[2].trim();
    }
}
