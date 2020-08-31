/**
 * Todo class is a subclass of task, it holds
 * information of a todo task without date and time.
 */
public class ToDo extends Task{
    /**
     * Constructs a todo task with the specified description.
     * @param description a string that describes the task content
     */
    public ToDo(String description){
        super(description);
    }

    /**
     * Constructs a todo task with the given description and completion status.
     * @param description a string that describes the task content
     * @param isDone a boolean value to describe the completion status
     */
    public ToDo(String description, boolean isDone){
        super(description, isDone);
    }

    /**
     * Overridden toString() method to print out the desired format of a todo task
     * @return a string representation of the task
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

}
