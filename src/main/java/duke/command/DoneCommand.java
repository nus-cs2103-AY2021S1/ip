package duke.command;

import duke.storage.*;
import duke.tasklist.*;
import duke.ui.*;

/**
 * Represents an action to mark a Task from TaskList as done.
 */
public class DoneCommand extends Command {

    /** Index of Task to be marked as done */
    private int taskNumber;

    /**
     * Constructs a <code>DoneCommand</code> object.
     *
     * @param taskNumber Index of Task to be marked as done.
     */
    public DoneCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    /**
     * Marks a Task from the TaskList as done and notify the user.
     *
     * @param tasks TaskList to store Task.
     * @param ui Ui to interact with users.
     * @param storage Storage use by Duke to save and load files.
     * @return Nothing.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.doTask(taskNumber);
        storage.save(tasks);
        ui.printMessage("Nice! I've marked this task as done:\n\t   " +
                tasks.getTask(taskNumber));
    }

}
