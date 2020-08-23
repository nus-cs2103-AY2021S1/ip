package duke.command;

import duke.task.Task;

import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;

/**
 * Represents a ListCommand for adding listing all existing tasks.
 */
public class ListCommand extends Command{

    /**
     * Lists the tasks in the TaskList.
     *
     * @param tasks The TaskList which contains all the tasks.
     * @param ui The Ui which will generate outputs significant to the user.
     * @param storage The Storage which will record any changes into the file in its path.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            ui.showMessage("\tYay! You have nothing to do at the moment! :-)\n");
        } else {
            StringBuilder output = new StringBuilder("\t Here are the tasks in your list:\n");

            for (int i = 1; i <= tasks.size(); i++) {
                Task theTask = tasks.get(i - 1);
                output.append("\t ").append(i).append(".").append(theTask).append("\n");
            }

            ui.showMessage(output.toString());
        }
    }

    /**
     * Indicates whether the program should exit after executing this Command.
     *
     * @return False since the program should still go on listing all the tasks.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
