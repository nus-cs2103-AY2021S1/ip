/**
 * Deletes a task from the list of tasks.
 */
public class DeleteCommand extends Command {
    public final static String COMMAND_WORD = "delete";

    DeleteCommand(String arguments) {
        super(arguments);
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        int index = Integer.parseInt(arguments) - 1;
        Task task = tasks.removeTask(index);
        try {
            storage.save(tasks.getTasks());
        } catch (DukeException e) {
            System.err.println(e);
        }
        String message = this.DELETE_MESSAGE + "  " + task.toString()
            + "\n" + tasks.replyNumTasks();
        return new CommandResult(message);
    }
}
