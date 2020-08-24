public class Deadline extends Task {
    private String time;

    public Deadline(String description, boolean done, String time) {
        super(description, done);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.time + ")";
    }

    @Override
    public String saveToHardDisk() {
        return "D" + super.saveToHardDisk() + " | " + this.time;
    }
}
