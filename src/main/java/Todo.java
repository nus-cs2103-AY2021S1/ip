public class Todo extends Task {

    Todo(String name) {
        super(name);
    }

    public String appendFile() {
        String doneString = (done == true ? "1" : "0");
        return "todo" + " | " + doneString + " | " + this.name;
    }

    @Override
    public String toString() {
        String doneString = (super.done == true ? "✓" : "✗");
        return "[T]" + "[" + doneString + "] " + this.name;
    }
}
