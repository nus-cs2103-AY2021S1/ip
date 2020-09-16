package task;

/**
 * Task is a class that provides an abstraction for tasks.
 */
public abstract class Task {
    protected String item;
    protected boolean isDone;
    protected String taskType;
    protected Task(){
        this.item = "";
        this.isDone = false;
        this.taskType = "-";
    }


    protected Task(String item) throws EmptyStringException{
        this();
        assert item != null;
        if(item.isBlank()){
            throw new EmptyStringException("Task cannot be empty.");
        }
        this.item = item;
    }

    /**
     * Set this task as done.
     */
    public void setDone() {
        isDone = true;
    }
    public abstract String encode();

    /**
     * Converts the Task to a string.
     * String will be in the form:
     * <br> [Type] [isDone?] item to be done.
     * @return A string representing the task
     */
    @Override
    public String toString() {
        assert !taskType.equals("-");
        String typeString = "[" + taskType + "]";
        String doneString = "";
        if(isDone){
            doneString = "[✓]";
        }
        else{
            doneString = "[✗]";
        }
        return typeString + " " + doneString + " " + item;
    }
}
