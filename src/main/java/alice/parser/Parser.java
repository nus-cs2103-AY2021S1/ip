package alice.parser;

import alice.command.ByeCommand;
import alice.command.ClearCommand;
import alice.command.Command;
import alice.command.DeadlineCommand;
import alice.command.DeleteCommand;
import alice.command.DoneCommand;
import alice.command.EventCommand;
import alice.command.HelpCommand;
import alice.command.InvalidCommandException;
import alice.command.ListCommand;
import alice.command.TodoCommand;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    private static final List<DateTimeFormatter> KNOWN_DT_FORMATS = createDateFormats();

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

    private static Command parseDoneInput(String inputIndex) throws InvalidCommandException {
        try {
            int index = Integer.parseInt(inputIndex) - 1;
            return new DoneCommand(index);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Don't play around. Give me a proper number!");
        }
    }

    private static Command parseDeleteInput(String inputIndex) throws InvalidCommandException {
        try {
            int index = Integer.parseInt(inputIndex) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Don't play around. Give me a proper number!");
        }
    }

    private static Command parseTodoInput(String arguments) throws InvalidCommandException {
        if (!arguments.isBlank()) {
            return new TodoCommand(arguments);
        } else {
            throw new InvalidCommandException("The todo description cannot be left empty.");
        }
    }

    private static Command parseDeadlineInput(String argument) throws InvalidCommandException {
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

    private static Command parseEventInput(String argument) throws InvalidCommandException {
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
