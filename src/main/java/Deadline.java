public class Deadline extends Task {
    private String time;
    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }
    public String getTaskIdentifier() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString()
                + "(by: "
                + this.time
                + ")";
    }
}
