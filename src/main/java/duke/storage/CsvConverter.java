package duke.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    private static final String dateTimeFormat = "MMM d yyyy / h.mm a";

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
        case "TODO":
            return new ToDo(description, isDone, timeFrame, dateTime);
        case "EVENT":
            return new Event(description, isDone, timeFrame, dateTime);
        case "DEADLINE":
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
        String[] resultArr = input.split("\\s{2},", 5);

        if (resultArr.length < 4) {
            throw new InvalidFileFormatException();
        }
        resultArr[0] = resultArr[0].toUpperCase();
        resultArr[4] = resultArr[4].toLowerCase();
        String doneStatus = resultArr[4];
        boolean validStatus = doneStatus.equals("done") || doneStatus.equals("not done");

        if (!validStatus) {
            throw new InvalidFileFormatException();
        }
        return resultArr;
    }

    private static LocalDateTime stringToDateTime(String input) throws InvalidFileFormatException {
        try {
            return LocalDateTime.parse(input, DateTimeFormatter.ofPattern(dateTimeFormat));
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
        return input.equals("done");
    }
}
