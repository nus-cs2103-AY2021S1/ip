public class Deadline extends Task {
    private String endDate;

    Deadline(String description, String endDate) {
        super(description);
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + this.endDate + ")";
    }
}
