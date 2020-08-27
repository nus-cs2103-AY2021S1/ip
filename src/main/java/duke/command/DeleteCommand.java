package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Represents a command where a task in the TaskList will be deleted during the usage of the Duke programme.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Public constructor
     * @param index the index of the Task in the TaskList that needs to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the Task with the index specified within this DeleteCommand.
     * Prints out an error message if the index is out of bounds of the TaskList.
     * After successful deletion, the deleted Task's information and total number of Tasks left is printed.
     * @param tasks the TaskList to delete the Task from
     * @param storage unused Storage object
     * @param ui unused Ui object
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            if (index > tasks.size()) {
                throw new DukeException("C'mon parder! That task doesn't exist cos you don't go so many!");
            }
            Task toBeRemoved = tasks.get(index);
            tasks.deleteTask(index);
            System.out.println("Alrighty, I'm taking that one out:\n" + toBeRemoved.toString() + "\n"
                    + "You've got a total of " + tasks.size() + " items right now.");
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}
