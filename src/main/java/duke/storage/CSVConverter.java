package duke.storage;

import duke.exception.InvalidFileFormatException;
import duke.task.ComplexTask;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * Converts the task in CSV format to a Task object.
 */
public class CSVConverter {
    /**
     * Creates a task from the CSV format of the task.
     *
     * @param input CSV format of task.
     * @param ui User interface.
     * @return Task.
     */
    protected static Task parseToTask(String input, Ui ui) {
        try {
            String[] inputArr = input.split("\\s{2},", 4);
            if (inputArr.length < 4) {
                throw new InvalidFileFormatException();
            }
            String taskType = inputArr[0].toUpperCase();
            String description = inputArr[1];
            String time = inputArr[2];
            String status = inputArr[3];
            boolean isDone = checkStatus(status);

            if (taskType.equals("TODO")) {
                return new ToDo(description, isDone);
            } else if (taskType.equals("EVENT")) {
                return new ComplexTask(description, time, TaskType.EVENT);
            } else if (taskType.equals("DEADLINE")) {
                return new ComplexTask(description, time, TaskType.DEADLINE);
            } else {
                throw new InvalidFileFormatException();
            }
        } catch (InvalidFileFormatException e) {
            ui.printBasic(e.getMessage());
            return null;
        }
    }

    private static boolean checkStatus(String input) throws InvalidFileFormatException {
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
