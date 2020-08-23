public class Task {
    protected boolean isDone;
    protected String description;
    protected static int totalTasks = 0;
    protected TaskType taskType;

    /**
     * Instantiates Task object.
     * @param description Description of the command.
     * @param taskType Type of the command.
     */
    public Task(String description, TaskType taskType) {
        this.isDone = false;
        this.description = description;
        this.taskType = taskType;
    }

    /**
     * Instantiates Task object.
     * @param description Description of the command.
     * @param taskType Type of the command.
     * @param done Checks if it is a done command.
     */
    public Task(String description, TaskType taskType, int done) {
        this.description = description;
        this.taskType = taskType;
        this.isDone = false;
        if (done == 1) {
            this.isDone = true;
        }
    }

    /**
     * Asserts that the task is completed.
     * @return void
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Overrides toString method so as to customize output string format.
     * @return String in our desired format.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return taskType + "[✓] " + this.description;
        } else {
            return taskType + "[✘] " + this.description;
        }
    }
}
