public abstract class Task {
    protected String item;
    private boolean done;
    protected String taskType;
    protected Task(String item){
        this.item = item;
        this.done = false;
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
