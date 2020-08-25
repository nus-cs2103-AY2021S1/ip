public class ToDo extends Task {
// ToDos: tasks without any date/time attached to it e.g., visit new theme park
    public ToDo(String description) throws PandaBotEmptyTaskDescriptionException {
        super(description);
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    
    @Override
    public String saveAsText() {
        return "T | " + super.saveAsText();
    }
}
