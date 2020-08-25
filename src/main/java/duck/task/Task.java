package duck.task;

import duck.ui.Colour;

import java.io.Serializable;

abstract public class Task implements Serializable {
    private String description;
    private boolean isDone;

    public Task(String d) {
        this.description = d;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getDone() {
        return this.isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public String getStatus() {
        String check = this.isDone ? "\u2713" : "\u2718";
        String status = "[" + check + "] " + this.getDescription();
        return this.isDone
                ? Colour.Green(status)
                : Colour.Red(status);
    }
}
