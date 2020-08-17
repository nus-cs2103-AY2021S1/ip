package dependencies.task;

import static dependencies.task.CompletionState.*;

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

}
