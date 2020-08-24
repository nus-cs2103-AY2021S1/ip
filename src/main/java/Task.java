public class Task {
    protected String new_task;
    protected boolean isDone;

    public Task(String new_task) {
        this.new_task = new_task;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    
    public void set_Task_As_Done() {
        this.isDone = true;
    }
    
    public String fileFormat() {
        return this.getStatusIcon() + " | " + new_task;
    }
    
    @Override
    public String toString() {
        return  "[" + this.getStatusIcon() + "] " + new_task;
    }

    
}
