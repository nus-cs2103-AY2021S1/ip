public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
        taskType = "T";
    }
    
    public ToDo(String uniqueId, boolean isDone, String description) {
        super(uniqueId, isDone, description);
        taskType = "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}