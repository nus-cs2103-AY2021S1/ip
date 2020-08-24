package main.java.duke;

class CreateTodoCommand extends Command {

    private final String description;

    private final boolean isComplete;

    CreateTodoCommand(String description, boolean isComplete) {
        this.description = description;
        this.isComplete = isComplete;
    }

    @Override
    protected void execute(Storage storage, TaskList tasks, Ui ui) {
        ToDo todo = tasks.addTodo(this.description, this.isComplete);
        ui.printCreateTask(tasks, todo);
    }
}
