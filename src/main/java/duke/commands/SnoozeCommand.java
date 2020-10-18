package duke.commands;

import java.time.LocalDate;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that snooze a deadline or an evnet.
 * @version 1.0
 */
public class SnoozeCommand extends Command {
    private final int taskIndex;
    private final LocalDate newDate;

    /**
     * Creates a new SnoozeCommand object with the given task index in the task list.
     *
     * @param taskIndex Index of the task in the task list.
     */
    public SnoozeCommand(int taskIndex, LocalDate newDate) {
        this.commandName = "Snooze";
        this.taskIndex = taskIndex;
        this.newDate = newDate;
        this.isExit = false;
    }

    /**
     * Deletes the task indexed from the specified TaskList.
     * Updates the specified storage with the changed TaskList.
     * Show action feedback to user.
     *
     * @param list A TaskList object of which the command is executed on.
     * @param ui An UI object to interact with the user if required by the command.
     * @param storage A Storage object to write/access information to/from a file if required by the command.
     * @throws DukeException thrown if any DukeException is thrown by the called functions.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        if (taskIndex > list.getActiveTasks()) {
            throw new DukeException("Task at index does not exist or already done.");
        }
        Task task = list.getTaskAtIndex(taskIndex);
        list.deleteTask(task);
        Task newTask = update(task, newDate);
        list.addTask(newTask);
        storage.write(list.getList());
        ui.showSnooze(newTask);
        list.printList("All");
    }

    private Task update(Task task, LocalDate newDate) throws DukeException {
        if (task.getDate() == null) {
            throw new DukeException("Target task cannot have a due date.");
        }
        Task newTask = new Task(task.getType(), task.getDescription(), newDate);
        return newTask;
    }
}
