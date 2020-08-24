public class EventCommand extends Command {
    public final static String COMMAND_WORD = "event";

    EventCommand(String arguments) {
        super(arguments);
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        String[] s = this.arguments.split(" /at ");
        String desc = s[0];
        String at = s[1];
        Task task = new Event(desc, at);
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
