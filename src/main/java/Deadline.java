public class Deadline extends Task {
    protected String time;

    public Deadline(String name, String time, boolean status) {
        super(name, status);
        this.time = time;
    }

    @Override
    public String getStatus() {
        return "[D]" + super.getStatus() + " (by: " + this.time + ")";
    }

    @Override
    public String getTime() {
        return this.time;
    }

    @Override
    public String getType() {
        return "D";
    }
}
