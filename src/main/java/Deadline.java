import java.time.LocalDateTime;

public class Deadline extends Task {

    static char sym = '\u270F';

    public Deadline(String desc, LocalDateTime time) {
        super(desc);
        symbol = sym;
        this.time = "/" + time.format(formatter);
    }


    public Deadline(String desc) {
        super(desc);
        symbol = sym;
    }
}
