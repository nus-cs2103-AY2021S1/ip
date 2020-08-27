package duke.task;

import duke.task.Task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }
    
    public String getParsedTask() {
        return "todo " + this.description + "\n" 
                + this.done + "\n";
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
