import java.util.HashMap;

public class Deadline extends Task {

    private String deadline = "";

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

    @Override
    public HashMap<String, String> convertToHashMap() {
        HashMap<String, String> dict = super.convertToHashMap();
        dict.put("type", "Deadline");
        dict.put("deadline", this.deadline);
        return dict;
    }
}
