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
    private boolean isComplete;
    public DeadlineCommand(String fullCommand) {
        this.inputCommand = fullCommand;
    }
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) throws DukeException {
        List<Task> taskList = tasks.getTaskList();
        int breakpoint = inputCommand.indexOf("/") - 1;
        if (inputCommand.length() <= "deadline ".length()) {
            throw new DukeException("☹ OOPS!!! The description of a Deadline cannot be empty.\n");
        } else if (breakpoint == -1) {
            throw new DukeException("Hey a deadline cannot have no actual date!!\n");
        }
        String description = inputCommand.substring("deadline".length() + 1, breakpoint);
        String dateTime = inputCommand.substring(breakpoint + 5);
        Task theDeadline = new Deadline(description, dateTime);
        taskList.add(theDeadline);

        //append text
        storage.appendText(theDeadline.convertToFile());

        //print status
        ui.showNewTaskAdded(theDeadline.getCurrentStatus(), taskList);
        isComplete = true;
    }

    @Override
    public boolean isComplete() {
        return isComplete;
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
