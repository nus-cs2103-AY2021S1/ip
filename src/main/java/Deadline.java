public class Deadline extends Task {
    String deadline;
    public Deadline(String in, String deadline) {

        super(in);
        this.deadline = deadline;
    }
    public String toString() {
        String donez;
        if (done) {
            donez = "✓";
        } else {
            donez = "✗";
        }
        return "[D] [" + donez + "] " + task + " (by: " + deadline + ")";
    }
}
