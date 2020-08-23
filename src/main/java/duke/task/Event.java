package duke.task;

/**
 * Encapsulates an Event which occurs at a certain time.
 */

public class Event extends TimedTask {

    public Event(String description, String at) {
        super(description,at);
        this.connecting = " (at: ";
        this.firstLetter = "[E]";
    }

    /**
     * Loads an Event from its stored form.
     * @param str String representing the task in the storage file.
     * @return The corresponding Event.
     */
    public static Event load(String str) {
        String[] arr = str.split("\\|", 4);
        Event ev = new Event(arr[2], arr[3]);
        if (arr[1].equals("true")) {
            ev.isDone = true;
        }
        return ev;
    }

    @Override
    public String store() {
        return "E|" + super.store() + "|" + this.dateTime;
    }

}