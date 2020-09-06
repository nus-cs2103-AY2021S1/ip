package duke.task;


/**
 * <h1> Todo Task class </h1>
 *
 * The Todo class is a child class of Task
 * It does not have any additional properties
 * from a Task Object
 *
 * @author Lee Penn Han
 * @version 1.0
 * @since 2020-08-25
 */
public class Todo extends Task {

    protected Todo(String task) {
        super(task);
    }

    public static Todo createTodo(String task) {
        return new Todo(task);
    }

    @Override
    public String toString() {
        String done = this.done ? "O" : "X";
        return "[T][" + done + "] " + this.task;
    }
}
