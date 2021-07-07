package duke.command;

import duke.exception.DukeException;
import duke.logic.Storage;
import duke.logic.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand implements Command {
    private int indexToBeDeleted;

    public DeleteCommand(int indexToBeDeleted) {
        this.indexToBeDeleted = indexToBeDeleted;
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        // Do TaskList stuff
        Task taskToBeDeleted = tasks.getTask(indexToBeDeleted);
        tasks.delete(indexToBeDeleted);
        // Do storage stuff
        Storage.save(tasks);
        // Do UI stuff
        return Ui.printDelete(taskToBeDeleted.getDescription(), tasks.length());
    }
}
