import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

public class Deadline extends ComplexTask {

    protected Deadline(String description, String at) {
        super(description, at);
    }

    protected Deadline(String description, LocalDate date) {
        super(description, date);
    }

    protected Deadline(String description, LocalDateTime dateAndTime) {
        super(description, dateAndTime);
    }

    protected Deadline(String description, LocalTime time) {
        super(description, time);
    }

    @Override
    public String toString() {
        return convertToString(TaskType.DEADLINE);
    }
}
