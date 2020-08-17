public class Deadline extends Task {
    String time;
    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String getStatusIcon() {
        return String.format("[D]%s (by: %s)", super.getStatusIcon(), time);
    }
}
