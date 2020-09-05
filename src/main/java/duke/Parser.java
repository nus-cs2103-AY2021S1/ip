package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;
import duke.exception.EmptyTaskException;
import duke.exception.InvalidDateException;
import duke.exception.NoIndexException;
import duke.exception.NoKeywordException;
import duke.exception.UnrecognizedTaskException;

/** Deals with making sense of user inputs. */
public class Parser {

    /**
     * Makes sense of user inputs.
     *
     * @param fullCommand The user input.
     * @return A Command for the bot to do.
     * @throws UnrecognizedTaskException If the first word is not a recognized command.
     * @throws NoIndexException          If the command is delete or done and the index is < 0
     *                                   or larger than the size of the task list.
     * @throws EmptyTaskException        If the command is an add task command such as todo,
     *                                   event, or deadline and is followed by no description.
     */
    public static Command parse(String fullCommand)
        throws UnrecognizedTaskException, NoIndexException, EmptyTaskException, NoKeywordException {

        assert fullCommand != null;

        // Removes unnecessary whitespace
        fullCommand = fullCommand.trim();

        // Determines the case for each command
        switch (getFirstWord(fullCommand)) {
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

            case "find":
                return new FindCommand(getKeywords(fullCommand));
                // Fallthrough

            case "done":
                return new DoneCommand(getTaskNumbers(fullCommand, "done"));
                // Fallthrough

            case "delete":
                return new DeleteCommand(getTaskNumbers(fullCommand, "delete"));
                // Fallthrough

            case "bye":
                return new ExitCommand();
                // Fallthrough

            default:
                throw new UnrecognizedTaskException();
                // Fallthrough
        }
    }

    /**
     * Returns the description of a task.
     *
     * @param fullCommand   The input given by the user.
     * @param firstWord     The command.
     * @return The task description.
     * @throws EmptyTaskException If the input is an empty string, or contains only whitespaces.
     */
    private static String getTask(String fullCommand, String firstWord) throws EmptyTaskException {
        try {
            return fullCommand.substring(firstWord.length() + 1).trim(); // take whitespace into account
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyTaskException();
        }
    }

    /**
     * Makes sense of a string that represents date time in ISO format.
     *
     * @param dateTimeString A String that represents the date time.
     * @return The date time in LocalDateTime.
     * @throws InvalidDateException If the dateTimeString is not in a valid date time format.
     */
    public static LocalDateTime getDateTime(String dateTimeString) throws InvalidDateException {

        assert dateTimeString != null;

        // Remove unnecessary whitespace
        dateTimeString = dateTimeString.trim();

        // Determine the dateTime format
        String isoFormat = "yyyy-mm-ddThh:mm:ss";
        String isoFormatShort = "yyyy-mm-ddThh:mm";
        boolean isIsoFormat = dateTimeString.length() == isoFormat.length()
            || dateTimeString.length() == isoFormatShort.length();

        try {

            if (isIsoFormat) {
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

    /**
     * Parses the taskNumbers given.
     *
     * @param fullCommand   The full command given by the user.
     * @param firstWord     The first word (command) given by the user.
     * @return The array of task numbers.
     * @throws NoIndexException If there are no integers following the command.
     */
    public static Integer[] getTaskNumbers(String fullCommand, String firstWord) throws NoIndexException {
        if (fullCommand.equalsIgnoreCase(firstWord)) {
            throw new NoIndexException(firstWord);
        }

        try {
            String[] taskNumbers = fullCommand.substring(firstWord.length()).trim().split(" ");
            return Stream.of(taskNumbers).map(Integer::valueOf).toArray(Integer[]::new);

        } catch (NumberFormatException numError) {
            throw new NoIndexException(firstWord);
        }
    }

    /**
     * Parses the keywords given (separated by whitespace).
     *
     * @param fullCommand The full command given by the user.
     * @return An array of keywords.
     */
    public static String[] getKeywords(String fullCommand) {
        return fullCommand.substring("find ".length()).trim().split(" ");
    }

    /**
     * Obtains the command (first word) given by the user.
     *
     * @param fullCommand The full command given by the user.
     * @return The command (first word).
     */
    public static String getFirstWord(String fullCommand) {
        return fullCommand.contains(" ")
            ? fullCommand.substring(0, fullCommand.indexOf(" ")).toLowerCase()
            : fullCommand.toLowerCase();
    }
}
