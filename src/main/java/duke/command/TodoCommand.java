package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.Ui;
import main.java.duke.exceptions.InvalidFileException;
import main.java.duke.exceptions.InvalidInputException;
import main.java.duke.tasks.Task;
import main.java.duke.tasks.TaskList;
import main.java.duke.tasks.Todo;

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
     * @param tasks TaskList of tasks.
     * @param ui Ui object from the Ui class.
     * @param storage Storage object from the Storage class.
     * @throws InvalidInputException incorrect input with the todo command.
     * @throws InvalidFileException failed to save file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException, InvalidFileException {
        if (super.input.length() <= 5) {
            throw new InvalidInputException("☹ OOPS!!! The description of a todo cannot be empty.\n");
        }
        Task todoTask = new Todo(super.input.substring(5));
        tasks.addTask(todoTask);
        ui.printMessage(MESSAGE_SUCCESS + todoTask.toString() + "\nNow you have " + tasks.taskListSize() + " tasks in the list.");
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
