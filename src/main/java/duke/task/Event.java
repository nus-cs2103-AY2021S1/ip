package duke.task;

public class Event extends TimedTask {

    public Event(String description, String at) {
        super(description,at);
        this.connecting = " (at: ";
        this.firstLetter = "[E]";
    }

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