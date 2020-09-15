package duke.tasks;

import java.time.LocalDateTime;

public class DeadLineTask extends Task {

    private static String indicator = "[D]";
    private final LocalDateTime date;

    public DeadLineTask(String name, LocalDateTime date) {
        super(name);
        this.date = date;
    }

    public boolean equals(DeadLineTask deadLineTask) {
        return super.equals(deadLineTask) && this.date.equals(deadLineTask.date);
    }

    @Override
    public String toString() {
        return indicator + super.toString() + " (by: " + date + ")";
    }

    @Override
    public String saveString() {return "D/break/" + this.done + "/break/" + description + "/break/" + date;}
}
