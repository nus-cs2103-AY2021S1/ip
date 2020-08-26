package main.java.duke.command;

import main.java.duke.Duke;
import main.java.duke.exceptions.DukeException;
import main.java.duke.exceptions.InvalidInputException;
import main.java.duke.Storage;
import main.java.duke.tasks.TaskList;
import main.java.duke.Ui;

/**
 * ListCommand class to execute command that list out all tasks
 * in the task list.
 */
public class ListCommand extends Command {

    public ListCommand(String input) {
        super(input);
    }

    /**
     * Execute the command to list out all tasks in a specific format.
     * @param tasks TaskList of tasks.
     * @param ui Ui object from the Ui class.
     * @param storage Storage object from the Storage class.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws InvalidInputException {
        if (tasks.taskListSize() == 0) {
            ui.printMessage("List is empty! Start adding to your task list!");
        } else {
            for (int i = 0; i < tasks.taskListSize(); i++) {
                ui.printMessage((i + 1) + "." + tasks.getTask(i));
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
