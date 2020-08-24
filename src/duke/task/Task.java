package duke.task;

import duke.DukeException;

import java.time.LocalDate;

public abstract class Task {
    private String title;
    private boolean isDone;

    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    public Task(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    public Task markAsDone() throws DukeException {
        if (this.isDone) {
            throw new DukeException("Already marked as done.");
        } else {
            this.isDone = true;
            return this;
        }
    }

    public String toString() {
        String status = this.isDone ? "✓" : "✗";
        return "[" + status + "] " + this.title;
    }

    public String print() {
        String status = this.isDone ? "1" : "0";
        return status + " | " + this.title;
    }

    public abstract LocalDate getDate();
}
