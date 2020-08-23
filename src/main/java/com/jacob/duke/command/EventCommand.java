package main.java.com.jacob.duke.command;

import main.java.com.jacob.duke.*;
import main.java.com.jacob.duke.task.Event;
import main.java.com.jacob.duke.task.Task;

import java.util.List;

public class EventCommand implements Command{
    private String inputCommand;
    private boolean isComplete;

    public EventCommand(String fullCommand) {
        this.inputCommand = fullCommand;
    }
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) throws DukeException {
        List<Task> taskList = tasks.taskList;
        int breakpoint = inputCommand.indexOf("/")-1;
        if (inputCommand.length() <= "event ".length() ) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be incomplete.");
        } else if (breakpoint == -1) {
            throw new DukeException("Hey, a event cannot have no actual date and time!!");
        }
        String description = inputCommand.substring("event".length()+1, breakpoint);
        String dateTime = inputCommand.substring(breakpoint + 5);
        Task theEvent = new Event(description, dateTime);
        taskList.add(theEvent);

        //append text
        storage.appendText(theEvent.convertToFile());

        ui.showNewTaskAdded(theEvent.getCurrentStatus(), taskList);
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
