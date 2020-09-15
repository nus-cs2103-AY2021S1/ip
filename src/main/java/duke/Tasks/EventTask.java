package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {

    private static String indicator = "[E]";
    private final LocalDateTime date;

    public EventTask(String name, LocalDateTime date) {
        super(name);
        this.date = date;
    }

    public boolean equals(EventTask eventTask) {
        return super.equals(eventTask) && this.date.equals(eventTask.date);
    }

    @Override
    public String toString() {
        return indicator + super.toString() + " (Date: " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ")";
    }

    @Override
    public String saveString() {return "E/break/" + this.done + "/break/" + description + "/break/" + date;}
}
