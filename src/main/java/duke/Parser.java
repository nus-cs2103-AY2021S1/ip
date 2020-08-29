package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.CommandKey;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;

import duke.exception.DukeDateTimeException;
import duke.exception.DukeException;
import duke.exception.DukeNoDescriptionException;
import duke.exception.DukeNoItemToDeleteException;
import duke.exception.DukeNoItemToMarkDoneException;
import duke.exception.DukeUnknownCommandException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a parser for parsing user inputs.
 */
public class Parser {
    private LocalDate parseDate(String inputDate) throws DukeDateTimeException {
        try {
            return LocalDate.parse(inputDate, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeException e) {
            throw new DukeDateTimeException(inputDate);
        }
    }

    private LocalTime parseTime(String inputTime) throws DukeDateTimeException {
        try {
            return LocalTime.parse(inputTime, DateTimeFormatter.ISO_LOCAL_TIME);
        } catch (DateTimeException e) {
            throw new DukeDateTimeException(inputTime);
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
        String[] processedInput = input.split(" ");
        String key = processedInput[0];
        if (CommandKey.equalsCommandKey(input, CommandKey.EXIT)) {
            return new ExitCommand();
        } else if (CommandKey.equalsCommandKey(input, CommandKey.LIST)) {
            return new ListCommand();
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
        } else if (CommandKey.equalsCommandKey(key, CommandKey.TODO)) {
            if (processedInput.length == 1) {
                throw new DukeNoDescriptionException(input);
            }
            String taskDescription = processedInput[1];
            return new AddCommand(key, taskDescription);
        } else if (CommandKey.equalsCommandKey(key, CommandKey.DEADLINE)) {
            if (processedInput.length == 1) {
                throw new DukeNoDescriptionException(input);
            }
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
        } else if (CommandKey.equalsCommandKey(key, CommandKey.EVENT)) {
            if (processedInput.length == 1) {
                throw new DukeNoDescriptionException(input);
            }
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
        } else {
            throw new DukeUnknownCommandException(input);
        }
    }
}
