import java.time.LocalDateTime;

public class Deadline extends Task {
<<<<<<< HEAD
    static char sym = '\u270F';

    public Deadline(String desc, String time) {
        super(desc);
        symbol = sym;
        this.time = "(" + time + ")";
=======
    public Deadline(String desc, LocalDateTime time) {
        super(desc);
        symbol = "!" + "\u270F";
        this.time = "(" + time.format(formatter) + ")";
>>>>>>> branch-Level-8
    }

    public Deadline(String desc) {
        super(desc);
        symbol = sym;
    }
}
