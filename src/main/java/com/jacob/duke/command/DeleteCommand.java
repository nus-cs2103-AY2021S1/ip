package main.java.com.jacob.duke.command;

import main.java.com.jacob.duke.*;
import main.java.com.jacob.duke.task.Task;

import java.util.List;

public class DeleteCommand implements Command {
    private String inputCommand;
    /**
     * Constructor for Delete Command
     * @param fullCommand with from console input
     */
    public DeleteCommand(String fullCommand) {
        this.inputCommand = fullCommand;
    }

    /**
     * Execution command for pre-determined delete Command
     * @param ui UI object to deal with program output
     * @param tasks Task List Representation
     * @param storage Storage object to deal with interfacing with file system
     * @throws DukeException In case there are internal errors
     */
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) throws DukeException {
        List<Task> taskList = tasks.getTaskList();
        Task theRemovedTask = taskList.remove(Integer.parseInt(inputCommand.substring(7)) - 1);
        if (theRemovedTask == null) {
            throw new DukeException("No such task exists! ");
        }
        //remove text
        storage.removeText(theRemovedTask.convertToFile());

        //print the output

        ui.showTaskDeleted(theRemovedTask.getCurrentStatus(), taskList);
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
