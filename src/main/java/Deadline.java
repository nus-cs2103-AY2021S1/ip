public class Deadline extends Task {

    private String time;

    Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    Deadline(String name, String time, boolean done) {
        super(name);
        this.time = time;
    }

    public String appendFile() {
        String doneString = (done == true ? "1" : "0");
        return "DEADLINE" + " | " + doneString + " | " + this.name + " | " + this.time;
    }

    @Override
    public String toString() {
        String doneString = (super.done == true ? "✓" : "✗");
        return "[D]" + "[" + doneString + "] " + this.name + " (by: " + this.time + ")";
    }
}

