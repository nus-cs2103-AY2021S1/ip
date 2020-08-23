package duke;

import duke.command.*;
import duke.task.Todo;

public class ParserStub {
    private final static String ignoreCase = "(?i)";
    private final static Ui ui = new Ui();

    enum CommandState {
        TODO, DEADLINE, EVENT, DELETE
    }

    public static Command parse(String command) throws DukeException {
        if (command.matches(ignoreCase + Parser.CommandState.BYE.name())) {
            return new ExitCommand();
        } else if (command.matches(ignoreCase + Parser.CommandState.LIST.name())) {
            return new ListCommand();
        } else if (command.matches(ignoreCase + Parser.CommandState.DONE.name() + "(.*)")) {
            return new DoneCommand();
        } else if (command.matches(ignoreCase + Parser.CommandState.CHECK.name() + "(.*)")) {
            return new CheckCommand();
        } else {
            String t = checkAction(command);
            if (t.equals("Delete")) {
                return new DeleteCommand();
            }
            return new AddCommand(Todo.createTask("todo buy books"));
        }
    }

    public static String checkAction(String command) throws DukeException{
        String s;
        if (command.matches(ignoreCase + CommandState.DEADLINE.name() + "(.*)")) {
            s = "Deadline";
        } else if (command.matches(ignoreCase + CommandState.EVENT.name() + "(.*)")) {
            s = "Event";
        } else if (command.matches(ignoreCase + CommandState.TODO.name() + "(.*)")) {
            s = "Todo";
        } else if (command.matches(ignoreCase + CommandState.DELETE.name() + "(.*)")) {
            s = "Delete";
        } else {
            String errMessage = ui.printFormat(" I'm sorry but i do not know what you want to do. *woof*\n");
            throw new DukeException(errMessage);
        }
        return s;
    }

}
