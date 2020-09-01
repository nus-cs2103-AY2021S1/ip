package duke.tasks;

import java.io.Serializable;

/**
 * Implements a task that can be completed
 */
public abstract class Task implements Serializable {
    private String name;
    private Boolean isDone;

    public Task (String s) {
        name = s;
        isDone = false;
    }

    public String getName() {
        return name;
    }

    public Boolean isDone() {
        return isDone;
    }

    public void setAsDone() {
        isDone = true;
    }

}
