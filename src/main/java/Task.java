public class Task {
    protected String description;
    protected boolean isDone;
    /*test*/
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description,int x){
        this.description = description;
        this.isDone = true;
    }

    public String getTask(){
        return this.description;
    }

    public void setStatus(){
        isDone = true;
    }


    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

}