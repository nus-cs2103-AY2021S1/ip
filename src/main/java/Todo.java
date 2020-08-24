public class Todo extends Task {
    protected String doneState;
    
    public Todo(String description) {
        super(description);
    }
    
    public Todo(String doneState, String description) {
        super(description);
        this.isDone = doneState.equals("1");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    
    @Override
    public String saveString() {
        return "T" + super.saveString();
    }
}