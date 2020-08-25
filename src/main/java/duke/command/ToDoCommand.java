package duke.command;

import duke.storage.Storage;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

import duke.ui.Ui;

public class ToDoCommand extends TaskCreationCommand {
    public final static String COMMAND = "todo";
    
    private String description;
    
    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new ToDo(description);
        super.execute(task, ui, tasks);
    }
}
