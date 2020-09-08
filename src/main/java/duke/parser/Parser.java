package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.stream.Stream;

import duke.command.Command;
import duke.command.CommandType;
import duke.exception.DukeException;
import duke.ui.Ui;
import duke.util.IndexDescriptionPair;

/**
 * Parser that parses raw user input into digestible output
 */
public class Parser {
    /**
     * Returns the description excluding the command type in the command string<br />
     * CommandString Format: `command_type` `description_to_be_returned`
     *
     * @param commandType   of the commandString
     * @param commandString to be parsed to get the description
     * @return a String that represent the description excluding the command type in the command
     * string
     * @throws DukeException when the description is not found
     */
    public static String getTaskDescription(CommandType commandType, String commandString)
            throws DukeException {
        String[] cmdParts = commandString.split(commandType.toString() + " ", 2);

        if (cmdParts.length != 2 || cmdParts[1].equals("")) {
            throw Ui.taskDescriptionEmptyException(commandType);
        }

        return cmdParts[1];
    }

    /**
     * Returns the index in the command string<br />
     * CommandString Format: `command_type` `index_to_be_returned`
     *
     * @param commandType   of the commandString
     * @param commandString to be parsed to get the index
     * @return an int that represent the index in the command string
     * @throws DukeException when the index is invalid, i.e. when it cannot be parsed as an integer
     */
    public static int getTaskTargetIndex(CommandType commandType, String commandString)
        throws DukeException {
        String description = Parser.getTaskDescription(commandType, commandString);
        try {
            return Integer.parseInt(description);
        } catch (NumberFormatException e) {
            throw Ui.taskIndexFormatException(commandType);
        }
    }

    /**
     * Returns the index and additional description in the command string<br />
     * CommandString Format: `command_type` `index_to_be_returned` `description_to_be_returned`
     *
     * @param commandType   of the commandString
     * @param commandString to be parsed to get the index description pair
     * @return an int that represent the index in the command string
     * @throws DukeException when the index is invalid, i.e. when it cannot be parsed as an integer
     */
    public static IndexDescriptionPair getTaskTargetIndexDescription(CommandType commandType, String commandString)
        throws DukeException {
        DukeException indexDescriptionFormatException = Ui.taskIndexDescriptionFormatException(commandType, "<tags>");
        String indexDescription = Parser.getTaskDescription(commandType, commandString);

        String[] rawDescriptionPair = indexDescription.split(" ", 2);

        if (rawDescriptionPair.length != 2 || rawDescriptionPair[1].equals("")) {
            throw indexDescriptionFormatException;
        }

        try {
            int index = Integer.parseInt(rawDescriptionPair[0]);
            String description = rawDescriptionPair[1];
            return new IndexDescriptionPair(index, description);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw indexDescriptionFormatException;
        }
    }

    /**
     * Returns a LocalDate object by parsing a date string <br>
     * dateString should be of format yyyy-mm-dd
     *
     * @param dateString to be parsed into the LocalDate
     * @return a LocalDate object that is a representation of the dateString argument
     * @throws DukeException when the date is invalid
     */
    public static LocalDate parseDateString(String dateString) throws DukeException {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw Ui.taskDateFormatException();
        }
    }

    /**
     * Returns a Command object after parsing the command string
     *
     * @param commandString a string representing the command
     * @return a Command object parsed from the command string
     * @throws DukeException when the command string is invalid
     */
    public static Command parseCommandString(String commandString) throws DukeException {
        Optional<CommandType> commandType =
                Stream.of(CommandType.values()).filter(command -> command.is(commandString)).findAny();

        if (commandType.isPresent()) {
            return Command.create(commandType.get(), commandString);
        } else {
            throw Ui.commandInvalidException();
        }
    }
}
