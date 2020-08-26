import java.time.LocalDateTime;

public class AddEventCommand extends AddCommand {
    private final LocalDateTime at;

    public AddEventCommand(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("The description of an event cannot be empty.");
        }

        Event event = new Event(description, at);
        tasks.addTask(event);
        showAddTask(tasks, ui, event);
    }
}
