package duke.command;

import duke.task.TaskList;

import duke.Ui;
import duke.Storage;

/**
 * Represents a command to display all the tasks in the user's list of tasks.
 */
public class ListCommand extends Command {

    /**
     * Prints all the user's tasks and the relevant task information.
     *
     * @param tasks List of tasks belonging to the user.
     * @param ui Ui object created for the Duke object.
     * @param storage Storage object used by the Duke object for file operations.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            String emptyListMessage = "Your list is empty. Add a new task!";
            ui.printReply(emptyListMessage);
        } else {
            String message = "Here is a list of all your tasks:";
            ui.printReply(message);
            for (int i = 0; i < tasks.getListSize(); i++) {
                int index = i + 1;
                String task = "\t" + String.format("%d. %s", index, tasks.getTask(i));
                ui.printReply(task);
            }
        }
    }

    /**
     * Indicates if the DukeBot session has ended.
     *
     * @return False since the DukeBot session has not been terminated.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
