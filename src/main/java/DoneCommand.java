public class DoneCommand extends Command {
    public final static String COMMAND_WORD = "done";

    DoneCommand(String arguments) {
        super(arguments);
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        int index = Integer.parseInt(arguments) - 1;
        Task task = tasks.getTask(index);
        String message = task.markAsDone();
        try {
            storage.save(tasks.getTasks());
        } catch (DukeException e) {
            System.err.println(e);
        }
        return new CommandResult(message);
    }
}
