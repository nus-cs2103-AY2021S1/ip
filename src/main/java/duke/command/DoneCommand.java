package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class to mark a Task in TaskList as done.
 */
public class DoneCommand extends Command {
    private int taskIndex;

    /**
     * Creates a DoneCommand with a given TaskIndex.
     *
     * @param taskIndex Index of Task in the TaskList to be marked as done.
     */
    public DoneCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    /**
     * Marks the Task with corresponding TaskIndex as done.
     * Displays Task marked as done message to user.
     *
     * @param ui Ui object to display messages to user.
     * @param storage Storage object to store items in the TaskList.
     * @param tasks Current list of Tasks.
     * @return Ui message to Task has been marked as done.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) throws DukeException {
        if (tasks.numTask() == 0) {
            throw new DukeException("Hey! Your list is empty!");
        }

        if (this.taskIndex > tasks.numTask() || this.taskIndex <= 0) {
            throw new DukeException("Hey, no task with this index!");
        }

        Task currTask = tasks.get(this.taskIndex - 1);
        currTask.markAsDone();
        storage.saveData(tasks.getTasks());
        return ui.markAsDone(this.taskIndex - 1, tasks);
    }

}
