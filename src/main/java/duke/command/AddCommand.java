package duke.command;

import duke.DukeException;
import duke.Event;
import duke.ExceptionType;
import duke.Scheduler;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.ToDo;
import duke.Ui;

/**
 * This class adds a to do task, deadline task
 * or an event to the task list whenever execute
 * is called.
 */
public class AddCommand extends Command {
    protected Task task;

    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    /**
     * Adds task to the user tasklist and stores the new task list
     * in the storage.
     *
     * @param userTasks list of tasks from user.
     * @param storage user's storage.
     * @return response after command is executed.
     * @throws DukeException if clashing event is added.
     */
    public String execute(TaskList userTasks, Storage storage) throws DukeException {

        if (task instanceof Event) {
            boolean isClashing = new Scheduler().isEventClashingSchedule(userTasks, ((Event) task));
            if (isClashing) {
                throw new DukeException("", ExceptionType.EVENT_CLASHES_SCHEDULE);
            }
        }

        userTasks.addTask(task);
        storage.saveToFile(userTasks.getTaskList());
        response = new Ui().taskAddedMessage(task, userTasks.getTaskListSize());
        return getResponse();
    }
}