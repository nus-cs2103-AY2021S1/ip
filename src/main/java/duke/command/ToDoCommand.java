package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

public class ToDoCommand extends Command {
    private String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new ToDo(this.description, false);
        tasks.addTask(newTask);
    }
}
