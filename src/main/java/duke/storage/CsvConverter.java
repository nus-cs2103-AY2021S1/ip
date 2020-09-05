package duke.storage;

import duke.exception.InvalidFileFormatException;
import duke.task.ComplexTask;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.ToDo;

/**
 * Converts the task in CSV format to a Task object.
 */
public class CsvConverter {

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
        String time = resultArr[2];
        boolean isDone = stringToBoolean(resultArr[3]);

        if (taskType.equals("TODO")) {
            return new ToDo(description, isDone);
        } else if (taskType.equals("EVENT")) {
            return new ComplexTask(description, isDone, TaskType.EVENT, time);
        } else if (taskType.equals("DEADLINE")) {
            return new ComplexTask(description, isDone, TaskType.DEADLINE, time);
        } else {
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
        String[] resultArr = input.split("\\s{2},", 4);

        if (resultArr.length < 4) {
            throw new InvalidFileFormatException();
        }
        resultArr[0] = resultArr[0].toUpperCase();
        resultArr[3] = resultArr[3].toLowerCase();
        String doneStatus = resultArr[3];
        boolean validStatus = doneStatus.equals("done") || doneStatus.equals("not done");

        if (!validStatus) {
            throw new InvalidFileFormatException();
        }
        return resultArr;
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
