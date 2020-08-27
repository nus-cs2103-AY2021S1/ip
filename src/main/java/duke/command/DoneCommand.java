package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.exception.DukeException;
import duke.ui.Ui;
import duke.storage.Storage;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";

    private final int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task t = tasks.retrieve(taskNumber);
            if (t.isDone()) {
                throw new DukeException("Task is already marked as done.");
            }
            t.markAsDone();
            ui.doneTaskMessage(t);
        } catch (Exception e) {
            throw new DukeException("Task does not exist/invalid task number.");
        }
    }
}
