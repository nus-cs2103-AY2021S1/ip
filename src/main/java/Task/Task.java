package main.java.Task;

public class Task{
    public String task;
    public boolean complete;
    public Task(String task, boolean complete){
        this.task = task;
        this.complete = complete;
    }

    /**
     * Returns the description of the task and its completion in a string.
     *
     * @return Description of task in a String.
     */
    public String stringify(){
        if(this.complete == true) {
            return "[T][✓] " + this.task;
        }else{
            return "[T][✗] " + this.task;
        }
    }
}
