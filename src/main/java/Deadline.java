public class Deadline extends  Listing{
    public Deadline(String s, String deadLine) {
        super(s);
        this.deadLine = deadLine;
    }
    String deadLine;

    @Override
    public String toString() {
        return "[D]" + super.doneness() + this.title + " (by:" + this.deadLine + ")";
    }

}
