public class Todo extends Task{
    private Todo(String name) {
        super(name);
    }

    public static Todo createTodo(String name) {
        return new Todo(name);
    }

    @Override
    public String missingNameError() {
        return "The description of a todo task cannot be empty.";
    }

    @Override
    public String toString() {
        String marked = this.isDone ? "[✓] " : "[✗] ";
        return "[T]" + marked + this.name;
    }


}
