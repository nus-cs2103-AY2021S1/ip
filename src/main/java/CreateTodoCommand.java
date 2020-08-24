package main.java;

class CreateTodoCommand extends Command {

    private final String description;

    private final boolean isComplete

    CreateTodoCommand(String description, boolean isComplete) {
        this.description = description;
        this.isComplete = isComplete;
    }

    @Override
    protected void execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.addTodo(this.description);
        // TODO Ui: successfully create todo
    }
}
