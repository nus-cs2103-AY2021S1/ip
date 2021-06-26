package duke.command;

import java.util.List;

import duke.task.Task;
import duke.task.TaskList;

class Helper {

    /**
     * Checks if a Task number (1-based) is valid. Throws an {@link InvalidTaskNumberException} with a message
     * describing what is wrong if the number is invalid. Otherwise does nothing.
     *
     * @param taskNumber 1-based task number to validate.
     * @param taskList Task list.
     */
    static void validateTaskNumber(int taskNumber, TaskList taskList) throws InvalidTaskNumberException {
        if (taskList.size() == 0) {
            throw new InvalidTaskNumberException("Your list is empty.");
        } else if (taskNumber < 1) {
            throw new InvalidTaskNumberException(
                    String.format("That's not a valid number, please give a number from 1 to %d.", taskList.size()));
        } else if (taskNumber > taskList.size()) {
            throw new InvalidTaskNumberException(
                    String.format("That's not a valid number, you only have %d item%s in your list.", taskList.size(),
                            taskList.size() == 1 ? "" : "s"));
        }
    }

    /**
     * Returns a numbered list of the given Tasks.
     *
     * @param tasks List of Tasks.
     * @return Numbered list of Tasks.
     */
    static String tasksToDisplayListString(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + ". " + tasks.get(i).displayString());
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns a numbered list of the given Tasks.
     *
     * @param tasks TaskList.
     * @return Numbered list of Tasks.
     */
    static String tasksToDisplayListString(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + ". " + tasks.get(i).displayString());
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns a String describing the number of Tasks in a TaskList.
     *
     * @param taskList TaskList.
     * @return Message describing the number of Tasks in the TaskList.
     */
    static String getNumberOfTasksString(TaskList taskList) {
        return String.format("Now you have %d item%s in your list.", taskList.size(), taskList.size() == 1 ? "" : "s");
    }
}
