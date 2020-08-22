package duke.command;

import duke.*;
import duke.task.*;
import duke.exception.*;
public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeLoadingErrorException {
        taskList.addTask(task);
        storage.save(taskList);
        String uiMessage = String.format(
                "Got it. I've added this task:\n%s\n%s",
                task.toString(),
                taskList.taskSizeMessage());
        ui.print(uiMessage);

    }
}
