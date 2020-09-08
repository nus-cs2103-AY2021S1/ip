package main.java.com.jacob.duke.command;

import java.util.List;

import main.java.com.jacob.duke.DukeException;
import main.java.com.jacob.duke.DukeList;
import main.java.com.jacob.duke.Ui;
import main.java.com.jacob.duke.io.Storage;
import main.java.com.jacob.duke.task.Task;


public class FindCommand implements Command {
    private String inputCommand;
    public FindCommand(String fullCommand) {
        this.inputCommand = fullCommand;
    }
    @Override
    public String execute(Ui ui, DukeList dukeList, Storage storage) throws DukeException {
        List<Task> taskList = dukeList.getTaskList();
        return ui.showKeywordList(inputCommand, taskList);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
