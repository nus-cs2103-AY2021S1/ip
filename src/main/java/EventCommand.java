public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    private final String description;
    private final String eventDate;

    public EventCommand(String description, String eventDate) {
        this.description = description;
        this.eventDate = eventDate;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = new Event(description, eventDate);
        tasks.add(t);
        ui.addTaskMessage(t, tasks);
    }
}
