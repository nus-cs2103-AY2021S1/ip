public class AddTodoCommand extends AddCommand {
    public AddTodoCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        Todo todo = new Todo(description);
        tasks.addTask(todo);
        showAddTask(tasks, ui, todo);
    }
}
