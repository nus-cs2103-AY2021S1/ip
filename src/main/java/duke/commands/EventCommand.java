package duke.commands;

import duke.DukeException;
import duke.Ui;
import duke.tasks.Event;
import duke.tasks.TaskManager;

/**
 * <code>EventCommand</code> inherits from the base class <code>Command</code>
 * and will handle the job of adding events to the task manager.
 */
public class EventCommand extends Command {

    private String eventName;
    private String eventStartTime;
    private String eventEndTime;

    private void setEventName(String name) {
        eventName = name;
    }

    private void setEventStartTime(String startTime) {
        eventStartTime =  startTime;
    }

    private void setEventEndTime(String endTime) {
        eventEndTime = endTime;
    }

    /**
     * Adds an <code>Event</code> to the task manager.
     * @param input the user input.
     * @return <code>true</code>
     * @throws DukeException if the construction of the <code>Event</code> object results in an <code>exception</code>
     */
    @Override
    public boolean execute(String input) throws DukeException {
        switch (stage) {
        case 0:
            setEventName(input);
            setResponse(ui.askEventStartTime());
            incrementStage();
            break;
        case 1:
            setEventStartTime(input);
            setResponse(ui.askEventEndTime());
            incrementStage();
            break;
        case 2:
            setEventEndTime(input);
            tm.addEvent(new Event(eventName, eventStartTime, eventEndTime));
            setResponse("Event added");
            setEventEndTime(input);
            setDone();
        }
        return true;
    }

    /**
     * Sets the utility tools <code>tm</code> and <code>ui</code>.
     * In addition, sets the initial response to ask for the name of the
     * event to be created.
     * @param tm the task manager.
     * @param ui the ui.
     */
    @Override
    public void init(TaskManager tm, Ui ui) {
        setResponse(ui.askEventName());
        setUtility(tm, ui);
    }
}