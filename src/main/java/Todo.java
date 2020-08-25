public class Todo extends Task {
    /**
     * Instantiates Todo object.
     * @param description Description of todo command.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
        Task.TOTAL_TASKS++;
    }

    /**
     * Instantiates Todo object.
     * @param description Description of todo command.
     * @param done Checks if it is a done command.
     */
    public Todo(String description, int done) {
        super(description, TaskType.TODO, done);
        Task.TOTAL_TASKS++;
    }

    /**
     * Overrides toString method so as to customize output string format.
     * @return String in our desired format.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
