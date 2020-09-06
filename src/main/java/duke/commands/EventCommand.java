package duke.commands;

import duke.DukeException;
import duke.Ui;
import duke.tasks.Event;
import duke.tasks.TaskManager;

/**
 * <code>duke.commands.EventCommand</code> inherits from the base class <code>duke.commands.Command</code>
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
     * Using the <code>duke.Ui</code> object in the parent class, it prints out
     * the user interface to ask for the name, start time and end time of the event to be created.
     * It uses the <code>Scanner</code> object in the parent class to receive the name, start time
     * and end time of the deadline.
     * It uses the <code>TaskManager</code> object in the parent class and calls
     * its <code>add</code> method with an <code>Event</code> object passed as an argument.
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
                tm.add(new Event(eventName, eventStartTime, eventEndTime));
                setResponse("Event added"); // TODO: refactor this
                setEventEndTime(input);
                setDone();
        }
        return true;
    }

    @Override
    public void init(TaskManager tm, Ui ui) {
        setResponse(ui.askEventName());
        setUtility(tm, ui);
    }
}