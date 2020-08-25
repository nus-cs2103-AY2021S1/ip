/**
 * The {@code Todo} class extends the {@code Task} class.
 */
public class Todo extends Task {
    
    /**
     * Initialises a Todo task.
     * 
     * @param description Description of the Todo task.
     */
    public Todo (String description) {
        super(description.split("todo ")[1], "T");
    }
}