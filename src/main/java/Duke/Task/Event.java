package Duke.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    
    protected LocalDateTime time;

    public Event(String description, LocalDateTime time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + "(at: " + time.format(format) + ")";
    }

    @Override
    public String toWrite() {
        return "E | " + (this.isDone ? '1' : '0')  + " | " + this.taskDescription + "| " + this.time.toString().replace('T', ' ');
    }
}
