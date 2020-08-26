public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    private final String commandSuffix;

    TodoCommand(String commandSuffix) {
        this.commandSuffix = commandSuffix;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = new Todo(commandSuffix);
        tasks.add(t);
        ui.addTaskMessage(t, tasks);
    }
}
