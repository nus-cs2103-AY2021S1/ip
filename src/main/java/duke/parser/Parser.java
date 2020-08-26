package duke.parser;

import duke.DukeException;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        String command = fullCommand.split(" ")[0];
        switch (command) {
            case "list":
                return new ListCommand();
            case "done":
                return new DoneCommand(fullCommand);
            case "todo":
                return new ToDoCommand(fullCommand);
            case "deadline":
                return new DeadlineCommand(fullCommand);
            case "event":
                return new EventCommand(fullCommand);
            case "delete":
                return new DeleteCommand(fullCommand);
            case "bye":
                return new ByeCommand();
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means");
        }
    }
}
