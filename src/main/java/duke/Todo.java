package duke;

/**
 * A Todo class to represent tasking that are classified as Todo
 * @author Kor Ming Soon
 */
public class Todo extends Task {

    /**
     * Constructor for Todo.
     * @param task Task detail.
     */
    public Todo (String task) {
        super(task, Tasktype.TODO, "");
    }

    /**
     * Overloaded constructor for Todo to, as default is set to not completed.
     * @param task Task detail.
     * @param isDone To indicate if the task is done or not.
     */
    public Todo (String task, boolean isDone) {
        super(task, Tasktype.TODO, "", isDone);
    }

}
