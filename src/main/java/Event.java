public class Event extends Task {
    protected String start;
    protected String end;

    public Event (String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + start + "-" + end + ")";
    }

    @Override
    public Task fromString(String taskString) {
        boolean isDone = taskString.split("  ")[0].equals("[Done]");
        String[] stringSplit = taskString.split("  ")[1].split("\\s[(]at:\\s");
        String description = stringSplit[0];
        String start = stringSplit[1].split("-")[0];
        String end = stringSplit[1].split("-")[1];
        Event e = new Event(description, start, end);
        if (isDone) {
            e.setDone();
        }
        return e;
    }
}
