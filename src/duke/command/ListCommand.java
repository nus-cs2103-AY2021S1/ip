package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import main.java.*;

public class ListCommand extends Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
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

    @Override
    public boolean isExit() {
        return false;
    }
}
