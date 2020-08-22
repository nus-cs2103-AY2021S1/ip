public class Todo extends Task {

    Todo(String description, int id) {
        super(description, id);
    }

    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.description;
    }
}
