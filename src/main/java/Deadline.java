public class Deadline extends Task {
    protected String dateEnd;

    public Deadline(String description, String dateEnd) {
        super(description);
        this.dateEnd = dateEnd;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String getDate() {
        return dateEnd;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), dateEnd);
    }
}
