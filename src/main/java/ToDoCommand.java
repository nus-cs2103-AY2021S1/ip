/**
 * Adds a todo task to the list of tasks.
 */
public class ToDoCommand extends Command {
    public final static String COMMAND_WORD = "todo";

    ToDoCommand(String arguments) {
        super(arguments);
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        Task task = new ToDo(arguments);
        tasks.addTask(task);
        try {
            storage.save(tasks.getTasks());
        } catch (DukeException e) {
            System.err.println(e);
        }
        String message = this.ADD_MESSAGE + "  " + task.toString()
                + "\n" + tasks.replyNumTasks();
        return new CommandResult(message);
    }
}
