package duke.commands;

import duke.tasks.Task;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

import java.util.ArrayList;

public class SearchCommand extends Command {
    public static final String COMMAND_WORD = "search";

    public SearchCommand(String commandDescription) {
        super(commandDescription, false);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ArrayList<Task> tasks = new ArrayList<>();
        String pattern = "(.*)" + commandDescription + "(.*)";
        for (int i = 0; i < taskList.getListSize(); i++) {
            Task task = taskList.getTaskAtIndex(i);
            if (task.toString().matches(pattern)) {
                tasks.add(task);
            }
        }
        ui.displayAllItems(tasks);
    }
}
