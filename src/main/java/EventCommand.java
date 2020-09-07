import java.time.LocalDateTime;

/**
 * handles the "event" commands
 */
public class EventCommand extends Command {

    /**
     * Constructor for EventCommand
     *
     * @param eventName
     * @param eventDateTime
     * @throws IllegalArgumentException
     */
    public EventCommand(String eventName, LocalDateTime eventDateTime) throws IllegalArgumentException {
        super(eventName, eventDateTime);
    }

    /**
     * shows the event task added, updates the total number of tasks
     *
     * @param taskList
     */
    public String execute(TaskList taskList) {
        Event newEvent = new Event(this.getTaskName(), this.getTaskDateTime());
        taskList.addTask(newEvent);
        return TextUi.printNewTasks(newEvent.toString()) + "\n" + TextUi.printTaskSummary(taskList.getTaskLength());
    }
}
