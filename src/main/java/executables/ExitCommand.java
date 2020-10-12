package executables;

import storage.Storage;
import storage.TaskList;
import ui.UI;

public class ExitCommand extends Execute {

    public ExitCommand() { }


    @Override
    public String execute(TaskList taskList, UI ui) {
        Storage.save(taskList, Storage.FILE_PATH);
        return ui.addLines("Bye. Hope to see you again soon!");
    }
}
