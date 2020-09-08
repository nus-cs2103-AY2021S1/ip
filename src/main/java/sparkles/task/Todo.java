package sparkles.task;

/**
 * Represent a Todo object.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
        super.typeOfTask = "T";
    }

    /**
     * Package the Todo to a format used to store in the task.txt.
     * A file in the local disk to store tasks.
     *
     * @return String of Task's details in custom disk format
     */
    @Override
    public String convertToDiskFormat() {
        return "     T | " + super.convertToDiskFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
