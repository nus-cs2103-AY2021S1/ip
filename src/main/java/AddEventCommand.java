import java.time.LocalDateTime;

public class AddEventCommand implements Command {
    private final Event event;

    public AddEventCommand(String description, LocalDateTime dateTime) {
        event = new Event(description, dateTime);
    }

    @Override
    public void execute(Ui ui, TaskList tasks) {
        tasks.add(event);
        ui.printAddSuccess(event, tasks.size());
    }

    @Override
    public boolean hasCommand() {
        return true;
    }
}
