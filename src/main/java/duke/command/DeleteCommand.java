package duke.command;

import duke.*;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private final String input;

    public DeleteCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            int num = Integer.parseInt(this.input);
            Task curr = taskList.deleteTask(num);
            storage.deleteTask(num);
            String numTasks = "Now you have " + taskList.size() + " tasks in the list.\n";
            ui.replyDelete(String.format("%s\n%s", curr.toString(), numTasks));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please key in the number of an existing task to be removed!");
        }
    }
}
