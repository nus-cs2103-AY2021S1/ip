public class Deadline extends TimedTask {

    public Deadline(String description, String datetime) {
        super(description, datetime);
    }

    @Override
    protected String textFormat() {
        return "deadline, " + super.textFormat() + "/by" + this.datetime.format(Deadline.inputFormatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + super.datetimeString() + ")";
    }
}
