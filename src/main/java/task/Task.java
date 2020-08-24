package task;

import exceptions.InvalidDescriptionException;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String s) throws InvalidDescriptionException {
        if (s.isBlank()) {
            throw new InvalidDescriptionException("Please add a nice description to your todo :)");
        }
        this.description = s;
        this.isDone = false;
    }

    public Task(int doneStatus, String s) {
        if (doneStatus == 1) this.isDone = true;
        this.description = s;
    }

    public void setDone() {
        this.isDone = !isDone;
    }

    public abstract String formatTaskForDatabase();

    @Override
    public String toString() {
        String checked = (isDone ? "[\u2713]" : "[\u2718]");
        return checked;
    }
}
