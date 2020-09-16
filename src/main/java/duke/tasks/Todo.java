package duke.tasks;

/**
 * Todo is a type of Task.
 */

public class Todo extends Task {
    /**
     * Constructs a Todo object.
     * @param name Description of the todo
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Gets the type of the task.
     * @return The type of a Todo which is "T"
     */
    @Override
    public String getType() {
        return "T";
    }

    public String[] toArray(){
        String status;
        if(this.isDone){
            status = "1";
        }else{
            status = "0";
        }
        String[] result = new String[]{"T", status, this.name};
        return result;
    }

    /**
     * The toString method of Todo.
     *
     * @return The required String format of a todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
