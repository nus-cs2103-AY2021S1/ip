package duke;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;

/**
 * This class is responsible for parsing the input provided by the user into the respective commands they represent
 */
public class Parser {

    /**
     * Produces a Command object corresponding to the input provided. Throws an exception if invalid input is provided.
     * @param input raw string form of a command
     * @return Command object representing the command in the input
     * @throws DukeException If the input is not a valid command
     */
    public Command parse(String input) throws DukeException {
        String[] splitInput = input.split(" ", 2);
        String command = splitInput[0];
        String details = splitInput.length < 2 ? null : splitInput[1];
        String[] parsedDetails;

        switch (command) {
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(Integer.parseInt(details) - 1);
        case "todo":
            return new ToDoCommand(details);
        case "find":
            return new FindCommand(details);
        case "deadline":
            parsedDetails = details.split(" /by ", 2);
            if (parsedDetails.length < 2) {
                throw DukeException.badDeadlineDate();
            }
            return new DeadlineCommand(parsedDetails[0], parsedDetails[1]);
        case "event":
            parsedDetails = details.split(" /at ", 2);
            if (parsedDetails.length < 2) {
                throw DukeException.badEventDate();
            }
            return new EventCommand(parsedDetails[0], parsedDetails[1]);
        case "delete":
            return new DeleteCommand(Integer.parseInt(details) - 1);
        default:
            throw DukeException.badCommand();
        }
    }
}
