//  ToDos: tasks without any date/time attached to it
//  e.g., visit new theme park

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%1$s", super.toString());
    }
}