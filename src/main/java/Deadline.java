import java.time.LocalDateTime;

/**
 * deadline
 */
public class Deadline extends Task {

    /**
     * constructor
     * @param desc task description
     * @param time local date time
     */
    public Deadline(String desc, LocalDateTime time) {
        super(desc);
        this.sym = DEADLINE_SYMBOL;
        this.time = time;
    }
}
