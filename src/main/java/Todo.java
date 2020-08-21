public class Todo extends Task {

    public Todo(String description, int index) {
        super(description, index);
    }
    @Override
    public String getStatusWithIndex() {
        return isDone ? index + ". " + "[T][\u2713] " + this.description
                : index + ". " + "[T][\u2718] " + this.description; //return tick or X symbols
    }
    @Override
    public String toString() {
        return isDone ? "[T][\u2713] " + this.description : "[T][\u2718] " + this.description;
    }
}
