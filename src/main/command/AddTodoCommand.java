package main.command;

import main.task.Todo;
import main.task.TaskList;
import main.ui.Ui;

public class AddTodoCommand implements Command {
    private final Todo todo;

    public AddTodoCommand(String description) {
        todo = new Todo(description);
    }

    @Override
    public void execute(Ui ui, TaskList tasks) {
        tasks.add(todo);
        ui.printAddSuccess(todo, tasks.size());
    }

    @Override
    public boolean hasCommand() {
        return true;
    }
}
