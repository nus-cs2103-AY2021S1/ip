public class Todo extends Task {
    Todo(String s) {
        super(s);
    }

    Todo(String name, boolean completed) {
        super(name, completed);
    }

    @Override
    String getType() {
        return "todo";
    }

    @Override
    String getTime() {
        return "0";
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
