import java.time.LocalDate;

public class Task{
    private String lines = ".~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.\n";
    private String description;
    private boolean isDone;

    Task(String message) {
        this.description = message.stripLeading().stripTrailing();
        this.isDone = false;
    }

    Task(String message, boolean isDone) {
        this.description = message.stripLeading().stripTrailing();
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public Task markAsDone() {
        if (!isDone) {
            this.isDone = true;
            Print.print(" Good Job!!! You cleared this task:\n   [" + this.getStatusIcon() + "] "
                    + this.description + "\n");
        } else {
            Print.print(" You have already completed this task! *Woof woof*\n");
        }
        return this;
    }

    public boolean compareDate(LocalDate date) {
        return false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
