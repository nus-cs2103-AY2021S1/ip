package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;
import duke.exception.DukeInputException;

/** Contains functions for parsing user inputs to software commands */
public class Parser {

    /**
     * Parses given input String into commands with parameters.
     *
     * @param userCommand Input string, typically from user.
     * @return Command object that represents the user command and contains execution functionality.
     * @throws DukeInputException Thrown when input cannot be cleanly parsed into an available command.
     */
    public static Command parse(String userCommand) throws DukeInputException {
        String[] inputs = userCommand.split(" ", 2);
        String command = inputs[0];
        String params = "";

        if (inputs.length == 1) {
            switch (command) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            default:
                break;
            }
        } else if (inputs.length == 2) {
            params = inputs[1];
            switch (command) {
            case "done":
                return new DoneCommand(params);
            case "todo":
                return new ToDoCommand(params);
            case "deadline":
                return new DeadlineCommand(params);
            case "event":
                return new EventCommand(params);
            case "delete":
                return new DeleteCommand(params);
            default:
                break;
            }
        }

        throw new DukeInputException("Invalid command <" + userCommand + "> given.");
    }


}
