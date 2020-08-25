package main.java.com.jacob.duke.command;

import main.java.com.jacob.duke.*;
import main.java.com.jacob.duke.task.Task;

import java.util.List;

public class PrintFilteredListCommand implements Command {
    private String inputCommand;

    /**
     * Constructor for Print filtered List Command
     * @param fullCommand with from console input
     */
    public PrintFilteredListCommand(String fullCommand) {
        this.inputCommand = fullCommand;
    }
    /**
     * Execution command for pre-determined printFilteredList Command
     * @param ui UI object to deal with program output
     * @param tasks Task List Representation
     * @param storage Storage object to deal with interfacing with file system
     * @throws DukeException In case there are internal errors
     */
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) throws DukeException {
        List<Task> taskList = tasks.getTaskList();
        ui.showFilteredList(inputCommand, taskList);
    }

    /**
     * Check if it is the bye Command
     * @return false since it is not
     */
    @Override
    public boolean isBye() {
        return false;
    }
}
