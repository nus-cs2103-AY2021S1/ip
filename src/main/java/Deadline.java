import java.time.LocalDateTime;

public class Deadline extends Task {
    public Deadline(String desc, LocalDateTime time) {
        super(desc);
        symbol = "!" + "\u270F";
        this.time = "(" + time.format(formatter) + ")";
    }
}
