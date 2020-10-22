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
            String[] taskDataFields = taskData.split("\\|");
            
            String taskTypeLetter = taskDataFields[0];
            String doneStatus = taskDataFields[1];
            String taskDesc = taskDataFields[2];
            
            // check for save file corruption
            if (isCorruptStatus(doneStatus)) {
                throw new CorruptedFileException();
            }
            
            boolean isDone = doneStatus.equals("1");

            Task task;

            if (taskTypeLetter.equals("T")) {
                task = new ToDo(taskDesc);
            } else if (taskTypeLetter.equals("D")) {
                String by = taskDataFields[3];
                task = new Deadline(taskDesc, by);
            } else if (taskTypeLetter.equals("E")) {
                String at = taskDataFields[3];
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
    
    private static boolean isCorruptStatus(String doneStatus) {
        return !(doneStatus.equals("0") || doneStatus.equals("1"));
    }
}
