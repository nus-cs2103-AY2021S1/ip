package duke.command;

import duke.*;
import duke.task.Task;

public class DoneCommand extends Command {
    private final int taskNum;

    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.markAsDone(taskNum);
            Task doneTask = tasks.getTask(taskNum);
            ui.printDoneTask(doneTask);
            storage.save(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("SORRY!!! Task number is not valid.");
        }
    }
}