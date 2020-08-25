package duke.command;

import duke.*;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand extends Command {
    private final String input;

    public DoneCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            int num = Integer.parseInt(this.input);
            Task curr = taskList.getTask(num);
            curr.markAsDone();
            storage.updateTask(num, curr);
            ui.replyDone(curr.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please key in the number of an existing task to be marked as done!");
        }
    }
}
