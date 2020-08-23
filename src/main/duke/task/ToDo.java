package duke.task;

public class ToDo extends Task {

    /**
     * Creates new To Do task.
     *
     * @param msg Stored message for to do.
     */
    public ToDo(String msg){
        super(msg);
    }

    /**
     * Returns a formatted string representing the to do.
     *
     * @return Formatted string of the to do.
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
