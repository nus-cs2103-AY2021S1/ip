public class Deadline extends TaskDetail {
    String timeBy;

    public Deadline(String desc, String timeBy) {
        super(desc);
        this.timeBy = timeBy;
    }

    @Override
    public String toString() {
        String sign = done ? "✓" : "✗";
        return "[D][" + sign + "]" + description + " (by:" + timeBy + ")";
    }
}
