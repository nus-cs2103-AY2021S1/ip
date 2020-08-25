package main.java.com.jacob.duke.command;

import main.java.com.jacob.duke.*;
import main.java.com.jacob.duke.task.Task;

import java.util.List;

public class ByeCommand implements Command {

    /**
     * Execution command for pre-determined ByeCommand
     * @param ui UI object to deal with program output
     * @param tasks Task List Representation
     * @param storage Storage object to deal with interfacing with file system
     */
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) {
        List<Task> taskList = tasks.getTaskList();
        ui.sayBye();
    }

    /**
     * Check if it is the bye Command
     * @return true since it is
     */
    @Override
    public boolean isBye() {
        return true;
    }
}
