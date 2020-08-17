package src.main.java;

public class Task {
    protected String item;
    protected boolean isCompleted;

    public Task(String item) {
        this.item = item;
        this.isCompleted = false;
    }

    public void markAsDone() {
        this.isCompleted = true;
    }

    public String getItem() {
        return this.item;
    }

    public String getStatus() {
        return this.isCompleted ? "[ \u2713 ]" : "[ \u2718 ]";
    }
}
