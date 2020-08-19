public class Event extends  Listing{
    public Event(String s, String deadLine) {
        super(s);
        this.deadLine = deadLine;
    }
    String deadLine;

    @Override
    public String toString() {
        return "[E]" + super.doneness() + this.title + " (at:" + this.deadLine + ")";
    }
}
