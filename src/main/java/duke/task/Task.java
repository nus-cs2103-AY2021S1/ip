package duke.task;

import duke.Ui;

import java.time.LocalDate;

public class Task{
    private final String description;
    private boolean isDone;
    protected final static Ui ui = new Ui();

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

    public void markAsDone() {
        if (!isDone) {
            this.isDone = true;
            ui.markDoneSuccess("   [" + this.getStatusIcon() + "] " + this.description + "\n");
        } else {
            ui.markDoneRepeat();
        }
    }

    public boolean compareDate(LocalDate date) {
        return false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
