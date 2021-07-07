package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.function.BiFunction;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;
import duke.command.UpdateCommand;
import duke.exception.DukeException;

/**
 * Implements user input parser.
 *
 * @author Audrey Felicio Anwar
 */
public class Parser {
    private static final String SEPARATOR = System.getProperty("line.separator");
    private static final BiFunction<String[], Integer, Boolean> CHECK_LENGTH = (s, i) -> s.length <= i;
    private enum Commands {
        BYE,
        LIST,
        DONE,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        FIND,
        UPDATE,
    }
    private static String errorMessage = null;

    private static Command handleDoneOrDelete(Commands commandType, String[] separatedWords) {
        Command command = null;
        if (CHECK_LENGTH.apply(separatedWords, 1)) {
            errorMessage = " Task index must be specified :(";
            return command;
        }
        int index = Integer.parseInt(separatedWords[1]);
        switch (commandType) {
        case DONE:
            command = new DoneCommand(index);
            break;
        case DELETE:
            command = new DeleteCommand(index);
            break;
        default:
            errorMessage = " Command type not found";
        }
        return command;
    }

    private static Command handleAddTask(Commands commandType, String input, String timeIndicator) {
        StringBuilder description = new StringBuilder();
        LocalDate time;
        Command command = null;
        String[] separatedWords = timeIndicator.equals("") ? new String[]{input} : input.split(timeIndicator);
        System.out.println(separatedWords[0]);
        String[] getDescription = separatedWords[0].split(" ");
        if (CHECK_LENGTH.apply(getDescription, 1)) {
            errorMessage = " Task description cannot be empty :(";
            return command;
        }
        if (CHECK_LENGTH.apply(separatedWords, 1) && !commandType.equals(Commands.TODO)) {
            errorMessage = " Date cannot be empty :(";
            return command;
        }
        for (int i = 1; i < getDescription.length; i++) {
            description.append(getDescription[i]).append(" ");
        }
        time = timeIndicator.equals("") ? null : LocalDate.parse(separatedWords[1].trim());
        switch (commandType) {
        case TODO:
            command = new ToDoCommand(description.toString().trim());
            break;
        case DEADLINE:
            command = new DeadlineCommand(description.toString().trim(), time);
            break;
        case EVENT:
            command = new EventCommand(description.toString().trim(), time);
            break;
        default:
            errorMessage = " Command type not found";
        }
        return command;
    }

    private static Command handleFind(String[] separatedWords) {
        Command command = null;
        if (CHECK_LENGTH.apply(separatedWords, 1)) {
            errorMessage = " Keyword cannot be empty :(";
            return command;
        }
        String keyword = separatedWords[1];
        command = new FindCommand(keyword);
        return command;
    }

    private static Command handleUpdate(String[] separatedWords) {
        Command command = null;
        if (CHECK_LENGTH.apply(separatedWords, 3)) {
            errorMessage = " Details cannot be empty :(";
            return command;
        }
        int index = Integer.parseInt(separatedWords[1]);
        String type = separatedWords[2];
        String newDetails = Arrays.stream(Arrays.copyOfRange(separatedWords, 3, separatedWords.length))
                .reduce("", (accumulated, current) -> accumulated + current + " ").trim();
        command = new UpdateCommand(index, type, newDetails);
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
            String[] separatedWords = input.split("\\s+");
            Commands commandType = Commands.valueOf(separatedWords[0].toUpperCase());
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
                command = handleDoneOrDelete(commandType, separatedWords);
                break;
            case TODO:
                command = handleAddTask(commandType, input, "");
                break;
            case DEADLINE:
                command = handleAddTask(commandType, input, "/by");
                break;
            case EVENT:
                command = handleAddTask(commandType, input, "/at");
                break;
            case FIND:
                command = handleFind(separatedWords);
                break;
            case UPDATE:
                command = handleUpdate(separatedWords);
                break;
            default:
                throw new DukeException(" Command type not found");
            }
            if (errorMessage != null) {
                throw new DukeException(errorMessage);
            }
            return command;
        } catch (NumberFormatException error) {
            throw new DukeException(" Task index must be an integer :(");
        } catch (IllegalArgumentException error) {
            throw new DukeException(" Command not recognized :(");
        } catch (DateTimeParseException error) {
            throw new DukeException(" I cannot recognize the date you put in :(");
        } finally {
            errorMessage = null;
        }
    }
}
