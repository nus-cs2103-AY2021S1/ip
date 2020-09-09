package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exceptions.InvalidInputException;
import duke.tasks.TaskList;

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
     *
     * @param tasks TaskList of tasks.
     * @param ui Ui object from the Ui class.
     * @param storage Storage object from the Storage class.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws InvalidInputException {
        assert tasks.taskListSize() >= 0;
        if (tasks.taskListSize() == 0) {
            return ui.printMessage("List is empty! Start adding to your task list!");
        } else {
            String result = "";
            for (int i = 0; i < tasks.taskListSize(); i++) {
                result = result + ui.printMessage((i + 1) + "." + tasks.getTask(i)) + "\n";
            }
            return result;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
