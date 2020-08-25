public class Todo extends Task {
    Todo(String s) {
        super(s);
    }

    private Todo(String name, boolean completed) {
        super(name, completed);
    }

    @Override
    Todo completeTask() {
        return new Todo(this.name, true);
    }

    @Override
    public String toString() {
        return "[todo]" + super.toString();
    }
}
