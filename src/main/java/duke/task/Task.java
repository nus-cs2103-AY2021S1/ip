package duke.task;

import java.io.Serializable;

public class Task implements Serializable {

    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (isDone) {
            return String.format("[\u2713] %s", name);
        } else {
            return String.format("[\u2718] %s", name);
        }
    }

    public void setDone() {
        this.isDone = true;
    }
}
