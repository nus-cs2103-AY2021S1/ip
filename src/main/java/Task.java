public class Task {
    protected final String name;
    protected Boolean isDone;

    protected Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Sets whether the task is done or undone.
     * @param isDone Boolean value describing whether the task is done or undone.
     */
    public void setDoneness(Boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the name of the task.
     * @return Name of the task.
     */
    public String getName() {
        return String.valueOf(this.name);
    }

    /**
     * Returns the error message for the case where the name of the task is empty.
     * @return Error message for missing name.
     */
    public String returnMissingNameError() {
        return "The name of a task cannot be empty.";
    }

    /**
     * Returns the Task instance's Save Data String used by the Storage class when writing to
     * duke data text file on disk.
     * @see Storage#saveTasksToDisk(TaskList)
     * @return The Save Data String of the Task instance.
     */
    public String getSaveDataString() {
        String saveData = "";
        saveData += this.isDone ? 1 : 0;
        saveData += " A " + this.name;
        return saveData;
    }

    /**
     * Returns String representation of the Task instance.
     * @return String representation of the Task instance.
     */
    @Override
    public String toString() {
        String marked = this.isDone ? "[Y] " : "[N] ";
//        String marked = this.isDone ? "[✓] " : "[✗] ";
        return marked + this.name;
    }
}
