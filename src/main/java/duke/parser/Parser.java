package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;
import duke.exception.DukeException;

/**
 * Implements user input parser.
 *
 * @author Audrey Felicio Anwar
 */
public class Parser {
    private enum Commands {
        BYE,
        LIST,
        DONE,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        FIND,
    }
    private static String errorMessage = null;

    private static Command handleDoneOrDelete(Commands commandType, int index) throws DukeException {
        Command command = null;
        switch (commandType) {
        case DONE:
            command = new DoneCommand(index);
            break;
        case DELETE:
            command = new DeleteCommand(index);
            break;
        default:
            throw new DukeException(" Command type not found");
        }
        return command;
    }

    private static Command handleAddTask(Commands commandType, String input) throws DukeException {
        String description;
        String[] contents;
        LocalDate time;
        Command command = null;
        switch (commandType) {
        case TODO:
            description = input.substring(5);
            command = new ToDoCommand(description);
            break;
        case DEADLINE:
            contents = input.substring(9).split(" /by ");
            if (contents.length <= 1) {
                errorMessage = " Deadline date cannot be empty :(";
                break;
            }
            description = contents[0];
            time = LocalDate.parse(contents[1]);
            command = new DeadlineCommand(description, time);
            break;
        case EVENT:
            contents = input.substring(6).split(" /at ");
            if (contents.length <= 1) {
                errorMessage = " Event date cannot be empty :(";
                break;
            }
            description = contents[0];
            time = LocalDate.parse(contents[1]);
            command = new EventCommand(description, time);
            break;
        default:
            throw new DukeException(" Command type not found");
        }
        return command;
    }

    /**
     * Parses user input into command.
     *
     * @param input User input.
     * @return Command to be executed.
     * @throws DukeException If there is parsing error.
     */
    public static Command parse(String input) throws DukeException {
        try {
            String[] separated = input.split("\\s+");
            Commands commandType = Commands.valueOf(separated[0].toUpperCase());
            Command command = null;
            switch (commandType) {
            case BYE:
                command = new ByeCommand();
                break;
            case LIST:
                command = new ListCommand();
                break;
            case DONE:
            case DELETE:
                if (separated.length <= 1) {
                    errorMessage = " Task index must be specified :(";
                    break;
                }
                try {
                    int index = Integer.parseInt(separated[1]);
                    command = handleDoneOrDelete(commandType, index);
                } catch (NumberFormatException error) {
                    errorMessage = " Task index must be an integer :(";
                }
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                if (separated.length <= 1) {
                    errorMessage = " Task description cannot be empty :(";
                    break;
                }
                command = handleAddTask(commandType, input);
                break;
            case FIND:
                if (separated.length <= 1) {
                    errorMessage = " Keyword cannot be empty :(";
                    break;
                }
                String keyword = input.substring(5);
                command = new FindCommand(keyword);
                break;
            default:
                throw new DukeException(" Command type not found");
            }
            if (errorMessage != null) {
                throw new DukeException(errorMessage);
            }
            return command;
        } catch (IllegalArgumentException error) {
            throw new DukeException(" Command not recognized :(");
        } catch (DateTimeParseException error) {
            throw new DukeException(" I cannot recognize the date you put in :(");
        } finally {
            errorMessage = null;
        }
    }
}
