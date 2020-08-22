import java.time.LocalDate;

public abstract class Task {

    private String taskName;
    private boolean isDone = false;

    protected Task(String taskName) {
        this.taskName = taskName;
    }

    public void competeTask() {
        isDone = true;
    }
    
    public boolean isOnSameDay(LocalDate localDate) {
        return false;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[\u2713] " + taskName;
        } else {
            return "[\u2718] " + taskName;
        }
    }
}
