import java.time.LocalDateTime;

public class Deadline extends Task {

    protected static char sym = '\u270F';

    /**
     * Constructor for Deadline task object
     * @param desc task description
     * @param time deadline
     */
    public Deadline(String desc, LocalDateTime time) {
        super(desc);
        symbol = sym;
        this.time = "/" + time.format(formatter);
    }
}
