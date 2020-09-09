package main.command;

import java.time.LocalDateTime;
import java.util.HashSet;

import main.task.Event;
import main.task.TaskList;
import main.ui.Ui;

/**
 * Represents the add event command.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.3
 * @since v0.1
 */
public class EventCommand implements Command {
    private final Event event;

    /**
     * Constructs an EventCommand instance and the Event object
     * with the description and the deadline of the task.
     * @param description the description of the task.
     * @param dateTime the time of the event occurring.
     * @param options the options of the task.
     * @param tags the tags associated with the task.
     */
    public EventCommand(String description, LocalDateTime dateTime,
                        HashSet<Option> options, String[] tags) {
        event = new Event(description, dateTime, options, tags);
    }

    /**
     * Adds the Event object into the task list and prints add success
     * from the ui.
     * @param ui the ui used to print out responses.
     * @param tasks the task list.
     * @return the string indicating the task has been added successfully.
     */
    @Override
    public String execute(Ui ui, TaskList tasks) {
        tasks.add(event);
        return ui.printAddSuccess(event, tasks.size());
    }

    /**
     * Returns true since there can still be commands after this.
     * @return true.
     */
    @Override
    public boolean hasCommandAfter() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EventCommand) {
            EventCommand o = (EventCommand) obj;
            return this.event.equals(o.event);
        }
        return false;
    }
}
