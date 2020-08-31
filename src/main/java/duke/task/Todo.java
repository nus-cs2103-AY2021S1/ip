package duke.task;

public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }
    
    public String getParsedTask() {
        return "todo " + this.description + System.lineSeparator()
                + this.isDone + System.lineSeparator();
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Todo) {
            Todo otherTodo = (Todo) other;
            return this.description.equals(otherTodo.description) && (this.isDone == otherTodo.isDone);
        } else {
            return false;
        }
    }
}
