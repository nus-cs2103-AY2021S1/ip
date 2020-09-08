/**
 * a task waiting to be done
 */
public class Todo extends Task{

    /**
     * Todo constructor
     * @param description details of the task
     */
    public Todo(String description){
        super(description);
    }

    /**
     * task to be written as string
     * @return string to be presented in a list
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

    /**
     * task to be written in file
     * @return string to be written in a file
     */
    @Override
    public String write(){
        return "T|" + this.getStatusIcon() + "|" + this.description;
    }
}
