public class TodoTask extends Task {

    /**
     * constructor for a Todo object
     * @param name description of the todo task
     * @param isComplete is it completed or not
     */
    public TodoTask(String name, boolean isComplete) {
        super(name, isComplete);
    }

    public String getType(){
        return "T";
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
