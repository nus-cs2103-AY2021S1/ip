package duke.command;

import duke.exception.DukeException;
import duke.task.Task;

import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;


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
     * @param ui The Ui which will generate outputs significant to the user.
     * @param storage The Storage which will record the new task into the location specified in its path.
     * @throws DukeException Relays exception possibly thrown by storage when storing new task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.append(newTask);
        tasks.add(newTask);

        String output = "\t Got it. I've added this task:\n" +
                "\t   " + newTask + "\n" +
                "\t Now you have " + tasks.size() + " tasks in the list.\n" ;

        ui.showMessage(output);
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
