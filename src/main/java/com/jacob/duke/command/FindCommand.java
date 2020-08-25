package main.java.com.jacob.duke.command;

import java.util.List;

import main.java.com.jacob.duke.DukeException;
import main.java.com.jacob.duke.Storage;
import main.java.com.jacob.duke.TaskList;
import main.java.com.jacob.duke.Ui;
import main.java.com.jacob.duke.task.Task;


public class FindCommand implements Command {
    private String inputCommand;
    public FindCommand(String fullCommand) {
        this.inputCommand = fullCommand;
    }
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) throws DukeException {
        List<Task> taskList = tasks.getTaskList();
        ui.showKeywordList(inputCommand, taskList);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
