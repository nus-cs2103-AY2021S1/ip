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

    private static boolean isEmptyFromIdx(String userInput, int idx) {
        return userInput.length() == idx || userInput.substring(idx).isBlank();
    }

    private static int parseTaskIndex(String userInput, int taskIdx) throws DukeException {
        try {
            return Integer.parseInt(userInput.substring(taskIdx + 1)) - 1;
        } catch (StringIndexOutOfBoundsException ex) {
            throw new DukeException("task index is empty");
        } catch (NumberFormatException ex) {
            throw new DukeException("task index is not a valid number");
        }
    }

    private static AddCommand parseTaskWithDate(String userInput, TaskType taskType, String dateIndicator) throws TaskException {
        int startIdxofDateIndicator = userInput.indexOf(dateIndicator);
        int endIdxofDateIndicator = startIdxofDateIndicator + dateIndicator.length();
        boolean isEmptyTime = startIdxofDateIndicator == -1 || isEmptyFromIdx(userInput, endIdxofDateIndicator);
        if (isEmptyTime) {
            throw new TaskException(taskType, "time", TaskExceptionType.EMPTY);
        }

        int startIdxOfDescription = taskType.toString().length();
        boolean isEmptyDescription = userInput.substring(startIdxOfDescription, startIdxofDateIndicator).isBlank();
        if (isEmptyDescription) {
            throw new TaskException(taskType, "description", TaskExceptionType.EMPTY);
        }

        Date date = parseDate(userInput.substring(endIdxofDateIndicator));
        if (date == null) {
            throw new TaskException(taskType, "time", TaskExceptionType.FORMAT);
        }

        String description = userInput.substring(startIdxOfDescription, startIdxofDateIndicator);
        return new AddCommand(taskType, description, date);
    }

    private static AddCommand parseAddTask(String userInput) throws TaskException {
        if (userInput.startsWith(TaskType.TODO.toString())) {
            if (isEmptyFromIdx(userInput, TaskType.TODO.toString().length())) {
                throw new TaskException(TaskType.TODO, "description", TaskExceptionType.EMPTY);
            }
            String description = userInput.substring(TaskType.TODO.toString().length());
            return new AddCommand(TaskType.TODO, description, null);
        } else if (userInput.startsWith(TaskType.DEADLINE.toString())) {
            return parseTaskWithDate(userInput, TaskType.DEADLINE, " /by ");
        } else if (userInput.startsWith(TaskType.EVENT.toString())) {
            return parseTaskWithDate(userInput, TaskType.EVENT, " /at ");
        } else {
            assert true: "Task type could not be identified.";
            return null; // won't hit
        }

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
        if (userInput.equals(ListCommand.toInputString())) {
            return new ListCommand();
        } else if (userInput.startsWith(DoneCommand.toInputString())) {
            int taskIdx = parseTaskIndex(userInput, DoneCommand.toInputString().length());
            return new DoneCommand(taskIdx);
        } else if (userInput.startsWith(DeleteCommand.toInputString())) {
            int taskIdx = parseTaskIndex(userInput, DeleteCommand.toInputString().length());
            return new DeleteCommand(taskIdx);
        } else if (userInput.startsWith(ListDateCommand.toInputString())) {
            if (isEmptyFromIdx(userInput, ListDateCommand.toInputString().length())) {
                throw new DukeException("time is empty");
            }
            Date date = parseDate(userInput.substring(ListDateCommand.toInputString().length()));
            if (date == null) {
                throw new DukeException("time is the wrong format");
            }
            return new ListDateCommand(date);
        } else if (userInput.startsWith(ListKeywordCommand.toInputString())) {
            if (isEmptyFromIdx(userInput, ListKeywordCommand.toInputString().length())) {
                throw new DukeException("keyword is empty");
            }
            String keyWord = userInput.substring(ListKeywordCommand.toInputString().length());
            return new ListKeywordCommand(keyWord);
        } else if (userInput.equals(ExitCommand.toInputString())) {
            return new ExitCommand();
        } else if (userInput.startsWith(TaskType.TODO.toString()) || userInput.startsWith(TaskType.DEADLINE.toString()) || userInput.startsWith(TaskType.EVENT.toString())){
            return parseAddTask(userInput);
        } else {
            throw new DukeException("I don't know what that means");
        }
    }
}
