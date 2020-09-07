package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a user command that deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private String userInput;

    static final int INDEX_OF_TASK_NUMBER = 7;

    public DeleteCommand(String userInput) {
        this.userInput = userInput;
    }

    public boolean isExit() {
        return false;
    }

    /**
     * Checks for the number specified by the user, and deletes the task at that
     * number accordingly.
     *
     * @param tasks List of <code>Task</code> objects.
     * @param ui Ui object created by Duke.
     * @param storage Storage object created by Duke.
     * @return String that informs user that the specified task is deleted.
     */
    public String executeToString(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String fileString = tasks.listToString();

        String result;

        int taskNumber = Integer.parseInt(userInput.substring(INDEX_OF_TASK_NUMBER));
        Task curr = tasks.get(taskNumber - 1);
        String taskToBeDeleted = curr.taskToText();
        result = "    Noted. I've removed this task:\n"
                + "        " + tasks.get(taskNumber - 1) + "\n";
        tasks.remove(taskNumber - 1);
        result += ("    Now you have " + tasks.size() + " tasks in the list.");

        fileString = fileString.replace(taskToBeDeleted + "\n", "");

        // saves fileString to txt file
        Storage.save(fileString);
        return result;
    }
}

