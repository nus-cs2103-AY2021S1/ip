package dependencies.storage;

import static dependencies.storage.CompletionState.*;

public class Task {
    protected CompletionState state;
    protected String task;

    Task(String task) {
        this.task = task;
        this.state = UNFINISHED;
    }

    public void completed() {
        this.state = COMPLETED;
    }

    public String toString() {
        return String.format("[%s] %s", state == UNFINISHED ? "X" : Character.toString((char)0x2713) , task);
    }
}
