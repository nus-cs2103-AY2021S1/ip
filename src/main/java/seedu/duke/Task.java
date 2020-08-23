package seedu.duke;

public class Task {
    protected String task;
    protected boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    public String getStatus() {
        return this.isDone ? "[✓]" : "[✘]";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getTask() {
        return this.task;
    }
    public boolean getIsDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return this.getStatus() + " " + this.getTask();
    }

    public String getStorageString(String task) {
        String done = "";
        if (this.isDone) {
            done = "1";
        } else {
            done = "0";
        }
        return task + " ~ " + done + " ~ " + this.getTask();
    }

    public String getStorageString(String task, String date) {
        String done = "";
        if (this.isDone) {
            done = "1";
        } else {
            done = "0";
        }
        return task + " ~ " + done + " ~ " + this.getTask() + " ~ " + date;
    }
}
