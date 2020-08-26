import java.time.LocalDate;

public class Todo extends Task {

    public Todo(String description) {
        super(description, LocalDate.EPOCH);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}