package command;

import duke.Storage;
import duke.TaskList;
import exception.DukeException;
import task.Task;


/**
 * Represents an AddCommand for adding new tasks.
 */
public class AddCommand extends Command {
    private Task newTask;

    /**
     * Creates an instance of an AddCommand.
     *
     * @param newTask The new task to add.
     */
    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    /**
     * Adds the task into the TaskList.
     *
     * @param tasks The TaskList which accepts the task.
     * @param storage The Storage which will record the new task into the location specified in its path.
     * @throws DukeException Relays exception possibly thrown by storage when storing new task.
     * @return The output to be displayed to the user.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        storage.append(this.newTask);
        tasks.add(this.newTask);

        return "\t Got it. I've added this task:\n"
                + "\t   " + newTask + "\n"
                + "\t Now you have " + tasks.size() + " tasks in the list.\n";
    }

    /**
     * Indicates whether the program should exit after executing this Command.
     *
     * @return False since the program should still go on after adding a task.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
