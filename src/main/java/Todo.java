public class Todo extends Task {

    Todo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T]" + this.getStatusIcon() + this.description;
    }
}
