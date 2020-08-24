package duke.task;

/**
 * Represents a Todo task
 */
public class Todo extends Task {

    /**
     * Class constructor with no extra arguments.
     * @param description String representing description of task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Class constructor with extra boolean argument
     * @param description String representing description of task.
     * @param isDone Boolean representing whether task is done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String write() {
        return "\ntodo," + super.write();
    }

//    public static void main(String[] args) {
//        Task todo = new Todo("read book");
//        System.out.println(todo);
//    }
}
