public class Deadline extends Task{
    protected String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return super.toString().replace("[\u2718]", "[D][\u2718]") + " (by: " + time + ")";
    }
}
