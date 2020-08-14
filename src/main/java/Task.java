public class Task {
    protected boolean isDone;
    protected String description;
    public static int totalTasks = 0;

    Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    void setDone() {
        isDone = true;
    }
    
    @Override
    public String toString() {
        if (this.isDone) {
            return "[\u2713] " + this.description;
        } else {
            return "[\u2718] " + this.description;
        }
    }
}