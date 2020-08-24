package duke;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[" + this.getType() + "]" + this.getStatusIcon() + " " + this.description;
    }

    @Override
    public Todo markAsDone() {
        //int index = taskNum - 1;
        if(!this.isDone) {
            Todo newTask = new Todo(this.getDescription(), true);
            return newTask;
        }
        return this;
    }
}