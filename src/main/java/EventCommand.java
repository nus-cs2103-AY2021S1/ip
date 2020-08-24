public class EventCommand extends Command {
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