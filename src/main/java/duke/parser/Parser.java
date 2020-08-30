package duke.parser;

import duke.commands.Command;
import duke.commands.CreateTaskCommand;
import duke.commands.DateFilterCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoCommand;
import duke.commands.ExitCommand;
import duke.commands.ListCommand;
import duke.exceptions.DukeException;
import duke.tasks.TaskType;

import java.time.LocalDate;

/**
 * Represents a parser that translate user input into Duke commands.
 * @version 1.0
 */
public class Parser {
    private static final String DATE_REGEX = "^(19|20)\\d\\d-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";

    /**
     * Translates user input into Duke commands.
     * Returns the duke command.
     *
     * @param userInput A String representation of the user input.
     * @return A Duke command.
     * @throws DukeException thrown if the parser does not understand the user input.
     */
    public static Command translate(String userInput) throws DukeException {
        Command command;
        String[] commandParaPair = userInput.split(" ", 2);
        switch (commandParaPair[0]) {
        case "bye":
            command = new ExitCommand();
            break;
        case "list":
            command = new ListCommand();
            break;
        case "on":
            if (commandParaPair.length < 2 || !commandParaPair[1].matches(DATE_REGEX)) {
                throw new DukeException("\tCannot recognise date.");
            } else {
                command = new DateFilterCommand(LocalDate.parse(commandParaPair[1]));
            }
            break;
        case "done":
            if (commandParaPair.length < 2 || !commandParaPair[1].matches("\\d+")) {
                throw new DukeException("\tCannot recognise task number.");
            } else {
                command = new DoCommand(Integer.parseInt(commandParaPair[1]));
            }
            break;
        case "delete":
            if (commandParaPair.length < 2 || !commandParaPair[1].matches("\\d+")) {
                throw new DukeException("\tCannot recognise task number.");
            } else {
                command = new DeleteCommand(Integer.parseInt(commandParaPair[1]));
            }
            break;
        case "todo":
            if (commandParaPair.length < 2) {
                throw new DukeException("\tCannot create todo without description.");
            } else {
                command = new CreateTaskCommand(TaskType.TODO, commandParaPair[1]);
            }
            break;
        case "deadline":
        case "event":
            if (commandParaPair.length < 2) {
                throw new DukeException("\tCannot create deadline / event without description.");
            } else {
                String[] descriptionDate = commandParaPair[1].split(" /by | /at ");
                if (descriptionDate.length < 2) {
                    throw new DukeException("\tCannot create deadline / event without date.");
                } else if (!descriptionDate[1].matches(DATE_REGEX)) {
                    throw new DukeException("\tCannot recognise date.");
                } else {
                    command = new CreateTaskCommand(TaskType.valueToType(commandParaPair[0]),
                            descriptionDate[0],
                            LocalDate.parse(descriptionDate[1]));
                }
            }
            break;
        default:
            throw new DukeException("\tCannot recognise command.");
        }

        return command;
    }
}
