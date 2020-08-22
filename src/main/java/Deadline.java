import java.util.HashMap;

public class Deadline extends Task {

    private TimePoint deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = TimePoint.of(deadline);
    }

    public Deadline(String name, String deadline, boolean done) {
        super(name, done);
        this.deadline = TimePoint.of(deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.toString() + ")";
    }

    @Override
    public HashMap<String, String> convertToHashMap() {
        HashMap<String, String> dict = super.convertToHashMap();
        dict.put("type", "Deadline");
        dict.put("deadline", this.deadline.toSaveString());
        return dict;
    }
}
