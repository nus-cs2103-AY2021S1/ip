package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;
import duke.task.Task;

public class EventCommand extends Command {
    private String taskName;
    private String eventTime;

    /**
     * Object representing Commands that refer to Event tasks
     * @param taskName Name of Event task
     * @param eventTime String representing time of event
     */
    public EventCommand(String taskName, String eventTime) {
        this.taskName = taskName;
        this.eventTime = eventTime;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        if (taskName.isBlank()) {
            throw DukeException.badEventTask();
        }
        if (eventTime.isBlank()) {
            throw DukeException.badEventDate();
        }

        Task task = new Event(taskName, eventTime);
        tasks.addTask(task);
        ui.displayAddTask(task, tasks.numTasks());
    }
}
