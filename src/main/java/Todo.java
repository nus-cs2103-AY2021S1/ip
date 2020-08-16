public class Todo extends Task{

    Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        String doneString = (super.done == true ? "✓" : "✗");
        return "[T]" + "[" + doneString + "] " + this.name;
    }
}
