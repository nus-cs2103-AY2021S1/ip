package Duke;

public class Todo extends Task {

    /**
     * The constructor for the Todo object which extends Task{@link Task}.
     *
     * @param description simply the descriptiong of the todo object.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
