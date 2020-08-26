public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public static Event load(String str) {
        String[] arr = str.split("\\|", 4);
        Event task = new Event(arr[2], arr[3]);
        if (arr[1].equals("true")) {
            task.markAsDone();
        }
        return task;
    }

    @Override
    public String store() {
        return "E|" + super.store() + "|" + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
