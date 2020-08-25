package main.java.com.jacob.duke.command;
import java.util.List;

import main.java.com.jacob.duke.DukeException;
import main.java.com.jacob.duke.Storage;
import main.java.com.jacob.duke.TaskList;
import main.java.com.jacob.duke.Ui;
import main.java.com.jacob.duke.task.Deadline;
import main.java.com.jacob.duke.task.Task;


public class DeadlineCommand implements Command {
    private String inputCommand;

    /**
     * Constructor for Deadline Command
     * @param fullCommand with from console input
     */
    public DeadlineCommand(String fullCommand) {
        this.inputCommand = fullCommand;
    }
    /**
     * Execution command for pre-determined Deadline Command
     * @param ui UI object to deal with program output
     * @param tasks Task List Representation
     * @param storage Storage object to deal with interfacing with file system
     * @throws DukeException In case there are internal errors
     */
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) throws DukeException {
        List<Task> taskList = tasks.getTaskList();
        int breakpoint = inputCommand.indexOf("/") - 1;
        if (inputCommand.length() <= "deadline ".length()) {
            throw new DukeException("☹ OOPS!!! The description of a Deadline cannot be empty.");
        } else if (breakpoint == -2) {
            throw new DukeException("Hey a deadline cannot have no actual date!!");
        }
        String description = inputCommand.substring("deadline".length() + 1, breakpoint);
        String dateTime = inputCommand.substring(breakpoint + 5);
        Task theDeadline = new Deadline(description, dateTime);
        taskList.add(theDeadline);

        //append text
        storage.appendText(theDeadline.convertToFile());

        //print status
        ui.showNewTaskAdded(theDeadline.getCurrentStatus(), taskList);
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
