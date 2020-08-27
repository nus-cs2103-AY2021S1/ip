package task;

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
        if(item.isBlank()){
            throw new EmptyStringException("Task cannot be empty.");
        }
        this.item = item;
    }

    public void setDone() {
        isDone = true;
    }
    public abstract String encode();

    @Override
    public String toString() {
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
