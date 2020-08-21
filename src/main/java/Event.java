public class Event extends DatedTask {
    public Event(String name, String date) {
        super(name, date);
    }

    public Event(String name, boolean completed, String date) {
        super(name, completed, date);
    }

    @Override
    public String format() {
        return "E" + SAVE_DELIMITER + super.format();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
