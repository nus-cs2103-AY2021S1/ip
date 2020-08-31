package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }
    
    public String getParsedTask() {
        return "todo " + this.description + System.lineSeparator()
                + this.done + System.lineSeparator();
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
