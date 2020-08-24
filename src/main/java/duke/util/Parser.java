package duke.util;

import duke.command.*;
import duke.exception.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(String input) throws InvalidDescriptionException, InvalidCommandException,
            InvalidEventException, InvalidDeadlineException, InvalidDateFormatException {
        String[] splitString = input.split(" ", 2);
        String command = splitString[0];
        int num;
        switch (command) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            case "todo":
            case "deadline":
            case "event":
                if (splitString.length == 1) {
                    throw new InvalidDescriptionException();
                }
                return parseTask(command, splitString[1].trim());
            case "done":
                num = Integer.parseInt(splitString[1]);
                return new DoneCommand(num);
            case "delete":
                num = Integer.parseInt(splitString[1]);
                return new DeleteCommand(num);
            case "find":
                String matchString = splitString[1].trim();
                return new FindCommand(matchString);
            default:
                throw new InvalidCommandException();
        }
    }

    private static Command parseTask(String command, String description)
            throws InvalidEventException, InvalidDeadlineException, InvalidCommandException, InvalidDateFormatException {
        String splitted[];
        LocalDate date;
        try {
            switch (command) {
                case "todo":
                    return new AddCommand(new Todo(description));
                case "deadline":
                    splitted = description.split(" /by ", 2);
                    if (splitted.length == 1) {
                        throw new InvalidDeadlineException();
                    }
                    date = LocalDate.parse(splitted[1]);
                    return new AddCommand(new Deadline(splitted[0], date));
                case "event":
                    splitted = description.split(" /at ", 2);
                    if (splitted.length == 1) {
                        throw new InvalidEventException();
                    }
                    date = LocalDate.parse(splitted[1]);
                    return new AddCommand(new Event(splitted[0], date));
                default:
                    throw new InvalidCommandException();
            }
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException();
        }
    }
}
