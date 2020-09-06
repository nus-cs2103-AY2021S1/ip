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
     * @return A processed command in the format: [to-do, task description].
     * @throws NoDescriptionException if no description was provided by user.
     */
    private String[] checkValidTodo(String command, String[] cmd) throws NoDescriptionException {
        if (cmd.length < 2) {
            throw new NoDescriptionException(cmd[0]);
        } else {
            return new String[]{cmd[0], command.replaceFirst(cmd[0] + " ", "")};
        }
    }

    /**
     * Checks if the deadline command has a description and valid date time and processes it.
     * @param command The original command from user.
     * @param cmd The command in array from user.
     * @return A processed command in the format: [deadline, task description, date, time].
     * @throws NoDescriptionException if no description was provided by user.
     * @throws InvalidDateAndTimeException if an invalid date is called.
     */
    private String[] checkValidDeadline(String command, String[] cmd) throws NoDescriptionException,
            InvalidDateAndTimeException {
        if (cmd.length < 2 || cmd[1].contains("/")) {
            throw new NoDescriptionException(cmd[0]);
        } else {
            try {
                LocalDate.parse(cmd[cmd.length - 2], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalTime.parse(cmd[cmd.length - 1], DateTimeFormatter.ofPattern("HHmm"));
                String[] splitBySlash = command.split("/");
                return new String[]{cmd[0], splitBySlash[0].replaceFirst(cmd[0] + " ", ""),
                        cmd[cmd.length - 2], cmd[cmd.length - 1]};
            } catch (DateTimeParseException e) {
                throw new InvalidDateAndTimeException();
            }
        }
    }

    /**
     * Checks if the event command has a description and valid date time and processes it.
     * @param command The original command from user.
     * @param cmd The command in array from user.
     * @return A processed command in the format: [event, task description, date, start time, end time].
     * @throws NoDescriptionException if no description was provided by user.
     * @throws InvalidDateAndTimeException if an invalid date time is called.
     */
    private String[] checkValidEvent(String command, String[] cmd) throws NoDescriptionException,
            InvalidDateAndTimeException {
        if (cmd.length < 2 || cmd[1].contains("/")) {
            throw new NoDescriptionException(cmd[0]);
        } else {
            try {
                LocalDate.parse(cmd[cmd.length - 2], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalTime.parse(cmd[cmd.length - 1].split("-")[0], DateTimeFormatter.ofPattern("HHmm"));
                LocalTime.parse(cmd[cmd.length - 1].split("-")[1], DateTimeFormatter.ofPattern("HHmm"));
                String[] splitBySlash = command.split("/");
                return new String[]{cmd[0], splitBySlash[0].replaceFirst(cmd[0] + " ", ""),
                        cmd[cmd.length - 2], cmd[cmd.length - 1].split("-")[0],
                        cmd[cmd.length - 1].split("-")[1]};
            } catch (DateTimeParseException e) {
                throw new InvalidDateAndTimeException();
            }
        }
    }
}
