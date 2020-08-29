public class Deadline extends Task{
    private String time;

    public Deadline(String command, String time) {
        super(command);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + time + ")";
    }
}