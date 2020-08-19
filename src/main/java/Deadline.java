public class Deadline extends Task {
    String time;

    public Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String getStatus() {
        return "[D]" + super.getStatus() + " (by: " + this.time + ")";
    }
}
