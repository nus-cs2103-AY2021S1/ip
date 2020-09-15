public class Task {
    protected String newTask;
    protected boolean isDone;
    protected boolean isImported;


    /**
     * Constructs a task object.
     * @param newTask Name of task.
     */
    public Task(String newTask) {
        this.newTask = newTask;
        this.isDone = false;
        this.isImported = false;
    }
    protected Task(String newTask, boolean isDone) {
        this.newTask = newTask;
        this.isDone = isDone;
        this.isImported = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the task as done or completed.
     */
    public void setTaskAsDone() {
        if (isImported) {
            this.newTask = this.newTask.replace("\u2718", "\u2713");
        } else {
            this.isDone = true;
        }
    }

    /**
     * Return the file format form for the task.
     */
    public String fileFormat() {
        return this.getStatusIcon() + " | " + newTask;
    }

    /**
     * Return the converted time form of the task.
     */
    public String timeConverted() {
        if (isImported) {
            return newTask;
        } else {
            return this.getStatusIcon() + " | " + newTask;
        }

    }

    /**
     * To check whether the task contain the keyword searched by the user
     */
    public boolean containKeyWord(String keyword) {
        return newTask.contains(keyword);
    }
    @Override
    public String toString() {
        String a = String.format("[%s] %s", this.getStatusIcon(), newTask);
        return this.newTask;
    }
}
