public class Todo extends Task{
    private Todo(String name) {
        super(name);
    }

    /**
     * Creates a Todo instance.
     * @param name Name of the Todo task.
     * @return A new Todo instance.
     */
    public static Todo createTodo(String name) {
        return new Todo(name);
    }

    /**
     * Returns the error message for the case where the name of the
     * Todo task is empty.
     * @return Error message for missing name.
     */
    @Override
    public String returnMissingNameError() {
        return "The name of a todo task cannot be empty.";
    }

    /**
     * Returns the Todo instance's Save Data String used by the Storage class when writing to
     * duke data text file on disk.
     * @see Storage#saveTasksToDisk(TaskList)
     * @return The Save Data String of the Todo instance.
     */
    @Override
    public String getSaveDataString() {
        String saveData = "";
        saveData += this.isDone ? 1 : 0;
        saveData += " T " + this.name;
        return saveData;
    }

    /**
     * Returns String representation of the Todo instance.
     * @return String representation of the Todo instance.
     */
    @Override
    public String toString() {
        String marked = this.isDone ? "[Y] " : "[N] ";
//        String marked = this.isDone ? "[✓] " : "[✗] ";
        return "[T]" + marked + this.name;
    }
}
