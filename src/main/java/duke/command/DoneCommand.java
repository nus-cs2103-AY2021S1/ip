package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.tool.TaskList;

/**
 * Represents a command to mark element with certain index as done.
 */
public class DoneCommand implements Command {
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done: \n";

    /** Index of task that will be marked as done */
    private final int targetIndex;
    private Task doneTask;

    /**
     * Creates a command to mark certain task as done.
     *
     * @param index Index of tasks that is going to be marked as done.
     */
    public DoneCommand(int index) {
        this.targetIndex = index;

        assert index >= 0;
        assert !isExit();
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        doneTask = tasks.markDone(targetIndex);
        storage.save(tasks);

        assert !isExit();
        assert doneTask != null;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getResponse() {
        assert !isExit();
        return DoneCommand.DONE_MESSAGE + "\n\t" + doneTask.toString();
    }

}
