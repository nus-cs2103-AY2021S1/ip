import java.time.LocalDate;

public abstract class Task {

    protected String taskData;
    protected boolean isDone = false;

    protected Task(String taskData) {
        this.taskData = taskData;
    }

    public void competeTask() {
        isDone = true;
    }
    
    public boolean isOnSameDay(LocalDate localDate) {
        return false;
    }
    
    public abstract String generateStorageString();

    @Override
    public String toString() {
        if (isDone) {
            return "[\u2713] " + taskData;
        } else {
            return "[\u2718] " + taskData;
        }
    }
}
