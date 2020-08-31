package duke;

/**
 * Todo is a type of Task
 */
public class Todo extends Task{
    public Todo(String name, boolean isCompleted) {
        super(name,isCompleted);
    }

    /**
     * @return String representing the symbol of the task
     */
    public String getTaskSymbol() {
        return "[T]";
    }

    /**
     * Formats the Todo object to be stored in the Database.
     * @return the storage of the event as a String
     */
    public String storeFormat() {
        return String.format("%s %s %s",this.getTaskSymbol(),this.isDone(),this.description);
    }
}
