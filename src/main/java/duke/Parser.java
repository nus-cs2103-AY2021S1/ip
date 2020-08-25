package duke;

import duke.command.*;
import duke.exception.EmptyTaskException;
import duke.exception.InvalidDateException;
import duke.exception.NoIndexException;
import duke.exception.UnrecognizedTaskException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(String fullCommand)
            throws UnrecognizedTaskException, NoIndexException, EmptyTaskException {

        fullCommand = fullCommand.trim();

        String firstWord = fullCommand.contains(" ")
                ? fullCommand.substring(0, fullCommand.indexOf(" ")).toLowerCase()
                : fullCommand.toLowerCase();

        switch (firstWord) {
            case "todo":
                return new ToDoCommand(getTask(fullCommand, "todo"));
            // Fallthrough
            case "event":
                return new EventCommand(getTask(fullCommand, "event"));
            // Fallthrough
            case "deadline":
                return new DeadlineCommand(getTask(fullCommand, "deadline"));
            // Fallthrough
            case "list":
                return new ListCommand(fullCommand);
            // Fallthrough
            case "done":

                if (fullCommand.equalsIgnoreCase("done")) {
                    throw new NoIndexException("done");
                } else {

                    try {
                        int taskNo = Integer.parseInt(fullCommand.substring(5));
                        return new DoneCommand(taskNo);

                    } catch (NumberFormatException numError) {
                        throw new NoIndexException("done");
                    }
                }
                // Fallthrough
            case "delete":
                if (fullCommand.equalsIgnoreCase("delete")) {
                    throw new NoIndexException("delete");
                } else {

                    try {
                        int taskNo = Integer.parseInt(fullCommand.substring(7));
                        return new DeleteCommand(taskNo);

                    } catch (NumberFormatException numError) {
                        throw new NoIndexException("delete");
                    }

                }
                // Fallthrough
            case "bye":
                return new ExitCommand();
                // Fallthrough
            default:
                throw new UnrecognizedTaskException();
                // Fallthrough
        }
    }

    private static String getTask(String fullCommand, String firstWord) throws EmptyTaskException {
        try {
            return fullCommand.substring(firstWord.length() + 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyTaskException();
        }
    }

    public static LocalDateTime getDateTime(String dateTimeString) throws InvalidDateException {

        dateTimeString = dateTimeString.trim();

        try {

            if (dateTimeString.length() == 19 || dateTimeString.length() == 16) {
                return LocalDateTime.parse(dateTimeString);
            } else if (dateTimeString.contains("-")) {
                return LocalDateTime.of(LocalDate.parse(dateTimeString), LocalTime.parse("23:59"));
            } else if (dateTimeString.contains(":")) {
                return LocalDateTime.of(LocalDate.now(), LocalTime.parse(dateTimeString));
            } else {
                throw new InvalidDateException();
            }

        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }
}
