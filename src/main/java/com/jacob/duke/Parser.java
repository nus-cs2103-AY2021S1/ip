package main.java.com.jacob.duke;

import main.java.com.jacob.duke.command.ByeCommand;
import main.java.com.jacob.duke.command.Command;
import main.java.com.jacob.duke.command.DeadlineCommand;
import main.java.com.jacob.duke.command.DeleteCommand;
import main.java.com.jacob.duke.command.DoneCommand;
import main.java.com.jacob.duke.command.EventCommand;
import main.java.com.jacob.duke.command.FindCommand;
import main.java.com.jacob.duke.command.PrintFilteredListDateTimeCommand;
import main.java.com.jacob.duke.command.PrintListCommand;
import main.java.com.jacob.duke.command.TodoCommand;

public class Parser {
    /**
     * Parses the full command and decides which command object to create and return for execution.
     *
     * @param fullCommand the full user console input command.
     * @return Command for execution.
     * @throws DukeException thrown when invalid message is given.
     */
    public Command parse(String fullCommand) throws DukeException {
        String[] splitStrings = fullCommand.split(" ");

        assert(splitStrings.length >= 1);

        String firstInput = splitStrings[0];
        Command c;
        switch (firstInput) {
        case "todo":
            c = new TodoCommand(fullCommand);
            break;
        case "deadline":
            c = new DeadlineCommand(fullCommand);
            break;
        case "event":
            c = new EventCommand(fullCommand);
            break;
        case "delete":
            c = new DeleteCommand(fullCommand);
            break;
        case "list":
            c = new PrintListCommand();
            break;
        case "list-due":
            c = new PrintFilteredListDateTimeCommand(fullCommand);
            break;
        case "find":
            c = new FindCommand(fullCommand);
            break;
        case "done":
            c = new DoneCommand(fullCommand);
            break;
        case "bye":
            c = new ByeCommand();
            break;
        default:
            throw new DukeException(" :( OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return c;
    }

}
