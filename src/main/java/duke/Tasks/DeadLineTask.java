package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadLineTask extends Task {

    private static String indicator = "[D]";
    private final LocalDateTime date;

    public DeadLineTask(String name, LocalDateTime date) {
        super(name);
        this.date = date;
    }

    @Override
    public boolean equals(Task task) {
        return task instanceof DeadLineTask &&
                this.description.equals(task.description) &&
                this.date.equals(((DeadLineTask) task).date);
    }

    @Override
    public String toString() {
        return indicator + super.toString() + " (By: " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ")";
    }

    @Override
    public String saveString() {return "D/break/" + this.done + "/break/" + description + "/break/" + date;}
}
