public class Todo extends Task {
    
    static {
        format = "todo <task description>";
    }

    public Todo(String description) {
        super(description);
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
