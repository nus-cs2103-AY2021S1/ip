package duke;

import duke.command.*;

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
        default:
            throw new DukeException("Oops! I'm sorry, but I don't know what that means");
        }
    }
}
