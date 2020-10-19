package main.java.com.jacob.duke.command;
import java.util.List;

import main.java.com.jacob.duke.DukeList;
import main.java.com.jacob.duke.Ui;
import main.java.com.jacob.duke.io.Storage;
import main.java.com.jacob.duke.task.Deadline;
import main.java.com.jacob.duke.task.Task;


public class DeadlineCommand implements Command {
    private String inputCommand;

    /**
     * Constructor for Deadline Command.
     *
     * @param fullCommand with from console input.
     */
    public DeadlineCommand(String fullCommand) {
        this.inputCommand = fullCommand;
    }
    /**
     * Executes command for pre-determined Deadline Command.
     *
     * @param ui UI object to deal with program output.
     * @param dukeList Task List Representation.
     * @param storage Storage object to deal with interfacing with file system.
     */
    @Override
    public String execute(Ui ui, DukeList dukeList, Storage storage) {
        List<Task> taskList = dukeList.getTaskList();
        int breakpoint = inputCommand.indexOf("/") - 1;
        String description = inputCommand.substring("deadline".length() + 1, breakpoint);
        String dateTime = inputCommand.substring(breakpoint + 5);
        Task theDeadline = new Deadline(description, dateTime);
        taskList.add(theDeadline);

        storage.appendTextToTasks(theDeadline.convertToFile());

        return ui.showNewTaskAdded(theDeadline.getCurrentStatus(), taskList);
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
