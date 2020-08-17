public class Todo extends Task {
    
    public Todo (String description) {
        super(description.split("todo ")[1], "T");
    }
}