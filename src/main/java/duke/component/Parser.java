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
    public static final String EMPTY_DONE_COMMAND_EXCEPTION = "\u2639 OOPS!!! "
            + "The task to mark as done cannot be empty.";
    public static final String EMPTY_DELETE_COMMAND_EXCEPTION = "\u2639 OOPS!!! "
            + "The task to mark to delete cannot be empty.";
    public static final String EMPTY_TASK_DESCRIPTION_EXCEPTION = "\u2639 OOPS!!!"
            + " The description of a task cannot be empty.";
    public static final String EMPTY_TIME_EXCEPTION = "The time specification cannot be empty.";
    public static final String NONPOSITIVE_TASK_INDEX_EXCEPTION = "\u2639 OOPS!!! The task index "
            + "should be a positive integer.";
    public static final String TASK_INDEX_OVERFLOW_EXCEPTION = "\u2639 OOPS!!! The task index does not exist.";
    public static final String NONNUMERIC_TASK_INDEX_EXCEPTION = "\u2639 OOPS!!! The task index should be a number.";
    public static final String LACK_TIME_SPECIFICATION_EXCEPTION = "\u2639 OOPS!!! Time should be specified";

    /**
     * Parses a DoneCommand to tell which task to mark as done.
     * @param cmd the given input command
     * @param count the current number of tasks in list
     * @return the index of the task to mark as done
     * @throws InvalidCommandException if the input is invalid, including non-integer, negative values, 0 or large
     * numbers
     */
    public static int isValidDone(String cmd, int count) throws InvalidCommandException {
        if (cmd.startsWith(DONE_COMMAND_PREFIX)) {
            if (cmd.length() < 6) {
                throw new InvalidCommandException(EMPTY_DONE_COMMAND_EXCEPTION);
            }
            try {
                int n = Integer.parseInt(cmd.substring(5));
                if (n < 1) {
                    throw new InvalidCommandException(NONPOSITIVE_TASK_INDEX_EXCEPTION);
                } else if (n > count) {
                    throw new InvalidCommandException(TASK_INDEX_OVERFLOW_EXCEPTION);
                }
                return n;
            } catch (NumberFormatException e) {
                throw new InvalidCommandException(NONNUMERIC_TASK_INDEX_EXCEPTION);
            }
        } else {
            return -1;
        }
    }

    /**
     * Parses a DeleteCommand to tell which task to delete.
     * @param cmd the given input command
     * @param count the current number of tasks in list
     * @return the index of the task to delete
     * @throws InvalidCommandException if the input is invalid, including non-integer, negative values, 0 or large
     * numbers
     */
    public static int isValidDelete(String cmd, int count) throws InvalidCommandException {
        if (cmd.startsWith(DELETE_COMMAND_PREFIX)) {
            if (cmd.length() < 8) {
                throw new InvalidCommandException(EMPTY_DELETE_COMMAND_EXCEPTION);
            }
            try {
                int n = Integer.parseInt(cmd.substring(7));
                if (n < 1) {
                    throw new InvalidCommandException(NONPOSITIVE_TASK_INDEX_EXCEPTION);
                } else if (n > count) {
                    throw new InvalidCommandException(TASK_INDEX_OVERFLOW_EXCEPTION);
                }
                return n;
            } catch (NumberFormatException e) {
                throw new InvalidCommandException(NONNUMERIC_TASK_INDEX_EXCEPTION);
            }
        } else {
            return -1;
        }
    }

    /**
     * Parses an AddCommand to tell what is the task need to be added.
     * @param cmd the given input command
     * @return the task to be added according to the command
     * @throws InvalidCommandException if the command does not make sense
     */
    public static Task generate(String cmd) throws InvalidCommandException {
        if (cmd.startsWith(TODO)) {
            if (cmd.length() < 5) {
                throw new InvalidCommandException(EMPTY_TASK_DESCRIPTION_EXCEPTION);
            } else if (cmd.charAt(4) != ' ') {
                throw new InvalidCommandException("Do you mean 'todo " + cmd.substring(4) + "'");
            } else if (cmd.length() < 6) {
                throw new InvalidCommandException(EMPTY_TASK_DESCRIPTION_EXCEPTION);
            }
            return new ToDo(cmd.substring(5));
        } else if (cmd.startsWith(DEADLINE)) {
            if (cmd.length() < 9) {
                throw new InvalidCommandException(EMPTY_TASK_DESCRIPTION_EXCEPTION);
            } else if (cmd.charAt(8) != ' ') {
                throw new InvalidCommandException("Do you mean 'deadline " + cmd.substring(8) + "'");
            } else if (cmd.length() < 10) {
                throw new InvalidCommandException(EMPTY_TASK_DESCRIPTION_EXCEPTION);
            }
            String description = cmd.substring(9);
            int s = description.indexOf("/by");
            if (s == -1) {
                throw new InvalidCommandException(LACK_TIME_SPECIFICATION_EXCEPTION);
            }
            if (description.length() - s < 4) {
                throw new InvalidCommandException(EMPTY_TIME_EXCEPTION);

            }
            String time = description.substring(s + 4);
            description = description.substring(0, s - 1);
            return new Deadline(description, time);
        } else if (cmd.startsWith(EVENT)) {
            if (cmd.length() < 6) {
                throw new InvalidCommandException(EMPTY_TASK_DESCRIPTION_EXCEPTION);
            } else if (cmd.charAt(5) != ' ') {
                throw new InvalidCommandException("Do you mean 'event " + cmd.substring(5) + "'");
            } else if (cmd.length() < 7) {
                throw new InvalidCommandException(EMPTY_TASK_DESCRIPTION_EXCEPTION);
            }
            String description = cmd.substring(6);
            int s = description.indexOf("/at");
            if (s == -1) {
                throw new InvalidCommandException(LACK_TIME_SPECIFICATION_EXCEPTION);
            }
            if (description.length() - s < 4) {
                throw new InvalidCommandException(EMPTY_TIME_EXCEPTION);

            }
            String time = description.substring(s + 4);
            description = description.substring(0, s - 1);
            return new Event(description, time);
        } else {
            throw new InvalidCommandException();
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
