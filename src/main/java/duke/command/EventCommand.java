package duke.command;

import duke.main.TaskList;
import duke.task.Event;

/**
 * EventCommand is Command to add an Event to the related TaskList.
 */
public class EventCommand extends Command {
    /** The Event that wants to be added to the TaskList. */
    private Event event;
    /** TaskList that is related to this command. **/
    private TaskList tasks;

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
     * @param t The related TaskList.
     */
    @Override
    public void perform(TaskList t) {
        tasks = t;
        int a = tasks.size();
        tasks.add(event);
        assert tasks.size() == a + 1;
    }

    /**
     * Gets the reply after performing the Command.
     *
     * @return A reply as a String based on the perform method.
     **/
    @Override
    public String getReply() {
        return " Okay! I have added this task:" + "\n" + "   "
            + event.toString() + "\n" + " Now you have " + tasks.getSize() + (tasks.getSize() > 1 ? " tasks."
            : " task.");
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
