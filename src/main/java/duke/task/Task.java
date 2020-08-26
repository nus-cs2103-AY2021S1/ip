package duke.task;

import duke.exceptions.DukeException;

public abstract class Task {
    protected String description;
    private boolean isDone;

    public Task(String s) throws DukeException {
        if (s.isBlank()) {
            throw new DukeException("Please add a nice description to your duke.task :)");
        }
        this.description = s;
        this.isDone = false;
    }

    public Task(int doneStatus, String s) {
        if (doneStatus == 1) {
            this.isDone = true;
        }
        this.description = s;
    }

    public void setDone() {
        this.isDone = !isDone;
    }

    public boolean getDoneStatus() {
        return this.isDone;
    }

    public abstract String formatTaskForDatabase();

    @Override
    public String toString() {
        String isChecked = (isDone ? "[\u2713]" : "[\u2718]");
        return isChecked;
    }
}
