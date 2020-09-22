package duke.command;

import duke.logic.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand implements Command {
    private int indexToBeDeleted;

    public DeleteCommand(int indexToBeDeleted) {
        this.indexToBeDeleted = indexToBeDeleted;
    }

    @Override
    public String execute(TaskList tasks) {
        // Do TaskList stuff
        Task taskToBeDeleted = tasks.getTask(indexToBeDeleted);
        tasks.delete(indexToBeDeleted);
        // Do UI stuff
        return Ui.printDelete(taskToBeDeleted.getDescription(), tasks.length());
        // Do storage stuff
        // tbc
    }
}
