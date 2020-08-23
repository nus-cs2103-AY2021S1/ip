package duke.commands;

import duke.tasks.Task;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public final int sizeOffset = -1;

    public DeleteCommand(String commandDescription) {
        super(commandDescription, false);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        Task task = taskList.getTaskAtIndex(Integer.parseInt(commandDescription) + sizeOffset);
        taskList.removeFromList(Integer.parseInt(commandDescription) + sizeOffset);
        ui.displayDeletedTask(task, taskList.getListSize());
    }
}
