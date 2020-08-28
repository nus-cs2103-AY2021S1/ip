package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;

/**
 * The Parser class takes in inputs from the user and convert
 * into commands.
 *
 * @author  Yen Pin Hsuan
 * @version 1.0
 */
public class Parser {

    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String DONE = "done";
    private static final String TODO = "todo";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";
    private static final String DELETE = "delete";
    private static final String FIND = "find";

    /** Returns the command based on user input. */
    public static Command parse(String command) throws DukeException {
        switch (command) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case DONE:
            return new DoneCommand();
        case TODO:
            return new TodoCommand();
        case EVENT:
            return new EventCommand();
        case DEADLINE:
            return new DeadlineCommand();
        case DELETE:
            return new DeleteCommand();
        case FIND:
            return new FindCommand();
        default:
            throw new DukeException("Oops! I'm sorry, but I don't know what that means");
        }
    }
}
