package duke;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * The <code>Parser</code> reads a command from user and perform various checks to determine the next action.
 */
public class Parser {
    private final static String ignoreCase = "(?i)";
    private final static String wildcard = "(.*)";
    private final static Ui ui = new Ui();

    /**
     * <code>CommandState</code> is an enum representing all possible command direction from user.
     */
    enum CommandState {
        LIST, DONE, BYE, TODO, DEADLINE, EVENT, DELETE, CHECK
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
        if (command.matches(ignoreCase + CommandState.BYE.name())) {
            return new ExitCommand();
        } else if (command.matches(ignoreCase + CommandState.LIST.name())) {
            return new ListCommand();
        } else if (command.matches(ignoreCase + CommandState.DONE.name() + wildcard)) {
            return new DoneCommand();
        } else if (command.matches(ignoreCase + CommandState.CHECK.name() + wildcard)) {
            return new CheckCommand();
        } else if (command.matches(ignoreCase + CommandState.DELETE.name() + "(.*)")) {
            return new DeleteCommand();
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
    public static Task checkAction(String command) throws DukeException{
        Task t;
        if (command.matches(ignoreCase + CommandState.DEADLINE.name() + wildcard)) {
            t = Deadline.createTask(command);
        } else if (command.matches(ignoreCase + CommandState.EVENT.name() + wildcard)) {
            t = Event.createTask(command);
        } else if (command.matches(ignoreCase + CommandState.TODO.name() + wildcard)) {
            t = Todo.createTask(command);
        } else {
            String errMessage = ui.printFormat(" I'm sorry but i do not know what you want to do. *woof*\n");
            throw new DukeException(errMessage);
        }
        return t;
    }

}
