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
    // adapted from https://stackoverflow.com/questions/4024544/
    // how-to-parse-dates-in-multiple-formats-using-simpledateformat
    public static Date parseDate(String str) {
        List<String> formatStrings = Arrays.asList("yyyy-M-dd", "dd/M/yyyy HHmm", "dd/M/yyyy", "MMM d yyyy");

        for (String formatString : formatStrings) {
            try {
                return new SimpleDateFormat(formatString).parse(str);
            } catch (ParseException e) {
                // if str is not of that date format, can continue to check if it is of other formats
                // try catch necessary otherwise there might be a RunTime error
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

    private static Priority parseTaskPriority(String userInput, TaskType taskType) throws TaskException {
        final String priorityIndicator = " /priority ";
        final String[] highPriority = {"high", "urgent"};
        final String[] mediumPriority = {"medium", "normal"};
        final String[] lowPriority = {"low"};
        int startIdxofPriorityIndicator = userInput.indexOf(priorityIndicator);
        if (startIdxofPriorityIndicator == -1) {
            return null; // no priority indicated (since its optional)
        }
        int endIdxofPriorityIndicator = startIdxofPriorityIndicator + priorityIndicator.length();

        try {
            String priority = userInput.substring(endIdxofPriorityIndicator).toLowerCase();
            boolean isHighPriority = Arrays.stream(highPriority).anyMatch(priority::equals);
            boolean isMediumPriority = Arrays.stream(mediumPriority).anyMatch(priority::equals);
            boolean isLowPriority = Arrays.stream(lowPriority).anyMatch(priority::equals);
            if (isHighPriority) {
                return Priority.HIGH;
            } else if (isMediumPriority) {
                return Priority.MEDIUM;
            } else if (isLowPriority) {
                return Priority.LOW;
            } else {
                throw new TaskException(taskType, "priority", TaskExceptionType.FORMAT);
            }
        } catch (StringIndexOutOfBoundsException ex) {
            throw new TaskException(taskType, "priority", TaskExceptionType.FORMAT);
        }

    }

    private static Date parseTaskDate(String userInput, TaskType taskType, String dateIndicator) throws TaskException {
        int startIdxofDateIndicator = userInput.indexOf(dateIndicator);
        if (startIdxofDateIndicator == -1) {
            throw new TaskException(taskType, "time", TaskExceptionType.EMPTY);
        }
        int startIdxofDate = startIdxofDateIndicator + dateIndicator.length();
        int endIdxofDate = userInput.substring(startIdxofDate).indexOf(" /") == -1
                ? userInput.length() : startIdxofDate + userInput.substring(startIdxofDate).indexOf(" /");
        if (endIdxofDate <= startIdxofDate || userInput.substring(startIdxofDate, endIdxofDate).isBlank()) {
            throw new TaskException(taskType, "time", TaskExceptionType.EMPTY);
        }

        Date date = parseDate(userInput.substring(startIdxofDate));
        if (date == null) {
            throw new TaskException(taskType, "time", TaskExceptionType.FORMAT);
        }

        return date;
    }

    private static String parseTaskDescription(String userInput, TaskType taskType) throws TaskException {
        int endIdx = userInput.indexOf(" /") == -1 ? userInput.length() : userInput.indexOf(" /");
        if (userInput.length() <= taskType.toString().length() || taskType.toString().length() + 1 >= endIdx) {
            throw new TaskException(taskType, "description", TaskExceptionType.EMPTY);
        }
        String description = userInput.substring(taskType.toString().length() + 1, endIdx);
        if (description.isBlank()) {
            throw new TaskException(taskType, "description", TaskExceptionType.EMPTY);
        }
        return description;
    }

    private static AddCommand parseAddTask(String userInput) throws TaskException {
        if (userInput.startsWith(TaskType.TODO.toString())) {
            String description = parseTaskDescription(userInput, TaskType.TODO);
            Priority priority = parseTaskPriority(userInput, TaskType.TODO);
            return new AddCommand(TaskType.TODO, description, null, priority);
        } else if (userInput.startsWith(TaskType.DEADLINE.toString())) {
            String description = parseTaskDescription(userInput, TaskType.DEADLINE);
            Date date = parseTaskDate(userInput, TaskType.DEADLINE, " /by ");
            Priority priority = parseTaskPriority(userInput, TaskType.TODO);
            return new AddCommand(TaskType.DEADLINE, description, date, priority);
        } else if (userInput.startsWith(TaskType.EVENT.toString())) {
            String description = parseTaskDescription(userInput, TaskType.EVENT);
            Date date = parseTaskDate(userInput, TaskType.EVENT, " /at ");
            Priority priority = parseTaskPriority(userInput, TaskType.EVENT);
            return new AddCommand(TaskType.EVENT, description, date, priority);
        } else {
            assert true : "Task type could not be identified.";
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
        } else if (userInput.startsWith(TaskType.TODO.toString()) || userInput.startsWith(TaskType.DEADLINE.toString())
                || userInput.startsWith(TaskType.EVENT.toString())) {
            return parseAddTask(userInput);
        } else {
            throw new DukeException("I don't know what that means");
        }
    }
}
