import java.time.LocalDateTime;

public class Todo extends Task{
    public Todo(String description) {
        super(description, LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
