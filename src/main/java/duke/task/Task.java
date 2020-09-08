package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected Priority priority;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = Priority.LOW;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.priority = Priority.LOW;
    }

    public Task(String description, boolean isDone, String priority) {
        this.description = description;
        this.isDone = isDone;
        if (priority.equals("high")) {
            this.priority = Priority.HIGH;
        } else if (priority.equals("medium")) {
            this.priority = Priority.MEDIUM;
        } else {
            this.priority = Priority.LOW;
        }
    }

    public String getStatusIcon() {
        //return ✓ or ✗ symbols
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
            this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean hasKeyword(String keyword) {
        String[] strings = this.description.split(" ");
        for (String word : strings) {
            if (keyword.equals(word)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else {
            if (obj instanceof Task) {
                Task otherTask = (Task) obj;
                return otherTask.getDescription().equals(this.description);
            } else {
                return false;
            }
        }
    }

    public String getStorageString() {
        return "";
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
