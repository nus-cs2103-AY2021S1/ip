package duke.component;

import java.time.format.DateTimeFormatter;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.FixCommand;
import duke.command.HappenCommand;
import duke.command.InvalidCommandException;
import duke.command.ListCommand;
import duke.command.RepeatCommand;
import duke.command.SnoozeCommand;
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
    public static final String FIX_COMMAND_PREFIX = "fix ";
    public static final String SNOOZE_COMMAND_PREFIX = "snooze ";
    public static final String REPEAT_COMMAND_PREFIX = "repeat ";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String BY_TIME_IDENTIFIER = "/by";
    public static final String AT_TIME_IDENTIFIER = "/at";
    public static final String ON = "on";
    public static final String BEFORE = "before";
    public static final String AFTER = "after";
    public static final String IN = "in";
    public static final String BETWEEN = "between";
    public static final String DAYS = "days";
    public static final String TODAY = "today";
    public static final String TO = "to";
    public static final String DAILY = "daily";
    public static final String WEEKLY = "weekly";
    public static final String EVERY = "every";
    public static final char SPACE_CHAR = ' ';
    public static final String SPACE_STRING = " ";
    public static final String TASK_SINGULAR = "task";
    public static final String TASK_PLURAL = "tasks";
    public static final String SUGGESTION_FORMAT = "Do you mean '%s %s'?";
    public static final String EMPTY_DONE_COMMAND_EXCEPTION = "The task to mark as done cannot be empty.";
    public static final String EMPTY_DELETE_COMMAND_EXCEPTION = "The task to mark to delete cannot be empty.";
    public static final String EMPTY_TASK_DESCRIPTION_EXCEPTION = "The description of a task cannot be empty.";
    public static final String EMPTY_TIME_EXCEPTION = "The time specification cannot be empty.";
    public static final String NONPOSITIVE_TASK_INDEX_EXCEPTION = "The task index should be a positive integer.";
    public static final String TASK_INDEX_OVERFLOW_EXCEPTION = "The task index does not exist.";
    public static final String NONNUMERIC_TASK_INDEX_EXCEPTION = "The task index should be a number.";
    public static final String LACK_TIME_SPECIFICATION_EXCEPTION = "Time should be specified.";
    public static final String UNRECOGNIZED_COMMAND_EXCEPTION = "I'm sorry, but I don't know what that means :-(";
    public static final String NEGATIVE_DAYS_EXCEPTION = "Number of days should be a positive integer.";
    public static final String NONNUMERIC_NUMBER_OF_DAYS_EXCEPTION = "Number of days is not a number.";
    public static final String HAPPEN_BETWEEN_EMPTY_PERIOD_EXCEPTION =
            "Latter date is before former date for happen between.";
    public static final String UNRECOGNIZED_HAPPEN_COMMAND_EXCEPTION = "Invalid happen command input.";
    public static final String INVALID_DATE_FORMAT_EXCEPTION = "Invalid date format. Please use yyyy-MM-dd.";
    public static final String INVALID_DATE_TIME_FORMAT_EXCEPTION = "Invalid input datetime, please input as"
            + "yyyy-MM-dd HH:mm.";
    public static final String INVALID_TASK_TYPE_INDEX_EXCEPTION = "The index of task is not of the desired task type.";
    public static final String FIX_COMMAND_FORMAT_EXCEPTION = "The format for fix command should be "
            + "'fix <task_index of event> <datetime to fix>'.";
    public static final String FIX_TIME_NOT_EXIST_EXCEPTION = "The time to fix for the event does not exist.";
    public static final String SNOOZE_COMMAND_FORMAT_EXCEPTION = "The format for snooze command should be "
            + "'snooze <task_index of timed task> to <date/datetime to reschedule to>'.";
    public static final String SNOOZE_UNFIXED_EVENT_EXCEPTION = "Cannot snooze an event with multiple tentative slots.";
    public static final String SNOOZE_TO_EARLIER_TIME_EXCEPTION = "Sorry, you cannot snooze a task to a time earlier"
            + "than its original scheduled time.";
    public static final String REPEAT_COMMAND_FORMAT_EXCEPTION = "The format for repeat command should be "
            + "'repeat <task_index of timed task> [daily/weekly/every {n} days]'.";
    public static final String REPEAT_UNFIXED_EVENT_EXCEPTION = "Cannot repeat an event with multiple tentative slots.";
    public static final String INVALID_FILE_EXCEPTION = "The resource file format is incorrect.";
    public static final String DONE_UNFIXED_EVENT_EXCEPTION =
            "Cannot mark an event as done with multiple tentative slots.";
    public static final DateTimeFormatter DATE_TIME_INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter DATE_TIME_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("hh:mm a   MMM d yyyy");
    public static final DateTimeFormatter DATE_INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DATE_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

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
     * Gets the task index from the input string of task index and checks its validity.
     * @param list the task list of the running duke app
     * @param inputN the string of the input task index
     * @return the task index if it is valid
     * @throws InvalidCommandException if the index is not a number or valid index in the list
     */
    public static int getTaskIndex(TaskList list, String inputN) throws InvalidCommandException {
        try {
            int n = Integer.parseInt(inputN);
            if (n < 1) {
                throw new InvalidCommandException(Parser.NONPOSITIVE_TASK_INDEX_EXCEPTION);
            } else if (n > list.size()) {
                throw new InvalidCommandException(Parser.TASK_INDEX_OVERFLOW_EXCEPTION);
            }
            return n - 1;
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(Parser.NONNUMERIC_TASK_INDEX_EXCEPTION);
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
        assert cmd.startsWith("done ") : "Calling isValidDone not using a done command";
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
        assert cmd.startsWith("delete ") : "Calling isValidDelete not using a delete command";
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
    public static Task parseAddTask(String cmd) throws InvalidCommandException {
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
        } else if (input.startsWith(FIX_COMMAND_PREFIX)) {
            return new FixCommand(input);
        } else if (input.startsWith(SNOOZE_COMMAND_PREFIX)) {
            return new SnoozeCommand(input);
        } else if (input.startsWith(REPEAT_COMMAND_PREFIX)) {
            return new RepeatCommand(input);
        } else {
            return new AddCommand(input);
        }
    }

    /**
     * Gets the string tasks with proper singular or plural format.
     * @param count the number of tasks currently have
     * @return "task" if count is smaller than 1 and "tasks" if count is biggerthan 1
     */
    public static String getTaskPlural(int count) {
        return count <= 1 ? TASK_SINGULAR : TASK_PLURAL;
    }
}
