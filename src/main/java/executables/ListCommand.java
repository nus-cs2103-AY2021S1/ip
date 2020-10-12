package executables;

import storage.Storage;
import storage.TaskList;
import ui.UI;

public class ListCommand extends Execute {

    public ListCommand() { }


    @Override
    public String execute(TaskList taskList, UI ui) {
        Storage.save(taskList, Storage.FILE_PATH);
        return ui.addLines(taskList.printOutList());
    }

}
