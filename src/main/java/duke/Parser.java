package duke;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;
import duke.command.UndoCommand;
import duke.exception.DukeException;

/**
 * This class is responsible for parsing the input provided by the user into the respective commands they represent
 */
public class Parser {

    private Command mostRecentCommand; // stores most recent Command to help the undo functionality

    /**
     * Produces a Command object corresponding to the input provided. Throws an exception if invalid input is provided.
     *
     * @param input raw string form of a command
     * @return Command object representing the command in the input
     * @throws DukeException If the input is not a valid command
     */
    public Command parse(String input) throws DukeException {
        String[] splitInput = input.split(" ", 2);
        String command = splitInput[0];
        assert !command.isBlank() : "The command should not be blank";

        String details = (splitInput.length < 2) ? null : splitInput[1];
        String[] parsedDetails;

        Command resultCommand;

        switch (command) {
        case "list":
            resultCommand = new ListCommand();
            break;
        case "done":
            resultCommand = new DoneCommand(Integer.parseInt(details) - 1);
            mostRecentCommand = resultCommand;
            break;
        case "todo":
            resultCommand = new ToDoCommand(details);
            mostRecentCommand = resultCommand;
            break;
        case "find":
            resultCommand = new FindCommand(details);
            break;
        case "deadline":
            parsedDetails = details.split(" /by ", 2);
            if (parsedDetails.length < 2) {
                throw DukeException.badDeadlineDate();
            }
            resultCommand = new DeadlineCommand(parsedDetails[0], parsedDetails[1]);
            mostRecentCommand = resultCommand;
            break;
        case "event":
            parsedDetails = details.split(" /at ", 2);
            if (parsedDetails.length < 2) {
                throw DukeException.badEventDate();
            }
            resultCommand = new EventCommand(parsedDetails[0], parsedDetails[1]);
            mostRecentCommand = resultCommand;
            break;
        case "delete":
            resultCommand = new DeleteCommand(Integer.parseInt(details) - 1);
            mostRecentCommand = resultCommand;
            break;
        case "undo":
            resultCommand = new UndoCommand(mostRecentCommand);
            break;
        default:
            throw DukeException.badCommand();
        }

        return resultCommand;
    }
}
