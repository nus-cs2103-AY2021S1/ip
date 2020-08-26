package duke.command;

import duke.main.TaskList;
import duke.task.Event;

/**
 * EventCommand is Command to add a Command to the related TaskList.
 */
public class EventCommand extends Command {

    /** The Event that wants to be added to the TaskList. */
    protected Event event;

    /**
     * Constructs an EventCommand.
     *
     * @param taskDescription The description of the Event.
     * @param date The date of the Event.
     */
    public EventCommand(String taskDescription, String date) {
        this.event = new Event(taskDescription, date);
    }

    /**
     * Adds the Event to the related TaskList.
     * @param tasks The related TaskList.
     */
    @Override
    public void perform(TaskList tasks) {
        tasks.add(event);
        System.out.println(" Okay! I have added this task:" + "\n" + "   "
                + event.toString() + "\n" + " Now you have " + tasks.size() + (tasks.size() > 1 ? " tasks."
                : " task."));
    }

    /**
     * Checks if this is a termination Command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
