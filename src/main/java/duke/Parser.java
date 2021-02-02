package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.CommandKey;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;

import duke.command.ReminderCommand;
import duke.exception.DukeDateTimeException;
import duke.exception.DukeException;
import duke.exception.DukeNoDescriptionException;
import duke.exception.DukeNoItemToDeleteException;
import duke.exception.DukeNoItemToMarkDoneException;
import duke.exception.DukeNoKeywordException;
import duke.exception.DukeTooManyKeywordsException;
import duke.exception.DukeUnknownCommandException;

/**
 * Represents a parser for parsing user inputs.
 */
public class Parser {
    private LocalDate parseDate(String inputDate) throws DukeDateTimeException {
        assert inputDate != null : "Input date cannot be null";
        try {
            return LocalDate.parse(inputDate, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeException e) {
            throw new DukeDateTimeException(inputDate);
        }
    }

    private LocalTime parseTime(String inputTime) throws DukeDateTimeException {
        assert inputTime != null : "Input time cannot be null";
        try {
            return LocalTime.parse(inputTime, DateTimeFormatter.ISO_LOCAL_TIME);
        } catch (DateTimeException e) {
            throw new DukeDateTimeException(inputTime);
        }
    }

    private AddCommand createAddCommand(String key, String input) throws DukeDateTimeException {
        if (CommandKey.equalsCommandKey(key, CommandKey.TODO)) {
            String taskDescription = input.substring(5);
            return new AddCommand(key, taskDescription);
        } else if (CommandKey.equalsCommandKey(key, CommandKey.DEADLINE)) {
            String[] taskDetails = input.substring(9).split(" /by ");
            String task = taskDetails[0];
            String[] by = taskDetails[1].split(" ");
            try {
                LocalDate date = parseDate(by[0]);
                LocalTime time = parseTime(by[1]);
                return new AddCommand(key, task, date, time);
            } catch (DukeDateTimeException e) {
                throw e;
            }
        } else {
            String[] taskDetails = input.substring(6).split(" /at ");
            String task = taskDetails[0];
            String[] by = taskDetails[1].split(" ");
            try {
                LocalDate date = parseDate(by[0]);
                LocalTime time = parseTime(by[1]);
                return new AddCommand(key, task, date, time);
            } catch (DukeDateTimeException e) {
                throw e;
            }
        }
    }

    /**
     * Parses and validates user input before returning a command to be executed.
     *
     * @param input The user input.
     * @return Command to be executed.
     * @throws DukeException If the user input is invalid.
     */
    public Command parse(String input) throws DukeException {
        String[] processedInput = input.trim().split(" ");
        String key = processedInput[0];
        if (CommandKey.equalsCommandKey(input, CommandKey.EXIT)) {
            return new ExitCommand();
        } else if (CommandKey.equalsCommandKey(input, CommandKey.LIST)) {
            return new ListCommand();
        } else if (CommandKey.equalsCommandKey(input, CommandKey.REMINDER)) {
            return new ReminderCommand();
        } else if (CommandKey.equalsCommandKey(processedInput[0], CommandKey.DELETE)) {
            if (processedInput.length == 1) {
                throw new DukeNoItemToDeleteException(input);
            }
            return new DeleteCommand(Integer.parseInt(processedInput[1]));
        } else if (CommandKey.equalsCommandKey(processedInput[0], CommandKey.DONE)) {
            if (processedInput.length == 1) {
                throw new DukeNoItemToMarkDoneException(input);
            }
            return new DoneCommand(Integer.parseInt(processedInput[1]));
        } else if (CommandKey.equalsCommandKey(processedInput[0], CommandKey.FIND)) {
            if (processedInput.length == 1) {
                throw new DukeNoKeywordException(input);
            }
            if (processedInput.length > 2) {
                throw new DukeTooManyKeywordsException(input);
            }
            return new FindCommand(processedInput[1]);
        } else if (CommandKey.equalsCommandKey(key, CommandKey.TODO)) {
            if (processedInput.length == 1) {
                throw new DukeNoDescriptionException(input);
            }
            return createAddCommand(key, input);
        } else if (CommandKey.equalsCommandKey(key, CommandKey.DEADLINE)) {
            if (processedInput.length == 1) {
                throw new DukeNoDescriptionException(input);
            }
            return createAddCommand(key, input);
        } else if (CommandKey.equalsCommandKey(key, CommandKey.EVENT)) {
            if (processedInput.length == 1) {
                throw new DukeNoDescriptionException(input);
            }
            return createAddCommand(key, input);
        } else {
            throw new DukeUnknownCommandException(input);
        }
    }
}
