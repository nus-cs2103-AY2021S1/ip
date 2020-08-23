import java.time.LocalDate;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private String eventDate;
    private LocalDate eventLocalDate;
    private boolean hasLocalDate;

    public EventCommand(String commandDescription, String deadlineDate) {
        super(commandDescription, false);
        this.eventDate = deadlineDate;
        this.hasLocalDate = false;
    }

    public EventCommand(String commandDescription, LocalDate deadlineLocalDate) {
        super(commandDescription, false);
        this.eventLocalDate = deadlineLocalDate;
        this.hasLocalDate = true;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        Event eventTask;
        if (this.hasLocalDate) {
            eventTask = new Event(commandDescription, eventLocalDate);
        } else {
            eventTask = new Event(commandDescription, eventDate);
        }
        taskList.addToList(eventTask);
        ui.displayAddedTask(eventTask, taskList.getListSize());
    }
}
