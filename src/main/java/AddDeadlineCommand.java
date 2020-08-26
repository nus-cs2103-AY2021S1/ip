import java.time.LocalDateTime;

public class AddDeadlineCommand extends AddCommand {
    private final LocalDateTime by;

    public AddDeadlineCommand(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        Deadline deadline = new Deadline(description, by);
        tasks.addTask(deadline);
        showAddTask(tasks, ui, deadline);
    }
}
