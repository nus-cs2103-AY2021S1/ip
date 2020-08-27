package duke;

import duke.command.*;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidFormatException;

import java.util.Arrays;

/**
 * The Parser is a utility class that is used to parse and process
 * String input.
 */
public class Parser {

    /**
     * Returns an executable Command class based on the input.
     * If the input does not match any of the default commands,
     * it will throw InvalidCommandException.
     *
     * @param input command given by the user through console.
     * @return executable Command class.
     * @throws InvalidCommandException
     */
    public static Command parse(String input) throws InvalidCommandException {
        String[] splitted = input.split("\\s+");
        String command = splitted[0];
        if (command.equals("help")) {
            return new HelpCommand();
        } else if (command.equals("bye")) {
            return new QuitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.equals("done")) {
            return new DoneCommand();
        } else if (command.equals("delete")) {
            return new DeleteCommand();
        } else if (command.equals("todo")) {
            return new TodoCommand();
        } else if (command.equals("deadline")) {
            return  new DeadlineCommand();
        } else if (command.equals("event")) {
            return new EventCommand();
        } else if (command.equals("find")) {
            return new FindCommand();
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Captures and returns the index of a task specified by the user input.
     *
     * @param input command given by the user through console.
     * @return taskId.
     */
    public static int parseTaskId(String input) {
        String rawNum = input.replaceAll("[^0-9]", "");
        int taskId = Integer.parseInt(rawNum) - 1;
        return taskId;
    }

    /**
     * Captures and returns the description of todo task from the user input.
     * @param input command given by the user through console.
     * @return description of the todo task.
     * @throws EmptyDescriptionException
     */
    public static String parseTodo(String input) throws EmptyDescriptionException {
        String[] splitted = input.split("\\s+");
        if (splitted.length == 1) throw new EmptyDescriptionException("ToDo");
        String[] title = Arrays.copyOfRange(splitted, 1, splitted.length);
        return String.join(" ", title);
    }

    /**
     * Captures and returns the description and deadline of
     * deadline task from the user input.
     *
     * @param input command given by the user through console.
     * @return description and deadline of the deadline task.
     * @throws EmptyDescriptionException
     * @throws InvalidFormatException
     */
    public static String[] parseDeadline(String input) throws EmptyDescriptionException, InvalidFormatException {
        String[] splitted = input.split("\\s+");
        if (splitted.length == 1) {
            throw new EmptyDescriptionException("Deadline");
        }

        int separator = getIndex(splitted, "/by");

        if (separator == -1) {
            throw new InvalidFormatException("/by parameter");
        }

        String[] titles = Arrays.copyOfRange(splitted, 1, separator);
        String[] deadlines = Arrays.copyOfRange(splitted, separator + 1, splitted.length);

        if (deadlines.length == 0) {
            throw new EmptyDescriptionException("/by parameter");
        }

        String title = String.join(" ", titles);
        String deadline = String.join(" ", deadlines);
        String[] pair = {title, deadline};

        return pair;
    }


    private static int getIndex(String[] words, String target) {
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(target)) return i;
        }
        return -1;
    }

    /**
     * Captures and returns the description and date/time of
     * event task from the user input.
     *
     * @param input command given by the user through console.
     * @return description and date/time of the event task.
     * @throws EmptyDescriptionException
     * @throws InvalidFormatException
     */
    public static String[] parseEvent(String input) throws EmptyDescriptionException, InvalidFormatException {
        String[] splitted = input.split("\\s+");
        if (splitted.length == 1) {
            throw new EmptyDescriptionException("Event");
        }

        int separator = getIndex(splitted, "/at");

        if (separator == -1) {
            throw new InvalidFormatException("/at parameter");
        }

        String[] titles = Arrays.copyOfRange(splitted, 1, separator);
        String[] deadlines = Arrays.copyOfRange(splitted, separator + 1, splitted.length);

        if (deadlines.length == 0) {
            throw new EmptyDescriptionException("/at parameter");
        }

        String title = String.join(" ", titles);
        String deadline = String.join(" ", deadlines);
        String[] pair = {title, deadline};

        return pair;
    }

    public static String parseFind(String input) throws EmptyDescriptionException {
        String[] splitted = input.split("\\s+");
        if (splitted.length == 1) throw new EmptyDescriptionException("Find");
        String[] find = Arrays.copyOfRange(splitted, 1, splitted.length);
        return String.join(" ", find);
    }
}
