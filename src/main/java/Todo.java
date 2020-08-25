public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getStoringFormat() {
        if (this.isDone) {
            return "T ~ 1 ~ " + this.description;
        } else {
            return "T ~ 0 ~ " + this.description;
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
