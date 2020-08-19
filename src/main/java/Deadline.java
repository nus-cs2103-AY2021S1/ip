public class Deadline extends Task {
    public Deadline(String desc, String time) {
        super(desc);
        symbol = "!" + "\u270F";
        this.time = "(" + time + ")";
    }
}
