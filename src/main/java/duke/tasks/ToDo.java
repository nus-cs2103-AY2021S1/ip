package duke.tasks;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }
    
    @Override
    protected boolean isComplete() {
        return super.isComplete();
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
