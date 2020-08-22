public class AddTodoCommand extends Command {
    private final String[] instructions;

    public AddTodoCommand(String[] instructions) {
        super();
        this.instructions = instructions;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task todo = new Todo(instructions[1]);
        tasks.addTask(todo);
        storage.save(tasks);
    }
}

