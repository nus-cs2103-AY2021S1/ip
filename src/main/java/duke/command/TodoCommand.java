package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Todo;

public class TodoCommand implements Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Todo newTask = new Todo(description);
        tasks.addTask(newTask);
        return ui.displayTaskAdd(newTask, tasks.getCount());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isMassCommand() {
        return false;
    }
}
