public class Event extends Task  {
    public String time;
    Event(String name, String time) {
        super(name);
        this.time = time;
    }


    Event(String name, String time, boolean isCompleted) {
        super(name, isCompleted);
        this.time = time;
    }

    public static Event create(String description) throws DukeException {
        String[] keywords = description.split(" /at ", 2);
        if (keywords.length < 2) {
            throw new DukeException("☹ OOPS!!! Add a time using \" /at <time>\".\n");
        }
        return new Event(keywords[0], keywords[1]);
    }
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }

    @Override
    public String toSaveFormat() {
        return "E | " + super.toSaveFormat() + " | " + time;
    }
}
