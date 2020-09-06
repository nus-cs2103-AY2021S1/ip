import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser that will make sense of user commands and break down
 * to various components for ease of processing.
 */
public class Parser {
    /**
     * Returns an array of string where the commands for tasks have been broken
     * into its various parts for ease of processing.
     * @param command The user command.
     * @return An array of string which has been processed.
     * @throws InvalidDateAndTimeException If input date/time is invalid.
     * @throws InvalidTaskNumber If an improper integer task number is called in delete and done.
     * @throws NoDescriptionException If there is no task description for addition of tasks.
     * @throws NotTaskException If an invalid command is being input.
     */
    public String[] parse(String command) throws InvalidDateAndTimeException,
            InvalidTaskNumber, NoDescriptionException, NotTaskException {
        String[] cmd = command.split(" ");
        switch(cmd[0].toLowerCase()) {
        case "bye":
        case "list":
        case "find":
            return cmd;
        case "print":
            return checkValidDate(cmd);
        case "done":
        case "delete":
            return checkValidTaskNum(cmd);
        case "todo":
            return checkValidTodo(command, cmd);
        case "deadline":
            return checkValidDeadline(command, cmd);
        case "event":
            return checkValidEvent(command, cmd);
        default:
            throw new NotTaskException();
        }
    }

    /**
     * Checks if the command has a valid date.
     * @param cmd The command in array from user.
     * @return The command if date is valid.
     * @throws InvalidDateAndTimeException if an invalid date is called.
     */
    private String[] checkValidDate(String[] cmd) throws InvalidDateAndTimeException {
        try {
            LocalDate.parse(cmd[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return cmd;
        } catch (DateTimeParseException e) {
            throw new InvalidDateAndTimeException();
        }
    }

    /**
     * Checks if the user has given a proper task number as input.
     * @param cmd The command in array from user.
     * @return The command if task number is an integer.
     * @throws InvalidTaskNumber if the task number is not an integer.
     */
    private String[] checkValidTaskNum(String[] cmd) throws InvalidTaskNumber {
        try {
            Integer.parseInt(cmd[1]);
            return cmd;
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumber();
        }
    }

    /**
     * Checks if the to-do command has a description and processes it.
     * @param command The original command from user.
     * @param cmd The command in array from user.
     * @return A processed command in the format: [to-do, task description, notes(if present)].
     * @throws NoDescriptionException if no description was provided by user.
     */
    private String[] checkValidTodo(String command, String[] cmd) throws NoDescriptionException {
        if (cmd.length < 2) {
            throw new NoDescriptionException(cmd[0]);
        } else {
            String[] detectNotes = command.split("/");
            boolean hasNotes = detectNotes.length > 1;
            if (hasNotes) {
                return new String[]{cmd[0], detectNotes[0].replaceFirst(cmd[0] + " ", "")
                        , detectNotes[1]};
            } else {
                return new String[]{cmd[0], detectNotes[0].replaceFirst(cmd[0] + " ", "")};
            }
        }
    }

    /**
     * Checks if the deadline command has a description and valid date time and processes it.
     * @param command The original command from user.
     * @param cmd The command in array from user.
     * @return A processed command in the format: [deadline, task description, date, time, notes (if present)].
     * @throws NoDescriptionException if no description was provided by user.
     * @throws InvalidDateAndTimeException if an invalid date is called.
     */
    private String[] checkValidDeadline(String command, String[] cmd) throws NoDescriptionException,
            InvalidDateAndTimeException {
        if (cmd.length < 2 || cmd[1].contains("/")) {
            throw new NoDescriptionException(cmd[0]);
        } else {
            try {
                String[] splitBySlash = command.split("/");
                String[] getDateAndTime = checkDeadlineInputDateTime(splitBySlash[1]);
                String date = getDateAndTime[0];
                String time = getDateAndTime[1];
                LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalTime.parse(time, DateTimeFormatter.ofPattern("HHmm"));
                boolean hasNotes = splitBySlash.length > 2;
                if (hasNotes) {
                    return new String[]{cmd[0], splitBySlash[0].replaceFirst(cmd[0] + " ", ""),
                            date, time, splitBySlash[2]};
                } else {
                    return new String[]{cmd[0], splitBySlash[0].replaceFirst(cmd[0] + " ", ""),
                            date, time};
                }
            } catch (DateTimeParseException | InvalidDateAndTimeException e) {
                throw new InvalidDateAndTimeException();
            }
        }
    }

    /**
     * Checks if the user has correctly input date and time for deadline.
     * @param dateTimeInput the string to be parsed for date and time.
     * @return an array {date, time}.
     * @throws InvalidDateAndTimeException if date or time detected in the incorrect format.
     */
    private String[] checkDeadlineInputDateTime(String dateTimeInput) throws InvalidDateAndTimeException {
        try {
            return new String[]{dateTimeInput.split(" ")[0], dateTimeInput.split(" ")[1]};
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidDateAndTimeException();
        }
    }
    /**
     * Checks if the event command has a description and valid date time and processes it.
     * @param command The original command from user.
     * @param cmd The command in array from user.
     * @return A processed command in the format: [event, task description, date, start time,
     * end time, notes (if present)].
     * @throws NoDescriptionException if no description was provided by user.
     * @throws InvalidDateAndTimeException if an invalid date time is called.
     */
    private String[] checkValidEvent(String command, String[] cmd) throws NoDescriptionException,
            InvalidDateAndTimeException {
        if (cmd.length < 2 || cmd[1].contains("/")) {
            throw new NoDescriptionException(cmd[0]);
        } else {
            try {
                String[] splitBySlash = command.split("/");
                String[] getDateAndTime = checkEventInputDateTime(splitBySlash[1]);
                String date = getDateAndTime[0];
                String startTime = getDateAndTime[1];
                String endTime = getDateAndTime[2];
                LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HHmm"));
                LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HHmm"));
                boolean hasNotes = splitBySlash.length > 2;
                if (hasNotes) {
                    return new String[]{cmd[0], splitBySlash[0].replaceFirst(cmd[0] + " ", ""),
                            date, startTime, endTime, splitBySlash[2]};
                } else {
                    return new String[]{cmd[0], splitBySlash[0].replaceFirst(cmd[0] + " ", ""),
                            date, startTime, endTime};
                }
            } catch (DateTimeParseException | InvalidDateAndTimeException e) {
                throw new InvalidDateAndTimeException();
            }
        }
    }

    /**
     * Checks if the user has correctly input date and time for event.
     * @param dateTimeInput the string to be parsed for date and time.
     * @return an array {date, startTime, endTime}.
     * @throws InvalidDateAndTimeException if date or time detected in the incorrect format.
     */
    private String[] checkEventInputDateTime(String dateTimeInput) throws InvalidDateAndTimeException {
        try {
            return new String[]{dateTimeInput.split(" ")[0],
                    dateTimeInput.split(" ")[1].split("-")[0],
                    dateTimeInput.split(" ")[1].split("-")[1]};
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidDateAndTimeException();
        }
    }
}
