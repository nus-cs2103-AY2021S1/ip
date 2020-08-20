public class Todo extends Task{
    Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        String marked = this.isDone ? "[✓] " : "[✗] ";
        return "[T]" + marked + this.name;
    }
}
