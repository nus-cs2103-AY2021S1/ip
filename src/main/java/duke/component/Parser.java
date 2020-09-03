package duke.component;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.HappenCommand;
import duke.command.InvalidCommandException;
import duke.command.ListCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Holds the methods for parsing commands.
 */
public class Parser {

    public static final String BYE_COMMAND = "bye";
    public static final String LIST_COMMAND = "list";
    public static final String DELETE_COMMAND_PREFIX = "delete ";
    public static final String DONE_COMMAND_PREFIX = "done ";
    public static final String HAPPEN_COMMAND_PREFIX = "happen ";
    public static final String FIND_COMMAND_PREFIX = "find ";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String BY_TIME_IDENTIFIER = "/by";
    public static final String AT_TIME_IDENTIFIER = "/at";
    public static final char SPACE_CHAR = ' ';
    public static final String SPACE_STRING = " ";
    public static final String SUGGESTION_FORMAT = "Do you mean '%s %s'?";
    public static final String EMPTY_DONE_COMMAND_EXCEPTION = "The task to mark as done cannot be empty.";
    public static final String EMPTY_DELETE_COMMAND_EXCEPTION = "The task to mark to delete cannot be empty.";
    public static final String EMPTY_TASK_DESCRIPTION_EXCEPTION = "The description of a task cannot be empty.";
    public static final String EMPTY_TIME_EXCEPTION = "The time specification cannot be empty.";
    public static final String NONPOSITIVE_TASK_INDEX_EXCEPTION = "The task index should be a positive integer.";
    public static final String TASK_INDEX_OVERFLOW_EXCEPTION = "The task index does not exist.";
    public static final String NONNUMERIC_TASK_INDEX_EXCEPTION = "The task index should be a number.";
    public static final String LACK_TIME_SPECIFICATION_EXCEPTION = "Time should be specified";
    public static final String UNRECOGNIZED_COMMAND_EXCEPTION = "I'm sorry, but I don't know what that means :-(";


    private static boolean hasEmptyContent(String cmd, String prefix) {
        return cmd.length() < prefix.length() + 1;
    }

    private static int parseTaskIndex(String cmd, String prefix) throws InvalidCommandException {
        try {
            return Integer.parseInt(cmd.substring(prefix.length()));
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(NONNUMERIC_TASK_INDEX_EXCEPTION);
        }
    }

    private static int getInputTaskIndex(String cmd, int count, String prefix) throws InvalidCommandException {
        int n = parseTaskIndex(cmd, prefix);
        if (n < 1) {
            throw new InvalidCommandException(NONPOSITIVE_TASK_INDEX_EXCEPTION);
        } else if (n > count) {
            throw new InvalidCommandException(TASK_INDEX_OVERFLOW_EXCEPTION);
        } else {
            return n;
        }
    }

    /**
     * Parses a DoneCommand to tell which task to mark as done.
     * @param cmd the given input command
     * @param count the current number of tasks in list
     * @return the index of the task to mark as done
     * @throws InvalidCommandException if the input is invalid, including non-integer, negative values, 0 or large
     * numbers
     */
    public static int getDoneTaskIndex(String cmd, int count) throws InvalidCommandException {
        if (hasEmptyContent(cmd, DONE_COMMAND_PREFIX)) {
            throw new InvalidCommandException(EMPTY_DONE_COMMAND_EXCEPTION);
        }
        return getInputTaskIndex(cmd, count, DONE_COMMAND_PREFIX);
    }

    /**
     * Parses a DeleteCommand to tell which task to delete.
     * @param cmd the given input command
     * @param count the current number of tasks in list
     * @return the index of the task to delete
     * @throws InvalidCommandException if the input is invalid, including non-integer, negative values, 0 or large
     * numbers
     */
    public static int getDeleteTaskIndex(String cmd, int count) throws InvalidCommandException {
        if (hasEmptyContent(cmd, DELETE_COMMAND_PREFIX)) {
            throw new InvalidCommandException(EMPTY_DELETE_COMMAND_EXCEPTION);
        }
        return getInputTaskIndex(cmd, count, DELETE_COMMAND_PREFIX);
    }

