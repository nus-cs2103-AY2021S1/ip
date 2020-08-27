package duke.task;

public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toFileString() {
        String doneInteger = isDone ? "1" : "0";
        return "T | " + doneInteger + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
