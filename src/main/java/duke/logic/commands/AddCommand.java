package duke.logic.commands;

import duke.exceptions.DukeException;
import duke.logic.TaskList;
import duke.logic.TaskType;
import duke.logic.tasks.Deadline;
import duke.logic.tasks.Event;
import duke.logic.tasks.Task;
import duke.logic.tasks.Todo;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a command by the user to add a task into the list of tasks.
 * An AddCommand object has a task type, which is either a todo, deadline or event task,
 * and a string array with the task description separated into the task name and date/time.
 */
public class AddCommand extends Command {
    private TaskType taskType;
    private String[] taskInfos;

    /**
     * Instantiates an AddCommand object.
     *
     * @param taskType The type of task to be added.
     * @param taskInfos The description of the task to be added.
     */
    public AddCommand(TaskType taskType, String[] taskInfos) {
        this.taskType = taskType;
        this.taskInfos = taskInfos;
    }

    /**
     * Executes the command by adding the new task to the list, replying the user,
     * and updating the list of tasks stored in the computer.
     *
     * @param tasks List of all the tasks of the user.
     * @param ui Replies the user.
     * @param storage Stores the new task in the computer.
     * @throws DukeException If the deadline or event is not specified in the correct format.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null && ui != null && storage != null
                : "TaskList, Ui or Storage is not supposed to be null";
        assert this.taskInfos.length > 0
                : "String array containing task descriptions is not supposed to be empty";

        Task t = createTask();
        tasks.addTask(t);
        storage.updateData(tasks);
        return ui.showAdd(t, tasks);
    }

    /**
     * Creates and returns a new task according to its task type.
     * @return A new task according to its task type.
     * @throws DukeException If the deadline or event is not specified in the correct format.
     */
    public Task createTask() throws DukeException {
        Task task;
        if (this.taskType == TaskType.TODO) {
            task = new Todo(this.taskInfos[0]);
        } else if (this.taskType == TaskType.DEADLINE) {
            task = new Deadline(this.taskInfos[0], this.taskInfos[1]);
        } else {
            task = new Event(this.taskInfos[0], this.taskInfos[1]);
        }
        return task;
    }

    /**
     * Returns false as the command is not an exit command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
