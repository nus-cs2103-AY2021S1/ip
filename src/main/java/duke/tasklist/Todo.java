package duke.tasklist;

import java.util.ArrayList;

/**
 * Todo class is a subclass of Task.
 * Todo stores each Todo's description.
 * @author Maguire Ong
 */

public class Todo extends Task {
    public Todo(String description, ArrayList<String> tags) {
        super(description, tags);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
