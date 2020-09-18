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

    @Override
    public boolean equals(Task task) {
        return task instanceof EventTask &&
                this.description.equals(task.description) &&
                this.date.equals(((EventTask) task).date);
    }

    @Override
    public String toString() {
        return indicator + super.toString() + " (At: " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ")";
    }

    @Override
    public String saveString() {return "E/break/" + this.done + "/break/" + description + "/break/" + date;}
}
