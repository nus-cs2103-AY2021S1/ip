public class Deadline extends Task {
    private String date;
    public Deadline(boolean isComplete, int index, String instructions, String date) {
        super(isComplete, index, instructions);
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        if (this.isComplete) {
            return "[D][✓] " + this.instructions + " (by: " + this.date + ")";
        } else {
            return "[D][✗] " + this.instructions + " (by: " + this.date + ")";
        }
    }
}
