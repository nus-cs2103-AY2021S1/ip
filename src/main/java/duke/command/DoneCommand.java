package duke.command;

import duke.Duke;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a user command that marks the specified task as done.
 */
public class DoneCommand extends Command {
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
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String fileString = tasks.listToString();
        int taskNumber = Integer.parseInt(userInput.substring(5));
        Task curr = tasks.get(taskNumber - 1);
        String beforeDone = curr.taskToText();

        curr.markAsDone();
        String afterDone = curr.taskToText();

        // mark this task as done in fileString
        fileString = fileString.replace(beforeDone, afterDone);

        // saves fileString to txt file
        Storage.save(Duke.FILENAME, fileString);

        System.out.println("    Nice! I've marked this task as done:\n"
                + "        " + tasks.get(taskNumber - 1));
    }

    public String executeToString(TaskList tasks, Ui ui, Storage storage) {
        String fileString = tasks.listToString();
        int taskNumber = Integer.parseInt(userInput.substring(5));
        Task curr = tasks.get(taskNumber - 1);
        String beforeDone = curr.taskToText();

        curr.markAsDone();
        String afterDone = curr.taskToText();

        // mark this task as done in fileString
        fileString = fileString.replace(beforeDone, afterDone);

        // saves fileString to txt file
        Storage.save(Duke.FILENAME, fileString);

        return "    Nice! I've marked this task as done:\n"
                + "        " + tasks.get(taskNumber - 1);
    }
}

