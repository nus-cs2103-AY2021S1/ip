public class Event extends TaskDDL {

    public Event(String task, String ddl) {
        super(task, ddl);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", ddl);
    }
}
