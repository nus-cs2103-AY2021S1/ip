package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ExitCommand;

import duke.exception.UnknownCommandException;


public class Parser {
    public static Command parse(String input) throws UnknownCommandException {
        Command command;
        switch (input) {
            case "help":
                command = new HelpCommand();
                break;
            case "add":
                command = new AddCommand();
                break;
            case "list":
                command = new ListCommand();
                break;
            case "done":
                command = new DoneCommand();
                break;
            case "delete":
                command = new DeleteCommand();
                break;
            case "find":
                command = new FindCommand();
                break;
            case "bye":
                command = new ExitCommand();
                break;
            default:
                throw new UnknownCommandException();
        }
        return command;
    }
}
