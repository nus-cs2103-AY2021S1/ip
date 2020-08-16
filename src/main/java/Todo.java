public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return  super.toString().replace("[\u2718]", "[T][\u2718]");
    }
}
