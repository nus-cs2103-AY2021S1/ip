package duke.command;

import duke.error.DukeError;
import duke.error.IllegalCharacterError;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.UI;
import duke.task.Task;

public class AddTaskCommand implements Command {
    final Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws DukeError {
        if (task.getDescription().contains("|") || task.getDate().contains("|")) {
            throw new IllegalCharacterError();
        }
        taskList.add(task);
        return ui.addTask(task.toString(), taskList.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
