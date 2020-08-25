package main.java.com.jacob.duke.command;

import main.java.com.jacob.duke.*;
import main.java.com.jacob.duke.task.Event;
import main.java.com.jacob.duke.task.Task;

import java.util.List;

public class EventCommand implements Command {
    private String inputCommand;
    /**
     * Constructor for Event Command
     * @param fullCommand with from console input
     */
    public EventCommand(String fullCommand) {
        this.inputCommand = fullCommand;
    }

    /**
     * Execution command for pre-determined Event Command
     * @param ui UI object to deal with program output
     * @param tasks Task List Representation
     * @param storage Storage object to deal with interfacing with file system
     * @throws DukeException In case there are internal errors
     */
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) throws DukeException {
        List<Task> taskList = tasks.getTaskList();
        int breakpoint = inputCommand.indexOf("/") - 1;
        if (inputCommand.length() <= "event ".length()) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be incomplete.");
        } else if (breakpoint == -1) {
            throw new DukeException("Hey, a event cannot have no actual date and time!!");
        }
        String description = inputCommand.substring("event".length() + 1, breakpoint);
        String dateTime = inputCommand.substring(breakpoint + 5);
        Task theEvent = new Event(description, dateTime);
        taskList.add(theEvent);

        //append text
        storage.appendText(theEvent.convertToFile());

        ui.showNewTaskAdded(theEvent.getCurrentStatus(), taskList);

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
