package main.java.com.jacob.duke.command;
import java.util.List;

import main.java.com.jacob.duke.DukeList;
import main.java.com.jacob.duke.Ui;
import main.java.com.jacob.duke.io.Storage;
import main.java.com.jacob.duke.task.Event;
import main.java.com.jacob.duke.task.Task;

public class EventCommand implements Command {
    private String inputCommand;
    /**
     * Constructor for Event Command.
     *
     * @param fullCommand with from console input.
     */
    public EventCommand(String fullCommand) {
        this.inputCommand = fullCommand;
    }

    /**
     * Executes command for pre-determined Event Command.
     *
     * @param ui UI object to deal with program output.
     * @param dukeList Task List Representation.
     * @param storage Storage object to deal with interfacing with file system.
     */
    @Override
    public String execute(Ui ui, DukeList dukeList, Storage storage) {
        List<Task> taskList = dukeList.getTaskList();
        int breakpoint = inputCommand.indexOf("/") - 1;
        String description = inputCommand.substring("event".length() + 1, breakpoint);
        String dateTime = inputCommand.substring(breakpoint + 5);
        Task theEvent = new Event(description, dateTime);
        taskList.add(theEvent);

        storage.appendTextToTasks(theEvent.convertToFile());

        return ui.showNewTaskAdded(theEvent.getCurrentStatus(), taskList);

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
