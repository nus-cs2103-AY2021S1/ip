import java.io.IOException;

/**
 * A Todo command to add a todo Task to the TaskList
 */
class TodoCommand extends Command {
    private String task;

    /**
     * Constructor for TodoCommand
     * @param toParse Partial user input to be parsed
     * @param tasks Existing list of tasks
     * @param ui User interface to be updated
     * @param storage Storage to be updated
     */
    TodoCommand(String toParse, TaskList tasks, Ui ui, Storage storage) throws EmptyTodoException {
        super(tasks, ui, storage);
        if (toParse.isEmpty()) {
            throw new EmptyTodoException();
        } else {
            this.task = toParse;
        }
    }

    /**
     * Executes the TodoCommand
     * @return Returns String confirmation of added task
     * @throws IOException Exception thrown when updating storage file
     */
    @Override
    public String execute() throws IOException {
        Task task = tasks.addTodo(this.task);
        storage.saveFile(tasks);
        return ui.printf("Got it. I've added this task:\n" + task.toString() + "\n" + tasks.taskCount());
    }
}
