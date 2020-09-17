/**
 * A class that has tasks with the type "T" which represents Todo.
 */
public class Todo extends Task {

    private final String type;

    /**
     * Constructor
     * @param desc the description of the todo.
     * @param isDone the status Icon of the todo.
     */
    public Todo(String desc, boolean isDone) {
        super(desc, isDone, null);
        type = "T";
    }

    /**
     * Checks the given task is a valid todo.
     * @param desc the description of the task.
     * @param isDone the status icon of the task
     * @return a Todo if the input is valid.
     * @throws DukeException with the message that the todo cannot be empty.
     */
    public static Todo makeToDo(String desc, boolean isDone) throws DukeException {
        if (desc.length() == 0) {
            throw new DukeException("The description of a todo cannot be empty \uD83D\uDE11");
        }
        return new Todo(desc, isDone);
    }

    /**
     * Gets the type of the task.
     * @return the type "T".
     */
    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
