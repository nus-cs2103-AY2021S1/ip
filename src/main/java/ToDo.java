/**
 * Todo class is a subclass of task, it holds
 * information of a todo task without date and time.
 */
public class ToDo extends Task{

    public ToDo(String description){
        super(description);
    }

    public ToDo(String description, boolean isDone){
        super(description, isDone);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

}
