package duke.tasks;

import java.time.LocalDateTime;

public class EventTask extends Task {

    private static String indicator = "[E]";
    private final LocalDateTime date;

    public EventTask(String name, LocalDateTime date) {
        super(name);
        this.date = date;
    }

    @Override
    public String toString() {
        return indicator + super.toString() + " (Date: " + date.toString() + ")";
    }

    @Override
    public String saveString() {return "E/break/" + this.done + "/break/" + name + "/break/" + date;}
}
