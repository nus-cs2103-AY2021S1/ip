package duke;

/**
 * Todo is a type of Task
 */
public class Todo extends Task{
    private static final String TODO_SYMBOL = "[T]";
    private static final String STORE_FORMAT = "%s %s %s %s";
    
    public Todo(String name, boolean isCompleted) {
        super(name,isCompleted);
    }

    /**
     * @return String representing the symbol of the task
     */
    public String getTaskSymbol() {
        return TODO_SYMBOL;
    }

    /**
     * Formats the Todo object to be stored in the Database.
     * @return the storage of the event as a String
     */
    public String storeFormat() {
        return String.format(STORE_FORMAT,this.getTaskSymbol(),this.isDone(),this.description,this.getTag());
    }
}
