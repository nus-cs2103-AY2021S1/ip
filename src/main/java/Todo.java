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
public class Todo extends Task{

    protected Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        String done = this.done ? "\u2713" : "\u2718";
        return "[T][" + done + "] " + this.task;
    }
}
