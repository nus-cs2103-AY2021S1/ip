public class Todo extends Task {

    /**
     * Creates a Todo object.
     * 
     * @param task The description of the task.
     */
    public Todo(String task) {
        super(task);
    }

    /**
     * Returns the message to be saved in the hard disk.
     * 
     * @return The string representation of this task in the local file.
     */
    @Override
    public String writeMessage() {
        String done = "";
        if (this.done) {
            done = "✓";
        } else {
            done = "✗";
        }
        return String.format("T | %s | %s", done, this.task);
    }

    /**
     * Returns the string representation of this task to the users when Duke receives list command.
     * 
     * @return The string representation of this task.
     */
    @Override
    public String toString() {
        return "[T][" + (this.done ? "✓" : "✗") + "] " + this.task;
    }
}
