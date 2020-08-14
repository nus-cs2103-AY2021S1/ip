public class Todo extends Task {
    Todo(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return "[todo]" + super.toString();
    }
}
