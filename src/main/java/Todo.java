import java.time.LocalDate;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String taskFileString() {
        return "T | " + (super.isDone ? "1 |" : "0 |") + super.description;
    }

    @Override
    public LocalDate getDate() {
        return null;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
