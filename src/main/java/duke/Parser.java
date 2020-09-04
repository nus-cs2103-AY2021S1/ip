package duke;

import duke.command.AddCommand;
import duke.command.CheckCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * The <code>Parser</code> reads a command from user and perform various checks to determine the next action.
 */
public class Parser {
    private static final String IGNORE_CASE = "(?i)";
    private static final String WILDCARD = "(.*)";

    /**
     * <code>CommandState</code> is an enum representing all possible command direction from user.
     */
    enum CommandState {
        LIST, DONE, BYE, TODO, DEADLINE, EVENT, DELETE, CHECK, FIND
    }

    /**
     * Obtain an instance of <code>Command</code> from a text string input.
     *
     * <p>The string must represent a valid command input in the correct format of "command taskDetails".</p>
     *
     * @param command the text to parse
     * @return the command desired by user
     * @throws DukeException if the command is unidentifiable
     */
    public static Command parse(String command) throws DukeException {
        if (command.matches(IGNORE_CASE + CommandState.BYE.name() + WILDCARD)) {
            return new ExitCommand();
        } else if (command.matches(IGNORE_CASE + CommandState.LIST.name() + WILDCARD)) {
            return new ListCommand();
        } else if (command.matches(IGNORE_CASE + CommandState.DONE.name() + WILDCARD)) {
            return new DoneCommand();
        } else if (command.matches(IGNORE_CASE + CommandState.CHECK.name() + WILDCARD)) {
            return new CheckCommand();
        } else if (command.matches(IGNORE_CASE + CommandState.DELETE.name() + WILDCARD)) {
            return new DeleteCommand();
        } else if (command.matches(IGNORE_CASE + CommandState.FIND.name() + WILDCARD)) {
            return new FindCommand(command);
        } else {
            Task t = checkAction(command);
            return new AddCommand(t);
        }
    }

    /**
     * Obtain an instance of <code>Task</code> from a text string input.
     * @param command the task command to be parse
     * @return the <code>Task</code> to be acted on
     * @throws DukeException if task command is unidentifiable
     */
    public static Task checkAction(String command) throws DukeException {
        Task t;

        if (command.matches(IGNORE_CASE + CommandState.DEADLINE.name() + WILDCARD)) {
            t = Deadline.createTask(command);
        } else if (command.matches(IGNORE_CASE + CommandState.EVENT.name() + WILDCARD)) {
            t = Event.createTask(command);
        } else if (command.matches(IGNORE_CASE + CommandState.TODO.name() + WILDCARD)) {
            t = Todo.createTask(command);
        } else {
            String errMessage = " I'm sorry but i do not know what you want to do. *woof*\n";
            throw new DukeException(errMessage);
        }
        return t;
    }

}
