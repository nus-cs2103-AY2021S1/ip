package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.task.ToDo;
import main.java.duke.Ui;

public class CreateTodoCommand extends Command {

    private final String description;

    private final boolean isComplete;

    public CreateTodoCommand(String description, boolean isComplete) {
        this.description = description;
        this.isComplete = isComplete;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ToDo todo = tasks.addTodo(this.description, this.isComplete);
        ui.printCreateTask(tasks, todo);
    }
}
