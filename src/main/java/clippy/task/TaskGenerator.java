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
            String doneStatus = taskSubData[1];
            String taskDesc = taskSubData[2];
            
            // check for save file corruption
            if (!(doneStatus.equals("0") || doneStatus.equals("1"))) {
                throw new CorruptedFileException();
            }
            
            boolean isDone = doneStatus.equals("1");

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
