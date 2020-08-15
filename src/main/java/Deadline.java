public class Deadline extends Task {

    private String dateTime;

    public Deadline(String name, String dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + " " + super.getItemName() + "(by: " + dateTime + ")";
    }
}
