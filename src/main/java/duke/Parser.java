package duke;

import duke.command.*;

public class Parser {

    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String DONE = "done";
    private static final String TODO = "todo";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";
    private static final String DELETE = "delete";
    private static final String FIND = "find";

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
