package duke;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents a Parser responsible for parsing Strings
 * to generate tasks and commands for the Duke bot.
 */
public class Parser {

    /** Format for date and time used when user inputs a task */
    private static final DateTimeFormatter INPUT_DATE_TIME_FORMAT
            = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    /** Format for date and time used when Duke saves a task list */
    private static final DateTimeFormatter SAVED_DATE_TIME_FORMAT
            = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");

    /**
     * Parses a line read from the task list from the storage space
     * and generates a corresponding Task to be added to the live TaskList.
     *
     * @param fullTask The line from the .txt file to be parsed
     * @return The corresponding Task.
     * @throws DukeException If the line contains an incorrect format.
     */
    public static Task parseTask(String fullTask) throws DukeException {
        String[] splitTask = fullTask.split(" \\| ");

        String type = splitTask[0];
        String description = splitTask[2];
        boolean isDone = Integer.parseInt(splitTask[1]) == 1;

        switch (type) {
        case "T":
            return new Todo(description, isDone);
        case "D":
            LocalDateTime by = LocalDateTime.parse(splitTask[3], SAVED_DATE_TIME_FORMAT);
            return new Deadline(description, isDone, by.toLocalDate(), by.toLocalTime());
        case "E":
            LocalDateTime at = LocalDateTime.parse(splitTask[3], SAVED_DATE_TIME_FORMAT);
            return new Event(description, isDone, at.toLocalDate(), at.toLocalTime());
        default:
            throw new DukeException("Unable to read task.");
        }
    }

    /**
     * Parses a line read from the user input and generates a
     * corresponding Command to be executed by Duke.
     *
     * @param fullCommand The user input to be parsed.
     * @return The corresponding Command
     * @throws DukeException If the command type cannot be recognised or is in the wrong format
     */
    public static Command parseCommand(String fullCommand) throws DukeException {
        String[] splitCommand = fullCommand.split(" ", 2);
        String type = splitCommand[0];

        try {
            switch (type) {
            case "list":
                return new ListCommand();
            case "bye":
                return new ExitCommand();
            case "todo":
                return new AddCommand(new Todo(splitCommand[1]));
            case "deadline":
                String[] splitDeadline = splitCommand[1].split("/by");
                String deadlineDescription = splitDeadline[0].trim();
                LocalDateTime by = LocalDateTime.parse(splitDeadline[1].trim(), INPUT_DATE_TIME_FORMAT);
                return new AddCommand(new Deadline(deadlineDescription, by.toLocalDate(), by.toLocalTime()));
            case "event":
                String[] splitEvent = splitCommand[1].split("/at");
                String eventDescription = splitEvent[0].trim();
                LocalDateTime at = LocalDateTime.parse(splitEvent[1].trim(), INPUT_DATE_TIME_FORMAT);
                return new AddCommand(new Event(eventDescription, at.toLocalDate(), at.toLocalTime()));
            case "done":
                return new DoneCommand(Integer.parseInt(splitCommand[1]));
            case "delete":
                return new DeleteCommand(Integer.parseInt(splitCommand[1]));
            default:
                throw new DukeException("Unknown command type.");
            }

        } catch (DateTimeException dateTimeException) {
            throw new DukeException("Please enter the date and time in the format 'DD-MM-YYYY HHMM'!");
        } catch (Exception exception) {
            throw new DukeException("Unable to read command. Please enter it in the correct format!");
        }
    }

}
