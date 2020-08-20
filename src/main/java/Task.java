public class Task {
    protected String description;
    protected boolean isDone;
    private String type;

    public Task(String description) {
        this.type="task";
            this.description = description.substring(1);
            this.isDone = false;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription(){
        return description;
    }

    public void finishTask(){
        this.isDone = true;
    }

    @Override
    public String toString(){
        return "["+getStatusIcon()+"]"+" "+description;
    }

}