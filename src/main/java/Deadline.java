public class Deadline extends Listing {
    String deadLine;

    public Deadline(String s, String deadLine) {
        super(s);
        this.deadLine = deadLine;
    }

    public Deadline(String doneness, String s, String time) {
        super(s);
        checkDoneness(doneness);
        this.deadLine = time;
    }

    public String[] toArray() {
        String[] details = new String[4];
        details[0] = "T";
        if (this.done) {
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
        return "[D]" + super.doneness() + this.title + " (by:" + this.deadLine + ")";
    }

}
