package alice.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import alice.command.ByeCommand;
import alice.command.ClearCommand;
import alice.command.Command;
import alice.command.DeadlineCommand;
import alice.command.DeleteCommand;
import alice.command.DoneCommand;
import alice.command.EventCommand;
import alice.command.FindCommand;
import alice.command.HelpCommand;
import alice.command.InvalidCommandException;
import alice.command.ListCommand;
import alice.command.TodoCommand;

/**
 * Represents a parser that makes sense of user input.
 */
public class Parser {
    /**
     * List of known datetime formats that ALICE accepts.
     **/
    private static final List<DateTimeFormatter> KNOWN_DT_FORMATS = createDateFormats();

    /**
     * Creates the list of formatter that accepts a specified list of known datetime patterns.
     *
     * @return list of DateTimeFormatter with acceptable date time format.
     */
    private static List<DateTimeFormatter> createDateFormats() {
        // List of acceptable date time format with optional time/year
        List<String> knownPatterns = Arrays.asList(
                "d/M[/uuuu][ HHmm]", "d-M[-uuuu][ HHmm]",
                "M/d[/uuuu][ HHmm]", "M-d[-uuuu][ HHmm]",
                "uuuu/M/d[ HHmm]", "uuuu-M-d[ HHmm]",
                "d-MMM[-uuuu][ HHmm]", "d MMM[ HHmm]"
        );

        List<DateTimeFormatter> knownFormats = new ArrayList<>();
        for (int i = 0; i < knownPatterns.size(); i++) {
            knownFormats.add(
                    new DateTimeFormatterBuilder()
                            .appendPattern(knownPatterns.get(i))
                            .parseDefaulting(ChronoField.YEAR, LocalDateTime.now().getYear())
                            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                            .toFormatter()
            );
        }
        return knownFormats;
    }

    /**
     * Parses the user input into the appropriate commands.
     *
     * @param userInput the input from user.
     * @return the appropriate command indicated by the user.
     * @throws InvalidCommandException if the userInput does not match any commands and/or its command signature.
     */
    public static Command parseCommand(String userInput) throws InvalidCommandException {
        String[] arr = userInput.trim().split(" ", 2);
        String cmd = arr[0];
        String argument;
        if (arr.length == 2) {
            argument = arr[1];
        } else {
            argument = "";
        }

        if (ListCommand.hasCommandWord(cmd)) {
            // Command to display task list
            return new ListCommand();
        } else if (FindCommand.hasCommandWord(cmd)) {
            // Command to find specific tasks
            return parseFindKeywords(argument);
        } else if (ClearCommand.hasCommandWord(cmd)) {
            // Command to clear task list
            return new ClearCommand();
        } else if (HelpCommand.hasCommandWord(cmd)) {
            // Command to get list of commands
            return new HelpCommand();
        } else if (DoneCommand.hasCommandWord(cmd)) {
            // Command to mark task as done
            return parseDoneInput(argument);
        } else if (DeleteCommand.hasCommandWord(cmd)) {
            // Command to mark task as done
            return parseDeleteInput(argument);
        } else if (TodoCommand.hasCommandWord(cmd)) {
            // Command to add to-do
            return parseTodoInput(argument);
        } else if (DeadlineCommand.hasCommandWord(cmd)) {
            // Command to add deadline
            return parseDeadlineInput(argument);
        } else if (EventCommand.hasCommandWord(cmd)) {
            // Command to add event
            return parseEventInput(argument);
        } else if (ByeCommand.hasCommandWord(cmd)) {
            return new ByeCommand();
        } else {
            // Invalid command
            throw new InvalidCommandException("Sorry I cannot register that command!\n"
                    + "Use 'help' command to see the lists of available command");
        }
    }

    /**
     * Parses the keywords given by the user for use by the find command.
     *
     * @param keywords the string of keywords provided by user
     * @return the <code>FindCommand</code> with the user's keywords
     * @throws InvalidCommandException if the keywords provided is an empty string
     */
    private static FindCommand parseFindKeywords(String keywords) throws InvalidCommandException {
        if (!keywords.isBlank()) {
            return new FindCommand(keywords.trim().split(" "));
        } else {
            throw new InvalidCommandException("The keyword for find cannot be left empty.");
        }
    }

