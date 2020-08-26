import java.time.LocalDateTime;

public class AddCommand extends Command {
    private final TaskType type;
    private final String description;
    private final LocalDateTime dateTime;

    public AddCommand(TaskType type, String description) {
        this(type, description, null);
    }

    public AddCommand(TaskType type, String description, LocalDateTime dateTime) {
        this.type = type;
        this.description = description;
        this.dateTime = dateTime;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.addTask(type, description, dateTime);
        ui.showPrompt("Got it. I've added this task:\n  "
                + task + "\n" + "Now you have " + tasks.getTasks().size()
                + (tasks.getTasks().size() == 1 ? " task" : " tasks") + " in the list.");
    }
}
