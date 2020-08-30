package duke.tasks;

/**
 * Todo is a type of Task.
 */

public class Todo extends Task {

    public Todo(String name){
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
