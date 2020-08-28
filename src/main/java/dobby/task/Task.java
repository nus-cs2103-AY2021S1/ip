package dobby.task;

public class Task {
    private final String description;
    private boolean isDone;
    private String tag;

    Task(String description, String tag) {
        this.description = description;
        this.isDone = false;
        this.tag = tag;
    }

    public String getDescription() {
        String checkbox = "[";
        if (this.isDone()) {
            checkbox = checkbox + "\u2713] ";
        } else {
            checkbox = checkbox + "\u2718] ";
        }
        return checkbox + this.description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getTag() {
        return this.tag;
    }
}
