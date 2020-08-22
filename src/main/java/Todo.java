public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    public static Todo parse(String[] split) {
        Todo todo = new Todo(split[2]);
        if (split[1].equals("1")) {
            todo.markDone();
        }
        return todo;
    }

    public String serialize() {
        return "T|" + super.serialize();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
