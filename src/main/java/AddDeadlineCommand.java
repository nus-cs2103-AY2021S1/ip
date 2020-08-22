import java.time.LocalDateTime;

public class AddDeadlineCommand implements Command {
    private final Deadline deadline;

    public AddDeadlineCommand(String description, LocalDateTime dateTime) {
        deadline = new Deadline(description, dateTime);
    }

    @Override
    public void execute(Ui ui, TaskList tasks) {
        tasks.add(deadline);
        ui.printAddSuccess(deadline, tasks.size());
    }

    @Override
    public boolean hasCommand() {
        return true;
    }
}
