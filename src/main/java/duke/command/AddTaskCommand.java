package duke.command;

import duke.error.DukeError;
import duke.error.IllegalCharacterError;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.UI;
import duke.task.Task;

public class AddTaskCommand implements Command {
    Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeError {
        if (task.getDescription().contains("|") || task.getDate().contains("|")) {
            throw new IllegalCharacterError();
        }
        taskList.addTask(task);
        ui.addTask(task.toString(), taskList.numberOfTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
