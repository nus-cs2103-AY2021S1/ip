package duke;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns an array of Strings representing the state of the task, to be passed to Storage to 
     * be formatted and written to a file.
     * @return Array of Strings representing the current state of the Task.
     */
    @Override
    public String[] serialize() {
        String[] output = new String[3];
        output[0] = this.isDone
                ? "1"
                : "0";
        output[1] = "Todo";
        output[2] = description;

        return output;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
