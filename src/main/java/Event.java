public class Event extends Listing{

    String deadLine;

    public Event(String s, String deadLine) {
        super(s);
        this.deadLine = deadLine;
    }

    public Event(String doneness, String s, String time) {
        super(s);
        checkDoneness(doneness);
        this.deadLine = time;
    }

    public String[] toArray() {
        String[] details = new String[4];
        details[0] = "E";
        if (this.isDone) {
            details[1] = "1";
        } else {
            details[1] = "0";
        }
        details[2] = this.title;
        details[3] = this.deadLine;
        return details;
    }

    @Override
    public String toString() {
        return "[E]" + super.doneness() + " " + this.title + "(at:" + this.deadLine + ")";
    }
}
