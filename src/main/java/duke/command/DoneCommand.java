package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a user command that marks the specified task as done.
 */
public class DoneCommand extends Command {
    static final int INDEX_OF_TASK_NUMBER = 5;
    static final String DONE_RESPONSE = "    Nice! I've marked this task as done:\n";
    private String userInput;

    public DoneCommand(String userInput) {
        this.userInput = userInput;
    }

    public boolean isExit() {
        return false;
    }

    /**
     * Checks for the number specified by the user, and marks the task attached to
     * that number as done.
     *
     * @param tasks List of <code>Task</code> objects.
     * @param ui Ui object created by Duke.
     * @param storage Storage object created by Duke.
     * @return Resultant string where the check box for the specified task is ticked.
     */
    public String executeToString(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String fileString = tasks.listToString();
        int taskNumber = Integer.parseInt(userInput.substring(INDEX_OF_TASK_NUMBER));
        Task curr = tasks.get(taskNumber - 1);
        String beforeDone = curr.taskToText();

        curr.markAsDone();
        String afterDone = curr.taskToText();

        // mark this task as done in fileString
        fileString = fileString.replace(beforeDone, afterDone);

        // saves fileString to txt file
        Storage.save(fileString);

        return DONE_RESPONSE + "        " + tasks.get(taskNumber - 1);
    }
}