    /**
     * Parses the task number given by the user for the done command.
     *
     * @param inputIndex the task number input given by user.
     * @return the <code>DoneCommand</code> with the verified task number.
     * @throws InvalidCommandException if the task number provided is invalid.
     */
    private static DoneCommand parseDoneInput(String inputIndex) throws InvalidCommandException {
        try {
            int index = Integer.parseInt(inputIndex) - 1;
            return new DoneCommand(index);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Don't play around. Give me a proper number!");
        }
    }

    /**
     * Parses the task number given by the user for the delete command.
     *
     * @param inputIndex the task number input given by user.
     * @return the <code>DeleteCommand</code> with the verified task number.
     * @throws InvalidCommandException if the task number provided is invalid.
     */
    private static DeleteCommand parseDeleteInput(String inputIndex) throws InvalidCommandException {
        try {
            int index = Integer.parseInt(inputIndex) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Don't play around. Give me a proper number!");
        }
    }

    /**
     * Parses the description given by the user for creating a new todo.
     *
     * @param argument the description input given by user.
     * @return the <code>TodoCommand</code> with the verified description.
     * @throws InvalidCommandException if the user gives an empty description.
     */
    private static TodoCommand parseTodoInput(String argument) throws InvalidCommandException {
        if (!argument.isBlank()) {
            return new TodoCommand(argument);
        } else {
            throw new InvalidCommandException("The todo description cannot be left empty.");
        }
    }

    /**
     * Parses the details given by the user for creating a new deadline.
     *
     * @param argument the deadline details input given by user.
     * @return the <code>DeadlineCommand</code> with the indicated details.
     * @throws InvalidCommandException if the user gives an invalid description and/or datetime.
     */
    private static DeadlineCommand parseDeadlineInput(String argument) throws InvalidCommandException {
        String[] arguments = argument.split(" /by ", 2);
        if (arguments.length == 2 && !arguments[1].isBlank()) {
            String description = arguments[0];
            String dateTime = arguments[1];
            LocalDateTime deadlineDt = parseDateTime(dateTime);
            return new DeadlineCommand(description, deadlineDt);
        } else if (argument.isBlank()) {
            // Empty description
            throw new InvalidCommandException("The deadline description cannot be left empty.");
        } else if (argument.endsWith("/by")) {
            // Empty date
            throw new InvalidCommandException("You cannot create an deadline without the date.");
        } else {
            // No /by marker
            throw new InvalidCommandException("I can't find the deadline date. "
                    + "Did you forget to add '/by'?");
        }
    }

    /**
     * Parses the details given by the user for creating a new event.
     *
     * @param argument the event details input given by user.
     * @return the <code>EventCommand</code> with the indicated details.
     * @throws InvalidCommandException if the user gives an invalid description and/or datetime.
     */
    private static EventCommand parseEventInput(String argument) throws InvalidCommandException {
        String[] arguments = argument.split(" /on ", 2);
        if (arguments.length == 2 && !arguments[1].isBlank()) {
            String description = arguments[0];
            String dateTime = arguments[1];

            LocalDateTime eventDateTime = parseDateTime(dateTime);
            return new EventCommand(description, eventDateTime);
        } else if (argument.isBlank()) {
            // Empty event description
            throw new InvalidCommandException("The event description cannot be left empty.");
        } else if (argument.endsWith("/on")) {
            // Empty start-end time
            throw new InvalidCommandException("You cannot create an event without a date/time.");
        } else {
            // No /on marker
            throw new InvalidCommandException("I can't find the date/time of the event. "
                    + "Did you forget to add '/on'?");
        }
    }

    /**
     * Parses the datetime input given by the user into the implied <code>LocalDateTime</code>.
     *
     * @param dateTimeString the user input containing a date and time.
     * @return the <code>LocalDateTime</code> indicated by the user input.
     * @throws InvalidCommandException if the datetime input given by the user does not match any known patterns.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) throws InvalidCommandException {
        for (int i = 0; i < KNOWN_DT_FORMATS.size(); i++) {
            try {
                return LocalDateTime.parse(dateTimeString, KNOWN_DT_FORMATS.get(i));
            } catch (DateTimeParseException ex) {
                // ignore
            }
        }

        throw new InvalidCommandException("Invalid datetime! Please use 24h format for time");
    }
}
