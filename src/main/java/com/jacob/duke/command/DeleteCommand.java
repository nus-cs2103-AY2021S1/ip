package main.java.com.jacob.duke.command;
import java.util.List;

import main.java.com.jacob.duke.DukeException;
import main.java.com.jacob.duke.DukeList;
import main.java.com.jacob.duke.Ui;
import main.java.com.jacob.duke.io.Storage;
import main.java.com.jacob.duke.task.Task;


public class DeleteCommand implements Command {
    private String inputCommand;
    /**
     * Constructor for Delete Command.
     *
     * @param fullCommand with from console input.
     */
    public DeleteCommand(String fullCommand) {
        this.inputCommand = fullCommand;
    }

    /**
     * Executes command for pre-determined delete Command.
     *
     * @param ui UI object to deal with program output.
     * @param dukeList Task List Representation.
     * @param storage Storage object to deal with interfacing with file system.
     * @throws DukeException In case there are internal errors.
     */
    @Override
    public String execute(Ui ui, DukeList dukeList, Storage storage) throws DukeException {
        List<Task> taskList = dukeList.getTaskList();
        int deleteIndex = Integer.parseInt(inputCommand.substring(7)) - 1;
        if (deleteIndex == -1 || deleteIndex >= taskList.size()) {
            throw new DukeException("No such task exists. ");
        }

        Task theRemovedTask = taskList.remove(deleteIndex);
        storage.removeTextFromTasks(theRemovedTask.convertToFile());

        return ui.showTaskDeleted(theRemovedTask.getCurrentStatus(), taskList);
    }

    /**
     * Check if it is the bye Command.
     *
     * @return false since it is not.
     */
    @Override
    public boolean isBye() {
        return false;
    }
}
