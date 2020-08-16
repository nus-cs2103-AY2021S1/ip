public class Task {
    private final String description;
    private boolean isDone;

    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        String checkbox = "[";
        if (this.isDone()) {
            checkbox = checkbox + "✔] ";
        } else {
            checkbox = checkbox + "ｘ] ";
        }
        return checkbox + this.description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        this.isDone = true;
    }
}
