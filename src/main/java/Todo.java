import java.time.format.DateTimeFormatter;

public class Todo extends Task {
    protected boolean hasTime = false;

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, int isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return super.toString().replace("[", "[T][");
    }

    public String data() {
        return  "T" + super.data();
    }
}
