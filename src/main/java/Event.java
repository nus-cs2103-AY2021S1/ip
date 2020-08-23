import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

public class Event extends ComplexTask {

    protected Event(String description, String at) {
        super(description, at);
    }

    protected Event(String description, LocalDate date) {
        super(description, date);
    }

    protected Event(String description, LocalDateTime dateAndTime) {
        super(description, dateAndTime);
    }

    protected Event(String description, LocalTime time) {
        super(description, time);
    }

    @Override
    public String toString() {
        return convertToString(TaskType.EVENT);
    }
}
