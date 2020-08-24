package duke;

public class Deadline extends TimedTask {

    public Deadline(String description, String datetime) {
        super(description, datetime);
    }

    @Override
    protected String getTxtFormat() {
        return "deadline, " + super.getTxtFormat() + "/by" + this.dateTime.format(Deadline.inputFormatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + super.getDateTimeString() + ")";
    }
}
