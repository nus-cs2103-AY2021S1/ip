import java.time.LocalDateTime;

/**
 * handles the "event" commands
 */
public class EventCommand extends Command {

    /**
     * Constructor for EventCommand
     *
     * @param deadlineName
     * @param deadlineDateTime
     * @throws IllegalArgumentException
     */
    public EventCommand(String deadlineName, LocalDateTime deadlineDateTime) throws IllegalArgumentException {
        super(deadlineName, deadlineDateTime);
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
