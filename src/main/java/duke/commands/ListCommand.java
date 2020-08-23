package duke.commands;

import duke.tasks.Task;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

import java.util.ArrayList;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < taskList.getListSize(); i++) {
            Task task = taskList.getTaskAtIndex(i);
            tasks.add(task);
        }
        ui.displayAllItems(tasks);
    }
}
