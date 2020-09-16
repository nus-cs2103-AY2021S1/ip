package duke.command;

import duke.Duke;
import duke.task.Event;

/**
 * EventCommand creates a Event task, stores it in task list, reports to the user.
 */
public class EventCommand extends Command {

    /**
     * Constructs an EventCommand.
     */
    public EventCommand() {
        names = new String[] { "event" };
        description = "Creates an event task.";
        format = CommandFormat.EVENT_CMD_FORMAT;
    }

    /**
     * Creates a Event task, stores it in task list, reports to the user.
     * @param str the task info
     * @param duke the currentDuke
     */
    @Override
    public void execute(String str, Duke duke) {
        Event newEvent = Event.createEvent(str);
        duke.getTaskList().addTask(newEvent);
        response(newEvent, duke);
    }

    private void response(Event newEvent, Duke duke) {
        if (duke.getState().getUseGui()) {
            duke.getGuiResponse().reportNewTask(newEvent);
        } else {
            duke.getUiResponse().reportNewTask(newEvent);
        }
    }
}
