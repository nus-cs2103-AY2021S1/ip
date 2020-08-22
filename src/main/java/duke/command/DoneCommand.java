package duke.command;

import duke.*;
import duke.task.*;
import duke.exception.*;
public class DoneCommand extends Command {
    private int taskNo;

    public DoneCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeLoadingErrorException {
        Task completedTask = taskList.completeTask(taskNo);
        storage.save(taskList);
        String uiMessage = String.format("Nice! I've marked this task as done:\n%s", completedTask.toString());
        ui.print(uiMessage);
    }
}
