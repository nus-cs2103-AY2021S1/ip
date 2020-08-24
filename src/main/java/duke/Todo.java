package duke;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTxtFormat() {
        return "todo, " + super.getTxtFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
