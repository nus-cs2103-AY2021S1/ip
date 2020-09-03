public class ToDo extends Task {
    // ToDos: Tasks without any date/time attached to it
    // Example: Visit new theme park

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return "ToDo";
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    
    @Override
    public String toStringInFile() {
        return "T" + super.toStringInFile();
    }
}
