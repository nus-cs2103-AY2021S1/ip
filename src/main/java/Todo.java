public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String textFormat() {
        return "todo, " + super.textFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