    private static String generateSuggestion(String taskType, String description) {
        return String.format(SUGGESTION_FORMAT, taskType, description.substring(taskType.length()));
    }

    private static boolean hasSpaceAfterType(String cmd, String taskType) {
        return cmd.charAt(taskType.length()) == SPACE_CHAR;
    }

    private static void checkValidAddCommand(String cmd, String taskType) throws InvalidCommandException {
        if (hasEmptyContent(cmd, taskType)) {
            throw new InvalidCommandException(EMPTY_TASK_DESCRIPTION_EXCEPTION);
        } else if (!hasSpaceAfterType(cmd, taskType)) {
            throw new InvalidCommandException(generateSuggestion(taskType, cmd));
        } else if (hasEmptyContent(cmd, taskType + SPACE_STRING)) {
            throw new InvalidCommandException(EMPTY_TASK_DESCRIPTION_EXCEPTION);
        }
    }

    private static String getTaskDescription(String cmd, String taskType) throws InvalidCommandException {
        checkValidAddCommand(cmd, taskType);
        return cmd.substring(taskType.length() + 1);
    }

    private static int locateTimeIdentifier(String description, String identifier) throws InvalidCommandException {
        int result = description.indexOf(identifier);
        if (result == -1) {
            throw new InvalidCommandException(LACK_TIME_SPECIFICATION_EXCEPTION);
        }
        return result;
    }

    private static String getTimeSpecification(String description, String identifier, int timePosition)
            throws InvalidCommandException {
        String timeSpecificationPart = description.substring(timePosition);
        if (hasEmptyContent(timeSpecificationPart, identifier + SPACE_STRING)) {
            throw new InvalidCommandException(EMPTY_TIME_EXCEPTION);
        }
        return timeSpecificationPart.substring(identifier.length() + 1);
    }

    private static String getPlainDescription(String description, int timePosition) {
        return description.substring(0, timePosition - 1);
    }

    /**
     * Parses an AddCommand to tell what is the task need to be added.
     * @param cmd the given input command
     * @return the task to be added according to the command
     * @throws InvalidCommandException if the command does not make sense
     */
    public static Task generate(String cmd) throws InvalidCommandException {
        if (cmd.startsWith(TODO)) {
            return new ToDo(getTaskDescription(cmd, TODO));
        } else if (cmd.startsWith(DEADLINE)) {
            String fullDescription = getTaskDescription(cmd, DEADLINE);
            int timePosition = locateTimeIdentifier(fullDescription, BY_TIME_IDENTIFIER);
            String time = getTimeSpecification(fullDescription, BY_TIME_IDENTIFIER, timePosition);
            String description = getPlainDescription(fullDescription, timePosition);
            return new Deadline(description, time);
        } else if (cmd.startsWith(EVENT)) {
            String fullDescription = getTaskDescription(cmd, EVENT);
            int timePosition = locateTimeIdentifier(fullDescription, AT_TIME_IDENTIFIER);
            String time = getTimeSpecification(fullDescription, AT_TIME_IDENTIFIER, timePosition);
            String description = getPlainDescription(fullDescription, timePosition);
            return new Event(description, time);
        } else {
            throw new InvalidCommandException(UNRECOGNIZED_COMMAND_EXCEPTION);
        }
    }

    /**
     * Parses any input command.
     * @param input the given input command
     * @return the Command to be executed
     */
    public static Command parse(String input) {
        if (input.equals(BYE_COMMAND)) {
            return new ByeCommand(input);
        } else if (input.equals(LIST_COMMAND)) {
            return new ListCommand(input);
        } else if (input.startsWith(DELETE_COMMAND_PREFIX)) {
            return new DeleteCommand(input);
        } else if (input.startsWith(DONE_COMMAND_PREFIX)) {
            return new DoneCommand(input);
        } else if (input.startsWith(HAPPEN_COMMAND_PREFIX)) {
            return new HappenCommand(input);
        } else if (input.startsWith(FIND_COMMAND_PREFIX)) {
            return new FindCommand(input);
        } else {
            return new AddCommand(input);
        }
    }
}
