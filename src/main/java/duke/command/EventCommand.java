package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;

public class EventCommand extends Command {
    private String taskName;
    private String eventTime;

    /**
     * Object representing Commands that refer to Event tasks
     *
     * @param taskName Name of Event task
     * @param eventTime String representing time of event
     */
    public EventCommand(String taskName, String eventTime) {
        this.taskName = taskName;
        this.eventTime = eventTime;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        verifyInput();
        Task task = new Event(taskName, eventTime);
        tasks.addTask(task);
        ui.displayAddTask(task, tasks.numTasks());
    }

    @Override
    public String executeWithOutput(TaskList tasks, Ui ui) throws DukeException {
        verifyInput();
        Task task = new Event(taskName, eventTime);
        tasks.addTask(task);
        return ui.getAddTaskResponseAsString(task, tasks.numTasks());
    }
    private void verifyInput() throws DukeException {
        if (taskName.isBlank()) {
            throw DukeException.badEventTask();
        }
        if (eventTime.isBlank()) {
            throw DukeException.badEventDate();
        }
    }

    @Override
    public void undo(TaskList tasks) {
        tasks.removeLastTask();
    }
}
