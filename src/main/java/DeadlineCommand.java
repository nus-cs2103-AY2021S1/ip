public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    private final String description;
    private final String deadline;

    DeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = new Deadline(description, deadline);
        tasks.add(t);
        ui.addTaskMessage(t, tasks);
    }
}
