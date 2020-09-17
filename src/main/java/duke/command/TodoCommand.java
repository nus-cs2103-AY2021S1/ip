package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exceptions.InvalidFileException;
import duke.exceptions.InvalidInputException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;

/**
 * TodoCommand class to execute command that adds a todo to
 * the TaskList.
 */
public class TodoCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Got it. I've added this task:\n";

    public TodoCommand(String input) {
        super(input);
    }

    /**
     * Execute command that adds a todo task into the task list.
     *
     * @param tasks TaskList of tasks.
     * @param ui Ui object from the Ui class.
     * @param storage Storage object from the Storage class.
     * @throws InvalidInputException incorrect input with the todo command.
     * @throws InvalidFileException failed to save file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws InvalidInputException, InvalidFileException {
        if (super.input.substring(4).contains("  ")
                | super.input.length() <= 5) {
            throw new InvalidInputException(
                    "OOPS!!! Todo description is empty!!\n");
        }

        Task todoTask = new Todo(super.input.substring(5));
        tasks.addTask(todoTask);
        storage.save(tasks);
        return ui.printMessage(MESSAGE_SUCCESS + todoTask.toString()
                + "\nNow you have " + tasks.taskListSize() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
