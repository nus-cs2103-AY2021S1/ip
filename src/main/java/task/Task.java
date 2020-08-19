package task;

public abstract class Task {
    protected String item;
    private boolean done;
    protected String taskType;
    protected Task(){
        this.item = "";
        this.done = false;
        this.taskType = "-";
    }

    protected Task(String item){
        this();
        this.item = item;
    }

    public void setDone() {
        done = true;
    }

    @Override
    public String toString() {
        String typeString = "[" + taskType + "]";
        String doneString = "";
        if(done){
            doneString = "[✓]";
        }
        else{
            doneString = "[✗]";
        }
        return typeString + " " + doneString + " " + item;
    }
}
