/**
 * Adds a do after task to the list of tasks.
 */
public class DoAfterCommand extends Command {
    public static final String COMMAND_WORD = "doafter";

    DoAfterCommand(String arguments) {
        super(arguments);
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        String[] s = this.arguments.split("/after");
        String description = s[0].trim();
        String after = s[1].trim();

        Task task = new DoAfter(description, after);
        tasks.addTask(task);

        try {
            storage.save(tasks.getTasks());
        } catch (DukeException e) {
            System.err.println(e);
        }

        String message = ADD_MESSAGE + "  " + task.toString()
                + "\n" + tasks.replyNumTasks();
        return new CommandResult(message);
    }
}
