package duke.tasks;

/**
 * Todo class which carries tasks of type Todo and extends of the base Task class.
 */

public class Todo extends Task {

    /**
     * Constructor for tasks of todo type.
     * @param description   Description of the activity
     * @param index         Numbers for the indexing process of the tasks
     * @param isDone        Task completion status
     */

    public Todo(String description, int index, boolean isDone) {
        super(description, index, isDone);
        super.type = TaskType.TODO;
    }

    /**
     * Method that returns the text version of task with index.
     * @return String representation for todo objects with indexing.
     */

    @Override
    public String getStatusWithIndex() {
        return String.format("%s. %s", index, toString());
    }

    /**
     * Default toString() definition for Todo tasks.
     * @return String representation for todo objects.
     */

    @Override
    public String toString() {
        return String.format("%s%s%s", super.type, isDone ? super.done : super.start, this.description);
    }
}
