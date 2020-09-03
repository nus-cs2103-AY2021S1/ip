import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Parser takes in a variety of strings, makes sense of the strings and creates relevant objects.
 */
public class Parser {
    /**
     * Parses dates that are of the accepted date formats.
     *
     * @param str String to parse.
     * @return Date if successfully parsed, null if wrong date format.
     */
    public static Date parseDate(String str) {
        List<String> formatStrings = Arrays.asList("yyyy-M-dd", "dd/M/yyyy HHmm", "dd/M/yyyy", "MMM d yyyy");

        for (String formatString : formatStrings) {
            try {
                return new SimpleDateFormat(formatString).parse(str);
            } catch (ParseException e) {
                // if str is not of that date format, can continue to check if it is of other formats
                // try catch necessary otherwise there might be a RunTime error
                continue;
            }
        }

        return null;
    }

    /**
     * Parses in a user input that is a String and generates a command based on the input.
     *
     * @param userInput
     * @return Command based on userInput.
     * @throws DukeException
     * @throws TaskException
     */
    public static Command parse(String userInput) throws DukeException, TaskException {
        if (userInput.equals("list")) {
            return new ListCommand(null, null);
        } else if (userInput.startsWith("done")) {
            try {
                int idx = Integer.parseInt(userInput.substring(5)) - 1;
                return new DoneCommand(idx);
            } catch (StringIndexOutOfBoundsException | NumberFormatException ex) {
                throw new DukeException("task index is empty / not a valid number");
            }
        } else if (userInput.startsWith("delete")) {
            try {
                int idx = Integer.parseInt(userInput.substring(7)) - 1;
                return new DeleteCommand(idx);
            } catch (StringIndexOutOfBoundsException | NumberFormatException ex) {
                throw new DukeException("task index is empty / not a valid number");
            }
        } else if (userInput.startsWith("tasks due on")) {
            if (userInput.length() == 12 || userInput.substring(12).isBlank()
                    || parseDate(userInput.substring(13)) == null) {
                throw new DukeException("time is empty / of the wrong format");
            } else {
                return new ListCommand(parseDate(userInput.substring(13)), null);
            }
        } else if (userInput.startsWith("find")) {
            if (userInput.length() == 4 || userInput.substring(4).isBlank()) {
                throw new DukeException("keyword is empty");
            } else {
                return new ListCommand(null, userInput.substring(5));
            }
        } else if (userInput.equals("bye")) {
            return new ExitCommand();
        } else {
            if (userInput.startsWith("todo")) {
                if (userInput.length() == 4 || userInput.substring(4).isBlank()) {
                    throw new TaskException(TaskType.TODO, "description", TaskExceptionType.EMPTY);
                } else {
                    return new AddCommand(TaskType.TODO, userInput.substring(5), null);
                }
            } else if (userInput.startsWith("deadline")) {
                int idx = userInput.indexOf(" /by ");
                if (idx == -1 || userInput.substring(idx + 5).isBlank()) {
                    throw new TaskException(TaskType.DEADLINE, "time", TaskExceptionType.IDENTIFY);
                } else if (userInput.substring(9, idx).isBlank()) {
                    throw new TaskException(TaskType.DEADLINE, "description", TaskExceptionType.EMPTY);
                } else {
                    if (parseDate(userInput.substring(idx + 5)) == null) {
                        throw new TaskException(TaskType.DEADLINE, "time", TaskExceptionType.FORMAT);
                    } else {
                        return new AddCommand(TaskType.DEADLINE, userInput.substring(9, idx),
                                parseDate(userInput.substring(idx + 5)));
                    }
                }
            } else if (userInput.length() >= 5 && userInput.startsWith("event")) {
                int idx = userInput.indexOf(" /at ");
                if (idx == -1 || userInput.substring(idx + 5).isBlank()) {
                    throw new TaskException(TaskType.EVENT, "time", TaskExceptionType.IDENTIFY);
                } else if (idx <= 6 || userInput.substring(6, idx).isBlank()) {
                    throw new TaskException(TaskType.EVENT, "description", TaskExceptionType.EMPTY);
                } else {
                    if (parseDate(userInput.substring(idx + 5)) == null) {
                        throw new TaskException(TaskType.EVENT, "time", TaskExceptionType.FORMAT);
                    } else {
                        return new AddCommand(TaskType.EVENT, userInput.substring(6, idx),
                                parseDate(userInput.substring(idx + 5)));
                    }
                }
            } else {
                throw new DukeException("I don't know what that means");
            }
        }
    }
}
