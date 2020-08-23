package duke;

import duke.command.*;
import duke.task.Task;
import duke.task.Todo;

public class ParserStub {
    private final static String ignoreCase = "(?i)";
    private final static String wildcard = "(.*)";
    private final static Ui ui = new Ui();

    enum CommandState {
        TODO, DEADLINE, EVENT, DELETE
    }

    public static Command parse(String command) throws DukeException {
        if (command.matches(ignoreCase + Parser.CommandState.BYE.name())) {
            return new ExitCommand();
        } else if (command.matches(ignoreCase + Parser.CommandState.LIST.name())) {
            return new ListCommand();
        } else if (command.matches(ignoreCase + Parser.CommandState.DONE.name() + wildcard)) {
            return new DoneCommand();
        } else if (command.matches(ignoreCase + Parser.CommandState.CHECK.name() + wildcard)) {
            return new CheckCommand();
        } else if (command.matches(ignoreCase + Parser.CommandState.DELETE.name() + "(.*)")) {
            return new DeleteCommand();
        } else if (command.matches(ignoreCase + Parser.CommandState.FIND.name() + "(.*)")) {
            return new FindCommand(command);
        } else {
            String t = checkAction(command);
            return new AddCommand(Todo.createTask("todo buy books"));
        }
    }

    public static String checkAction(String command) throws DukeException{
        String s;
        if (command.matches(ignoreCase + CommandState.DEADLINE.name() + wildcard)) {
            s = "Deadline";
        } else if (command.matches(ignoreCase + CommandState.EVENT.name() + wildcard)) {
            s = "Event";
        } else if (command.matches(ignoreCase + CommandState.TODO.name() + wildcard)) {
            s = "Todo";
        } else if (command.matches(ignoreCase + CommandState.DELETE.name() + wildcard)) {
            s = "Delete";
        } else {
            String errMessage = ui.printFormat(" I'm sorry but i do not know what you want to do. *woof*\n");
            throw new DukeException(errMessage);
        }
        return s;
    }

}
