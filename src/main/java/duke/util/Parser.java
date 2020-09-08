package duke.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TimedAddCommand;

/**
 * Represents a parser which parses user commands into Command objects.
 */
public class Parser {

    enum CommandType {
        BYE, LIST, FIND, DONE, DELETE, TODO, DEADLINE, EVENT
    }

    /**
     * Parses the given input and returns the corresponding command.
     *
     * @param input user input.
     * @return corresponding command.
     * @throws DukeException if there are any date/time parsing issues or unknown commands.
     */
    public static Command parse(String input) throws DukeException {

        assert input != null;
        String trimmedInput = input.trim();
        String command = trimmedInput.split(" ")[0];

        try {
            CommandType type = CommandType.valueOf(command.toUpperCase());
            switch (type) {
            case BYE:
                return new ByeCommand();
            case LIST:
                return new ListCommand();
            case FIND:
                String keyword = trimmedInput.substring(trimmedInput.indexOf(' ') + 1);
                return new FindCommand(keyword);
            case DONE:
                int doneIdx = Integer.parseInt(trimmedInput.split(" ")[1]) - 1;
                return new DoneCommand(doneIdx);
            case DELETE:
                int deleteIdx = Integer.parseInt(trimmedInput.split(" ")[1]) - 1;
                return new DeleteCommand(deleteIdx);
            case TODO:
                taskFormatCheck(command, trimmedInput);
                assert !trimmedInput.contains("/");
                int startOfDescription = trimmedInput.indexOf(' ') + 1;
                String description = trimmedInput.substring(startOfDescription);
                return new AddCommand(description);
            case DEADLINE:
            case EVENT:
                return getAddTimedCommand(trimmedInput, command);
            default:
                throw new DukeException("Oh dear! I'm sorry, but I don't know what that means :(");
            }
        } catch (DateTimeParseException ex) {
            throw new DukeException("Oh dear! Please format the date and time as yyyy/MM/dd HHmm!");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            throw new DukeException("Oh dear! Please give me a number!");
        } catch (IllegalArgumentException ex) {
            throw new DukeException("Oh dear! I'm sorry, but I don't know what that means :(");
        }
    }

    /**
     * Returns a timed command.
     *
     * @param input user input.
     * @param command type of command.
     * @throws DukeException if there are any formatting issues.
     */
    private static TimedAddCommand getAddTimedCommand(String input, String command) throws DukeException {

        taskFormatCheck(command, input);
        assert input.contains("/");

        int startOfInfo = input.indexOf(' ') + 1;
        String info = input.substring(startOfInfo);

        int endOfDescription = info.indexOf('/') - 1;
        String description = info.substring(0, endOfDescription);

        int startOfMeta = info.indexOf('/') + 4;
        String meta = info.substring(startOfMeta);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        boolean hasTime = meta.contains(" ");
        if (hasTime) {
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmm");
            String dateAsString = meta.substring(0, meta.indexOf(' '));
            String timeAsString = meta.substring(meta.indexOf(' ') + 1);

            LocalDate date = LocalDate.parse(dateAsString, dateFormat);
            LocalTime time = LocalTime.parse(timeAsString, timeFormat);

            return new TimedAddCommand(command, description, date, time);
        } else {
            LocalDate date = LocalDate.parse(meta, dateFormat);
            return new TimedAddCommand(command, description, date);
        }
    }

    /**
     * Checks for any task formatting issues
     *
     * @param type type of task.
     * @param input user input.
     * @throws DukeException if there are any formatting issues.
     */
    private static void taskFormatCheck(String type, String input) throws DukeException {

        assert type != null && input != null;
        assert type.equals("todo") || type.equals("deadline") || type.equals("event");

        int idxOfSpace = input.indexOf(' ');
        int idxOfMeta = input.indexOf('/');
        int infoLength = idxOfMeta - (idxOfSpace + 1);

        boolean hasNoSpace = idxOfSpace == -1;
        boolean hasTimestampOnly = idxOfMeta != -1 && infoLength < 1;
        boolean hasNoDescription = hasNoSpace || hasTimestampOnly;

        if (hasNoDescription) {
            throw new DukeException("Oh dear! A task description cannot be empty!");
        }

        String info = input.substring(input.indexOf(' ') + 1);

        if (type.equals("todo") && info.contains("/")) {
            throw new DukeException("Oh dear! A todo shouldn't contain a timestamp!");
        }
        if (type.equals("deadline") && !info.contains("/by")) {
            throw new DukeException("Oh dear! A deadline must contain '/by'!");
        }
        if (type.equals("event") && !info.contains("/at")) {
            throw new DukeException("Oh dear! An event must contain '/at'!");
        }

        boolean isTimedTask = type.equals("deadline") || type.equals("event");
        boolean hasNoTimestamp = isTimedTask && input.substring(idxOfMeta).length() < 5;

        if (type.equals("deadline") && hasNoTimestamp) {
            throw new DukeException("Oh dear! A deadline must contain a timestamp!");
        }
        if (type.equals("event") && hasNoTimestamp) {
            throw new DukeException("Oh dear! An event must contain a timestamp!");
        }
    }
}
