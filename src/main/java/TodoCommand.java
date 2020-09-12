import java.io.IOException;

/**
 * A Todo command to add a todo Task to the TaskList
 */
class TodoCommand extends Command {
    private String task;

    TodoCommand(String toParse, TaskList tasks, Ui ui, Storage storage) throws EmptyTodoException {
        super(tasks, ui, storage);
        if (toParse.isEmpty()) {
            throw new EmptyTodoException();
        } else {
            this.task = toParse;
        }
    }

    @Override
    public String execute() throws IOException {
        Task task = tasks.addTodo(this.task);
        storage.saveFile(tasks);
        return ui.printf("Got it. I've added this task:\n" + task.toString() + "\n" + tasks.taskCount());
    }
}
