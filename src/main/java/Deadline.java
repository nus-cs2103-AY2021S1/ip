public class Deadline extends Task {

    protected String dateAndOrTime;

    Deadline(String description, String dateAndOrTime) {
        super(description);
        this.dateAndOrTime = dateAndOrTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + dateAndOrTime + ")";
    }
}
