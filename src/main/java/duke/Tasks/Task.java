package duke.tasks;

import duke.textstoreandprint.TextStore;

public abstract class Task {

    String name;
    boolean done = false;

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

    public boolean markDone() {
        if (done) {
            return false;
        } else {
            done = true;
            return true;
        }
    }
}
