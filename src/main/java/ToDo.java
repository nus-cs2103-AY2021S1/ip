public class ToDo extends Task{
    public ToDo(String todo) {
        super(todo.substring(5));
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
