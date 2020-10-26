package duke.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.command.AddCommand;
import duke.command.AliasCommand;
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

    public enum CommandType {
        BYE, LIST, FIND, DONE, DELETE, TODO, DEADLINE, EVENT, ALIAS, UNKNOWN
    }

    /**
     * Parses the given input and returns the corresponding command.
     *
     * @param input user input.
     * @return corresponding command.
     * @throws DukeException if there are any date/time parsing issues or unknown commands.
     */
    public static Command parse(String input, AliasMap aliasMap) throws DukeException {

        assert input != null;
        String trimmedInput = input.trim();
        String command = trimmedInput.split(" ")[0];

        try {
            CommandType type = aliasMap.getCommand(command);

            if (type.equals(CommandType.UNKNOWN)) {
                type = CommandType.valueOf(command.toUpperCase());
            }

            assert !type.equals(CommandType.UNKNOWN);

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
                return getAddCommand(trimmedInput, type);
            case DEADLINE:
            case EVENT:
                return getAddTimedCommand(trimmedInput, type.toString().toLowerCase());
            case ALIAS:
                return getAliasCommand(trimmedInput);
            default:
                throw new DukeException("UNKNOWN COMMAND DETECTED");
            }
        } catch (DateTimeParseException ex) {
            throw new DukeException("INVALID DATE/TIME FORMAT DETECTED - USE 'yyyy/MM/dd HHmm'");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            throw new DukeException("NO NUMBER DETECTED");
        } catch (IllegalArgumentException ex) {
            throw new DukeException("UNKNOWN COMMAND DETECTED");
        }
    }

    /**
     * Returns a timed command.
     *
     * @param input user input.
     * @param command type of command.
     * @throws DukeException if there are any formatting issues.
     */
    private static AddCommand getAddCommand(String input, CommandType command) throws DukeException {
        checkTaskFormat(command.toString().toLowerCase(), input);
        assert !input.contains("/");
        int startOfDescription = input.indexOf(' ') + 1;
        String description = input.substring(startOfDescription);
        return new AddCommand(description);
    }

    /**
     * Returns a timed command.
     *
     * @param input user input.
     * @param command type of command.
     * @throws DukeException if there are any formatting issues.
     */
    private static TimedAddCommand getAddTimedCommand(String input, String command) throws DukeException {

        checkTaskFormat(command, input);
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
     * Returns a timed command.
     *
     * @param input user input.
     * @throws DukeException if there are any formatting issues.
     */
    private static AliasCommand getAliasCommand(String input) throws DukeException {

        checkAliasFormat(input);

        String mapping = input.substring(input.indexOf(' '));
        String alias = mapping.split("=")[0].trim();
        String typeAsString = mapping.split("=")[1].trim();
        CommandType commandToMap = CommandType.valueOf(typeAsString.toUpperCase());

        return new AliasCommand(alias, commandToMap);
    }

    /**
     * Checks for any task formatting issues
     *
     * @param type type of task.
     * @param input user input.
     * @throws DukeException if there are any formatting issues.
     */
    private static void checkTaskFormat(String type, String input) throws DukeException {

        assert type != null && input != null;
        assert type.equals("todo") || type.equals("deadline") || type.equals("event");

        int idxOfSpace = input.indexOf(' ');
        int idxOfMeta = input.indexOf('/');
        int infoLength = idxOfMeta - (idxOfSpace + 1);

        boolean hasNoSpace = idxOfSpace == -1;
        boolean hasTimestampOnly = idxOfMeta != -1 && infoLength < 1;
        boolean hasNoDescription = hasNoSpace || hasTimestampOnly;

        if (hasNoDescription) {
            throw new DukeException("NO TASK DESCRIPTION DETECTED");
        }

        String info = input.substring(input.indexOf(' ') + 1);

        if (type.equals("todo") && info.contains("/")) {
            throw new DukeException("TIMESTAMP DETECTED IN TODO");
        }
        if (type.equals("deadline") && !info.contains("/by")) {
            throw new DukeException("NO '/by' DETECTED IN DEADLINE");
        }
        if (type.equals("event") && !info.contains("/at")) {
            throw new DukeException("NO '/at' DETECTED IN EVENT");
        }

        boolean isTimedTask = type.equals("deadline") || type.equals("event");
        boolean hasNoTimestamp = isTimedTask && input.substring(idxOfMeta).length() < 5;

        if (type.equals("deadline") && hasNoTimestamp) {
            throw new DukeException("NO TIMESTAMP DETECTED IN DEADLINE");
        }
        if (type.equals("event") && hasNoTimestamp) {
            throw new DukeException("NO TIMESTAMP DETECTED IN EVENT");
        }
    }

    /**
     * Checks for any alias formatting issues
     *
     * @param input user input.
     * @throws DukeException if there are any formatting issues.
     */
    private static void checkAliasFormat(String input) throws DukeException {

        boolean hasNoSpace = !input.contains(" ");
        boolean hasNoEquals = !input.contains("=");

        if (hasNoSpace) {
            throw new DukeException("NO MAPPING DETECTED");
        } else if (hasNoEquals) {
            throw new DukeException("NO '=' DETECTED IN ALIAS");
        }

        String mapping = input.substring(input.indexOf(' '));
        int idxOfEquals = mapping.indexOf('=');
        String alias = mapping.substring(0, idxOfEquals).trim();
        boolean moreThanOneWord = alias.contains(" ");
        boolean hasNoAlias = alias.length() == 0;

        String aliasInUpperCase = alias.toUpperCase();
        boolean isACommandType = false;
        for (CommandType type : CommandType.values()) {
            if (aliasInUpperCase.equals(type.name())) {
                isACommandType = true;
                break;
            }
        }

        if (moreThanOneWord) {
            throw new DukeException("MORE THAN ONE WORD DETECTED IN KEYWORD");
        } else if (hasNoAlias) {
            throw new DukeException("NO KEYWORD DETECTED");
        } else if (isACommandType) {
            throw new DukeException("COMMAND AS KEYWORD DETECTED");
        }

        boolean endsWithEquals = idxOfEquals + 1 >= mapping.length();

        if (endsWithEquals) {
            throw new DukeException("NO COMMAND TYPE DETECTED");
        }

        String type = mapping.substring(idxOfEquals + 1).trim();
        boolean hasNoType = type.length() == 0;

        if (hasNoType) {
            throw new DukeException("NO COMMAND TYPE DETECTED");
        }

        try {
            CommandType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new DukeException("INVALID COMMAND TYPE: " + type);
        }
    }
}
