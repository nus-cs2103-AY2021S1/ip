package main.java.farrell.duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import main.java.farrell.duke.DukeException;

public class CommandParser {
    /**
     * Prases the input string to the corresponding Command object.
     * Parameters provided to the input string will be provided to the object.
     * @param input The input string to parse.
     * @return The corresponding Command object.
     * @throws DukeException If there is a problem with the command or parameters.
     */
    public static Command parse(String input) throws DukeException {
        String[] splitInput = input.split(" ", 2);
        CommandType commandType = CommandType.enumFromString(splitInput[0]);

        if (commandType == CommandType.UNDEFINED) {
            throw new DukeException("I don't understand this command!");
        }

        if (splitInput.length == 1 && commandType.requiresParams()) {
            throw new DukeException("This command has parameters!\n" + commandType.getUsageString());
        }

        String[] parameters = splitInput.length > 1 ? splitInput[1].split("\\s\\/[^\\s]*\\s") : new String[0];

        if (parameters.length < commandType.getMinParams()
                || parameters.length > commandType.getMaxParams()) {
            throw new DukeException("Invalid number of parameters!\n" + commandType.getUsageString());
        }

        switch (commandType) {
        case TODO:
            return new ToDoCommand(parameters[0]);
        case EVENT:
            try {
                return new EventCommand(parameters[0], LocalDate.parse(parameters[1]));
            } catch (DateTimeParseException exception) {
                throw new DukeException("Invalid date format! Please use YYYY-MM-DD");
            }
        case DEADLINE:
            try {
                return new DeadlineCommand(parameters[0], LocalDate.parse(parameters[1]));
            } catch (DateTimeParseException exception) {
                throw new DukeException("Invalid date format! Please use YYYY-MM-DD");
            }
        case DONE:
            return new DoneCommand(Integer.parseInt(parameters[0]));
        case LIST:
            return new ListCommand();
        case DELETE:
            return new DeleteCommand(Integer.parseInt(parameters[0]));
        case BYE:
            return new ByeCommand();
        case FIND:
            return new FindCommand(parameters[0]);
        case SORT:
            if (parameters.length == 1) {
                return new SortCommand(parameters[0].toUpperCase());
            } else {
                boolean sortAscending = parameters[1].toUpperCase().equals("ASCENDING");
                return new SortCommand(parameters[0].toUpperCase(), sortAscending);
            }
        default:
            throw new DukeException("This command has not yet been implemented!");
        }
    }
}
