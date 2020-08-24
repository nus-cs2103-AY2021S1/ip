public class Task {
    protected final String description;
    protected boolean isDone;
    protected final TaskType taskType;

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public String getStatusIcon() {
//            return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        //For checking purposes, V is checkmark and X is cross
        return (isDone ? "✓" : "✘");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return taskType + "[" + getStatusIcon() +"] " + description;
    }
}

//deadline task1 /by 21/8/2020 1900
//deadline task3 /by 24/8/2020 2000
//event task2 /at 21/8/2020 1800