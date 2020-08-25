public class Deadline extends Task {
    static char sym = '\u270F';

    public Deadline(String desc, String time) {
        super(desc);
        symbol = sym;
        this.time = "(" + time + ")";
    }

    public Deadline(String desc) {
        super(desc);
        symbol = sym;
    }
}
