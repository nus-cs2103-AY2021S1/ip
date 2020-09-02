package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class DeleteCommand extends Command {
    private int deleteIndex;

    public DeleteCommand(int taskDone) {
        this.deleteIndex = taskDone;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.removeATask(deleteIndex);
        storage.writeData(tasks);
        StringBuilder str = new StringBuilder("I have removed this task\n");
        str.append(task.toString());
        str.append("\nYou now have " + tasks.getNumberOfTasks() + " tasks left!");
        return str.toString();
    }
}
