package duke.storage;

import static duke.util.Keyword.CSV_SEPARATOR;
import static duke.util.Keyword.DATE_TIME_OUTPUT_FORMAT;
import static duke.util.Keyword.KEYWORD_DEADLINE;
import static duke.util.Keyword.KEYWORD_EVENT;
import static duke.util.Keyword.KEYWORD_TODO;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidFileFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Converts the task in CSV format to a Task object.
 */
public class CsvConverter {

    private static final String DONE = "done";
    private static final String NOT_DONE = "not done";

    /**
     * Creates a task from the CSV format of the task.
     *
     * @param input CSV format of task.
     * @return Task corresponding to the csv string.
     */
    protected static Task parseToTask(String input) throws InvalidFileFormatException {
        String[] resultArr = parseString(input);

        String taskType = resultArr[0];
        String description = resultArr[1];
        String timeFrame = resultArr[2];
        LocalDateTime dateTime = stringToDateTime(resultArr[3]);
        boolean isDone = stringToBoolean(resultArr[4]);

        switch (taskType) {
        case KEYWORD_TODO:
            return new ToDo(description, isDone, timeFrame, dateTime);
        case KEYWORD_EVENT:
            return new Event(description, isDone, timeFrame, dateTime);
        case KEYWORD_DEADLINE:
            return new Deadline(description, isDone, timeFrame, dateTime);
        default:
            throw new InvalidFileFormatException();
        }
    }

    /**
     * Parses the input csv string to an array.
     *
     * @param input Input string.
     * @return Array format of task.
     * @throws InvalidFileFormatException If there are errors in the csv format.
     */
    private static String[] parseString(String input) throws InvalidFileFormatException {
        int expectedLength = 5;
        String[] resultArr = input.split(CSV_SEPARATOR, expectedLength);

        if (resultArr.length < expectedLength) {
            throw new InvalidFileFormatException();
        }
        resultArr[0] = resultArr[0].toUpperCase();
        resultArr[4] = resultArr[4].toLowerCase();
        String doneStatus = resultArr[4];
        boolean validStatus = doneStatus.equals(DONE) || doneStatus.equals(NOT_DONE);

        if (!validStatus) {
            throw new InvalidFileFormatException();
        }
        return resultArr;
    }

    /**
     * Converts the string time in the .csv file to a {@code LocalDateTime} object.
     *
     * @param input Input string.
     * @return LocalDateTime object.
     * @throws InvalidFileFormatException If the input is not in format that can be converted.
     */
    private static LocalDateTime stringToDateTime(String input) throws InvalidFileFormatException {
        try {
            return LocalDateTime.parse(input, DATE_TIME_OUTPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidFileFormatException();
        }
    }

    /**
     * Converts the done status to a boolean.
     *
     * @param input Done status.
     * @return True if done, false if not done.
     */
    private static boolean stringToBoolean(String input) {
        return input.equals(DONE);
    }
}
