public class Deadline extends Task {
    private String time;

    public Deadline(String s, String time) {
        super(s);
        this.time = time;
    }

    @Override
    public String toString() {
        return done
                ? "[D][✓] " + text + " (by: " + time + ")"
                : "[D][✗] " + text + " (by: " + time + ")";
    }
}
