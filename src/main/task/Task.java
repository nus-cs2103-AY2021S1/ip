package main.task;

public class Task {
    private final String name;
    private boolean doneState;

    public Task(String name) {
        this.name = name;
        doneState = false;
    }

    public Task(String name, boolean doneState) {
        this.name = name;
        this.doneState = doneState;
    }

    private String doneTag() {
        return doneState ? "[\u2713]" : "[\u2718]";
    }

    public void setDone() {
        doneState = true;
    }

    public String write() {
        return String.format(",%d,%s\n", doneState ? 1 : 0, name);
    }

    @Override
    public String toString() {
        return String.format("%s %s", doneTag(), name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task o = (Task) obj;
            return this.name.equals(o.name) && this.doneState == o.doneState;
        }
        return false;
    }
}
