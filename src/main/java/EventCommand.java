import java.io.IOException;

public class EventCommand extends Command {
    private String taskName;
    private String eventTime;

    public EventCommand(String taskName, String eventTime) {
        this.taskName = taskName;
        this.eventTime = eventTime;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        if (taskName.isBlank()) {
            throw DukeException.badEventTask();
        } if (eventTime.isBlank()) {
            throw DukeException.badEventDate();
        }

        Task task = new Event(taskName, eventTime);
        tasks.addTask(task);
        ui.displayAddTask(task, tasks.numTasks());
    }
}
