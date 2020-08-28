package duke.Tasks;

/**
 * A ToDo class is a children object of the Task class
 * with the additional abilities to
 * returns original command of the Deadline
 */
public class ToDo extends Task {

    /**
     * Creates a ToDo object
     * @param description
     */
    public ToDo(String description) {

        super(description);
    }

    /**
     * Creates a ToDo object
     * @param description
     * @param isDone
     */
    public ToDo(String description, boolean isDone){

        super(description, isDone);
    }

    /**
     * Returns the original command used to create the ToDo
     * @return original command
     */
    @Override
    public String getOriginal(){

        return "todo " + getTask();
    }

    /**
     * Returns the ToDo with StatusIcon
     * @return ToDo with StatusIcon
     */
    @Override
    public String toString() {

        return "[T]" + super.toString() ;
    }
}
