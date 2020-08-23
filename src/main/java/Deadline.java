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

    public String toCommand() {
        return done
                ? "done deadline " + text + " /by " + time
                : "deadline " + text + " /by " + time;
    }
}
