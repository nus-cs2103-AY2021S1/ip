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
     * @return Task.
     */
    protected static Task parseToTask(String input) throws InvalidFileFormatException {
        String[] inputArr = input.split("\\s{2},", 4);
        if (inputArr.length < 4) {
            throw new InvalidFileFormatException();
        }
        String taskType = inputArr[0].toUpperCase();
        String description = inputArr[1];
        String time = inputArr[2];
        String status = inputArr[3];
        boolean isDone = checkIfDone(status);

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
     * Checks if task is done.
     *
     * @param input Input string.
     * @return true if word corresponds to done, false otherwise.
     * @throws InvalidFileFormatException If word does not correspond to either done or not done.
     */
    private static boolean checkIfDone(String input) throws InvalidFileFormatException {
        String status = input.toLowerCase();
        if (status.equals("done")) {
            return true;
        } else if (status.equals("not done")) {
            return false;
        } else {
            throw new InvalidFileFormatException();
        }
    }
}
