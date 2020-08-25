package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class DoneCommand extends Command {
    int index;

    public static final String MESSAGE_SUCCESS = "Nice! I've marked this task as done:\n%s";

    public DoneCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task completedTask = taskList.get(index - 1);
        taskList.completeTask(index);
        storage.writeToFile(taskList);
        ui.printReply(String.format(MESSAGE_SUCCESS, completedTask));
    }

    public boolean isExit() {
        return false;
    }
}
