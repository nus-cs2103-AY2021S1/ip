public class Deadline extends Task {
    private String endDate;

    Deadline(String description, String endDate) {
        super(description);
        this.endDate = endDate;
    }

    Deadline(String description, String endDate, String completionStatus) {
        super(description, completionStatus);
        this.endDate = endDate;
    }

    @Override
    String getType() {
        return "E";
    }

    @Override
    String getDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + endDate + ")";
    }
}
