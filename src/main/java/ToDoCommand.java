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
