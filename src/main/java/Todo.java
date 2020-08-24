public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    @Override
    public String writeToFile() {
        return "todo" + "|" + this.getStatusSymbol() + "|"
                + this.taskName;

    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
