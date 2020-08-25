package main.command;

import java.time.LocalDateTime;

import main.task.Event;
import main.task.TaskList;
import main.ui.Ui;

/**
 * Represents the add event command.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.1
 * @since v0.1
 */
public class AddEventCommand implements Command {
    private final Event event;

    /**
     * Constructs an AddEventCommand instance and the Event object
     * with the description and the deadline of the task.
     * @param description the description of the task.
     * @param dateTime the time of the event occurring.
     */
    public AddEventCommand(String description, LocalDateTime dateTime) {
        event = new Event(description, dateTime);
    }

    /**
     * Adds the Event object into the task list and prints add success
     * from the ui.
     * @param ui the ui used to print out responses.
     * @param tasks the task list.
     */
    @Override
    public void execute(Ui ui, TaskList tasks) {
        tasks.add(event);
        ui.printAddSuccess(event, tasks.size());
    }

    /**
     * Returns true since there can still be commands after this.
     * @return true.
     */
    @Override
    public boolean hasCommand() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddEventCommand) {
            AddEventCommand o = (AddEventCommand) obj;
            return this.event.equals(o.event);
        }
        return false;
    }
}
