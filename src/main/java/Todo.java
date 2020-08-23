import java.time.LocalDate;

public class Todo extends Task {
    protected boolean hasTime = false;

    public Todo(String description) {
        super(description);
    }



    @Override
    public String toString() {
        return  super.toString().replace("[\u2718]", "[T][\u2718]");
    }

    @Override
    public String deleteMessage() {
        return super.deleteMessage().replace("[\u2718]", "[T][\u2718]");
    }
}
