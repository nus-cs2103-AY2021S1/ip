package alice.util;

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
        // Create a formatter for each known patterns to be used for parsing dates
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
        // Split the userInput into the command word and subsequent command details
        String[] arr = userInput.strip().split(" ", 2);
        String cmd = arr[0];
        String argument = arr.length == 2 ? arr[1] : "";

        if (ListCommand.hasCommandWord(cmd)) {
            // Command to display task list
            return ListCommand.createCommand();

        } else if (FindCommand.hasCommandWord(cmd)) {
            // Command to find specific tasks
            return FindCommand.createCommand(argument);

        } else if (ClearCommand.hasCommandWord(cmd)) {
            // Command to clear task list
            return ClearCommand.createCommand();

        } else if (HelpCommand.hasCommandWord(cmd)) {
            // Command to get list of commands
            return HelpCommand.createCommand();

        } else if (DoneCommand.hasCommandWord(cmd)) {
            // Command to mark task as done
            return DoneCommand.createCommand(argument);

        } else if (DeleteCommand.hasCommandWord(cmd)) {
            // Command to mark task as done
            return DeleteCommand.createCommand(argument);

        } else if (TodoCommand.hasCommandWord(cmd)) {
            // Command to add to-do
            return TodoCommand.createCommand(argument);

        } else if (DeadlineCommand.hasCommandWord(cmd)) {
            // Command to add deadline
            return DeadlineCommand.createCommand(argument);

        } else if (EventCommand.hasCommandWord(cmd)) {
            // Command to add event
            return EventCommand.createCommand(argument);

        } else if (ByeCommand.hasCommandWord(cmd)) {
            // Command to exit program
            return ByeCommand.createCommand();

        } else {
            // Invalid command
            throw new InvalidCommandException("Sorry I cannot register that command!\n"
                    + "Use 'help' command to see the lists of available command");
        }
    }

    /**
     * Parses the datetime input given by the user into the implied {@code LocalDateTime}.
     *
     * @param dateTimeString the user input containing a date and time.
     * @return the {@code LocalDateTime} indicated by the user input.
     * @throws InvalidCommandException if the datetime input given by the user does not match any known patterns.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) throws InvalidCommandException {
        for (int i = 0; i < KNOWN_DT_FORMATS.size(); i++) {
            try {
                return LocalDateTime.parse(dateTimeString, KNOWN_DT_FORMATS.get(i));
            } catch (DateTimeParseException ex) {
                // Ignore exception, fall through expected
            }
        }

        throw new InvalidCommandException("Invalid datetime! Please use 24h format for time");
    }
}
