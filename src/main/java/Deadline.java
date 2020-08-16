public class Deadline extends Task{

    private String time;

    Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        String doneString = (super.done == true ? "✓" : "✗");
        return "[D]" + "[" + doneString + "] " + this.name + " (by: " + this.time + ")";
    }
}

