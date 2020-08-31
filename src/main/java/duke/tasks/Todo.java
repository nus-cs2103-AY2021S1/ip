package duke.tasks;

/**
 * Todo is a type of Task.
 */

public class Todo extends Task {

    public Todo(String name){
        super(name);
    }
    /**
     * The toString method of Todo.
     * @return The required String format of a todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
