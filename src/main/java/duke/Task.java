package duke;

import java.time.format.DateTimeFormatter;

public class Task {
    public enum Status {
        PENDING,
        DONE;

        public String statusToSymbol() {
            if (this == DONE) {
                return "[\u2713] ";
            } else {
                return "[\u2718] ";
            }
        }

    }
    String name;
    Status status;
    public Task(String name, Status status) {
        this.name = name;
        this.status = status;
    }

    public void markAsDone() {
        this.status = Status.DONE;
    }

    public String toStore() {
        return this.toString();
    }

    public void undo() {
        this.status = Status.PENDING;
    }
}
