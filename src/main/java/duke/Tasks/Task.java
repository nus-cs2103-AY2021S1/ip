package duke.tasks;

import duke.text.TextStore;

public abstract class Task {

    protected String name;
    protected boolean done = false;

    Task(String name) {
        this.name = name;
    }

    public String getDescription() {
        return name;
    }

    public String toString() {
        return done ? TextStore.doneText + name : TextStore.notDoneText + name;
    }

    public abstract String saveString();

    public boolean equals(Task task) {
        return this.name.equals(task.name);
    }

    public boolean markDone() {
        if (done) {
            return false;
        } else {
            done = true;
            return true;
        }
    }
}
