package duke.command;

import duke.*;
import duke.task.Task;

public class DeleteCommand extends Command {
    private final int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task deletedTask = tasks.deleteTask(taskNum);
            ui.printDeleteTask(deletedTask, tasks);
            storage.save(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("SORRY!!! Task number is not valid.");
        }
    }
}