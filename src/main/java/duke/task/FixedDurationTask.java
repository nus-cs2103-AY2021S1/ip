package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class FixedDurationTask extends Task {
    private String duration;

    /**
     * Instantiate Fixed Duration Task Object.
     * @param description  Description of Event.
     * @param time  Time of event.
     */
    public FixedDurationTask(String description, String time) {
        super(description);
        this.duration = time;
    }
    @Override
    public String toString() {
        return "[FT]" + super.toString() + " (" + duration + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        FixedDurationTask fdTask = (FixedDurationTask) o;
        return Objects.equals(duration, fdTask.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), duration);
    }

    @Override
    public String convertToData() {
        return "FT | " + (isDone ? 1 : 0) + " | " + description + " | " + duration;
    }
}
