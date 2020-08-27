package task;

/**
 * Todo is the basic verison of Task.
 */
public class Todo extends Task{
    /**
     * Constructor for Todo class.
     * @param item Item to be done.
     * @throws EmptyStringException if an empty string is the input.
     */
    public Todo(String item) throws EmptyStringException{
        super(item);
        taskType = "T";
    }

    /**
     * Encodes the Todo task back into the text command.
     * @return The text command used create this Todo task.
     */
    @Override
    public String encode() {
        String encoded = "todo " + item;
        if(this.isDone){
            encoded = encoded + "\n" + "done";
        }
        return encoded;
    }
}
