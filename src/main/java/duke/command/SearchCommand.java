package main.java.duke.command;

import main.java.duke.core.Storage;
import main.java.duke.core.TaskList;
import main.java.duke.core.Ui;
import main.java.duke.handle.TaskNotFoundException;

import java.io.IOException;

public class SearchCommand extends Command {
    private String key;

    public SearchCommand(String key) {
        this.key = key;
    }

    @Override
    public void excecute(TaskList taskList, Ui ui, Storage storage) throws TaskNotFoundException, IOException {
        ui.showList(taskList.findTaskWithDescription(key));
    }
}
