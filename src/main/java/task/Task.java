package task;

public class Task {
    private final String item;
    private boolean isCompleted;

    Task(String item, boolean isCompleted) {
        this.item = item;
        this.isCompleted = isCompleted;
    }

    public void completeTask() {
        this.isCompleted = true;
    }

    public String getItem() {
        if (isCompleted) {
            return "[O]" + this.item;
        } else {
            return "[X]" + this.item;
        }
    }

    public String getInput() {
        return this.item;
    }
}
