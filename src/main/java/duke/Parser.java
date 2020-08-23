package duke;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Parser {
    private final static String ignoreCase = "(?i)";
    private final static Ui ui = new Ui();

    enum CommandState {
        LIST, DONE, BYE, TODO, DEADLINE, EVENT, DELETE, CHECK, FIND
    }

    public static Command parse(String command) throws DukeException {
        if (command.matches(ignoreCase + CommandState.BYE.name())) {
            return new ExitCommand();
        } else if (command.matches(ignoreCase + CommandState.LIST.name())) {
            return new ListCommand();
        } else if (command.matches(ignoreCase + CommandState.DONE.name() + "(.*)")) {
            return new DoneCommand();
        } else if (command.matches(ignoreCase + CommandState.CHECK.name() + "(.*)")) {
            return new CheckCommand();
        } else if (command.matches(ignoreCase + CommandState.FIND.name() + "(.*)")) {
            return new FindCommand(command);
        } else if (command.matches(ignoreCase + CommandState.DELETE.name() + "(.*)")) {
            return new DeleteCommand();
        } else {
            Task t = checkAction(command);
            return new AddCommand(t);
        }
    }

    public static Task checkAction(String command) throws DukeException{
        Task t;
        if (command.matches(ignoreCase + CommandState.DEADLINE.name() + "(.*)")) {
            t = Deadline.createTask(command);
        } else if (command.matches(ignoreCase + CommandState.EVENT.name() + "(.*)")) {
            t = Event.createTask(command);
        } else if (command.matches(ignoreCase + CommandState.TODO.name() + "(.*)")) {
            t = Todo.createTask(command);
        } else {
            String errMessage = ui.printFormat(" I'm sorry but i do not know what you want to do. *woof*\n");
            throw new DukeException(errMessage);
        }
        return t;
    }

}
