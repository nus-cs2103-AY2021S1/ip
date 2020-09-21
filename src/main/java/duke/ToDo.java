package duke;

/**
 * Represents a <code>ToDo</code> object characterized by a <code>command</code>.
 */
public class ToDo extends Task {

    /**
     * Creates a new <code>ToDo</code> with the given <code>command</code>.
     */
    public ToDo(String command){
        super(command);
    }

    /**
     * Returns a string representation of a todo.
     * @return the string representation of this todo.
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}