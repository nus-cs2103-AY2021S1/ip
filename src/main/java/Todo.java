import java.time.LocalDateTime;

public class Todo extends Task{

    public Todo(String description, boolean done) {
        super(description, LocalDateTime.now(), done);
    }

    public Todo(String description) {
        super(description, LocalDateTime.now());
    }

    public String writeToFile() {
        return "T|" + super.writeToFile();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
