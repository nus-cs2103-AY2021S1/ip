package clippy.task;

import clippy.exception.CorruptedFileException;
import clippy.exception.InvalidDateFormatException;

/**
 * Represents a save file parser that parses Strings from save file to re-create the task stored within.
 */
public class TaskGenerator {
    /**
     * Generates a task from a line of string stored within the save file.
     * 
     * @param taskData Line of string encapsulating details of a task.
     * @return A task re-created from the string.
     * @throws CorruptedFileException If data within the string is corrupted.
     */
    public static Task generateTask(String taskData) throws CorruptedFileException {
        try {
            String[] taskSubData = taskData.split("\\|");
            String taskTypeLetter = taskSubData[0];
            Boolean isDone = taskSubData[1].equals("1");
            String taskDesc = taskSubData[2];

            // check for savefile corruption
            if (!(taskSubData[1].equals("0") || taskSubData[1].equals("1"))) {
                throw new CorruptedFileException();
            }

            Task task;

            if (taskTypeLetter.equals("T")) {
                task = new ToDo(taskDesc);
            } else if (taskTypeLetter.equals("D")) {
                String by = taskSubData[3];
                task = new Deadline(taskDesc, by);
            } else if (taskTypeLetter.equals("E")) {
                String at = taskSubData[3];
                task = new Event(taskDesc, at);
            } else {
                throw new CorruptedFileException();
            }

            if (isDone) {
                task.markAsDone();
            }

            return task;
        } catch (ArrayIndexOutOfBoundsException | InvalidDateFormatException e) {
            throw new CorruptedFileException();
        }
    }
}
