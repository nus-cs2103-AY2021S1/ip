package duke.commands;

/**
 * <code>duke.commands.EventCommand</code> inherits from the base class <code>duke.commands.Command</code>
 * and will handle the job of adding events to the task manager.
 */
public class EventCommand extends Command {
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
    public boolean execute() throws DukeException {
        ui.askEventName();
        String eventName = sc.nextLine();
        ui.askEventStartTime();
        String eventStartTime = sc.nextLine();
        ui.askEventEndTime();
        String eventEndTime = sc.nextLine();
        tm.add(new Event(eventName, eventStartTime, eventEndTime));
        return true;
    }
}