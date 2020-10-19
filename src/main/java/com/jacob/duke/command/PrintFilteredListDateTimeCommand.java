package main.java.com.jacob.duke.command;

import java.util.List;

import main.java.com.jacob.duke.DukeException;
import main.java.com.jacob.duke.DukeList;
import main.java.com.jacob.duke.Ui;
import main.java.com.jacob.duke.io.Storage;
import main.java.com.jacob.duke.task.Task;




public class PrintFilteredListDateTimeCommand implements Command {
    private String inputCommand;
    /**
     * Constructor for Print filtered List Command
     * @param fullCommand with from console input
     */
    public PrintFilteredListDateTimeCommand(String fullCommand) {
        this.inputCommand = fullCommand;
    }
    /**
     * Execution command for pre-determined printFilteredList Command
     * @param ui UI object to deal with program output
     * @param dukeList Task List Representation
     * @param storage Storage object to deal with interfacing with file system
     * @throws DukeException In case there are internal errors
     */
    @Override
    public String execute(Ui ui, DukeList dukeList, Storage storage) throws DukeException {
        List<Task> taskList = dukeList.getTaskList();
        return ui.showFilteredDateTimeList(inputCommand, taskList);
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
