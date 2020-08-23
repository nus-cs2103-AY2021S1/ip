package duke.commands;

import duke.tasks.Task;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public final int sizeOffset = -1;

    public DoneCommand(String commandDescription) {
        super(commandDescription, false);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        Task task = taskList.getTaskAtIndex(Integer.parseInt(commandDescription) + sizeOffset);
        task.setDone();
        ui.displayDoneTask(task);
    }
}
