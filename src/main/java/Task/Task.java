package main.java.Task;

public class Task{
    public String task;
    public boolean isComplete;

    public Task(String task, boolean isComplete){
        this.task = task;
        this.isComplete = isComplete;
    }

    public String stringify(){
        if(this.isComplete == true) {
            return "[T][✓] " + this.task;
        }else{
            return "[T][✗] " + this.task;
        }
    }
}
