

package main.java;

public abstract class Task {

    private boolean completed;
    private String msg;

    public Task(String msg) {
        this.msg = msg;
        completed = false;
    }

    public void completeTask() {
        completed = true;
    }

    @Override
    public String toString() {
        return "[" + (completed ? "✓" : "✗") + "] " + msg;
    }
}
