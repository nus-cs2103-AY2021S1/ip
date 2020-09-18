package duke.parser;

import java.time.LocalDate;

import duke.commands.*;
import duke.exceptions.DukeException;
import duke.tasks.TaskType;

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
            command = createExitCommand();
            break;
        case "list":
            command = createListCommand();
            break;
        case "on":
            command = createDateFilterCommand(commandParaPair);
            break;
        case "done":
            command = createDoCommand(commandParaPair);
            break;
        case "snooze":
            command = createSnoozeCommand(commandParaPair);
            break;
        case "delete":
            command = createDeleteCommand(commandParaPair);
            break;
        case "find":
            command = createFindCommand(commandParaPair);
            break;
        case "todo":
            command = createTodoTaskCommand(commandParaPair);
            break;
        case "deadline":
        case "event":
            command = createDeadlineEventTaskCommand(commandParaPair);
            break;
        default:
            throw new DukeException("\tCannot recognise command.");
        }
        return command;
    }

    private static Command createExitCommand() {
        return new ExitCommand();
    }

    private static Command createListCommand() {
        return new ListCommand();
    }

    private static Command createDateFilterCommand(String[] commandParaPair) throws DukeException {
        if (commandParaPair.length < 2 || !commandParaPair[1].matches(DATE_REGEX)) {
            throw new DukeException("\tCannot recognise date.");
        } else {
            return new DateFilterCommand(LocalDate.parse(commandParaPair[1]));
        }
    }

    private static Command createDoCommand(String[] commandParaPair) throws DukeException {
        if (commandParaPair.length < 2 || !commandParaPair[1].matches("\\d+")) {
            throw new DukeException("\tCannot recognise task number.");
        } else {
            return new DoCommand(Integer.parseInt(commandParaPair[1]));
        }
    }

    private static Command createSnoozeCommand(String[] commandParaPair) throws DukeException {
        if (commandParaPair.length < 2) {
            throw new DukeException("\tCannot recognise task number.");
        } else {
            String[] indexDatePair = commandParaPair[1].split(" ");
            int taskIndex = Integer.parseInt(indexDatePair[0]);
            LocalDate newDate = LocalDate.parse(indexDatePair[1]);
            return new SnoozeCommand(taskIndex, newDate);
        }
    }

    private static Command createDeleteCommand(String[] commandParaPair) throws DukeException {
        if (commandParaPair.length < 2 || !commandParaPair[1].matches("\\d+")) {
            throw new DukeException("\tCannot recognise task number.");
        } else {
            return new DeleteCommand(Integer.parseInt(commandParaPair[1]));
        }
    }

    private static Command createFindCommand(String[] commandParaPair) throws DukeException {
        if (commandParaPair.length < 2) {
            throw new DukeException("\tNothing specified to find.");
        } else {
            return new FindCommand(commandParaPair[1]);
        }
    }

    private static Command createTodoTaskCommand(String[] commandParaPair) throws DukeException {
        if (commandParaPair.length < 2) {
            throw new DukeException("\tCannot create todo without description.");
        } else {
            return new CreateTaskCommand(TaskType.TODO, commandParaPair[1]);
        }
    }

    private static Command createDeadlineEventTaskCommand(String[] commandParaPair) throws DukeException {
        if (commandParaPair.length < 2) {
            throw new DukeException("\tCannot create deadline / event without description.");
        } else {
            String[] descriptionDate = commandParaPair[1].split(" /by | /at ");
            if (descriptionDate.length < 2) {
                throw new DukeException("\tCannot create deadline / event without date.");
            } else if (!descriptionDate[1].matches(DATE_REGEX)) {
                throw new DukeException("\tCannot recognise date.");
            } else {
                return new CreateTaskCommand(TaskType.valueToType(commandParaPair[0]),
                        descriptionDate[0],
                        LocalDate.parse(descriptionDate[1]));
            }
        }
    }
}
