package main.java;

public class Task{
    public String task;
    public boolean complete;
    public Task(String task, boolean complete){
        this.task = task;
        this.complete = complete;
    }

    public String stringify(){
        if(this.complete == true) {
            return "[T][✓] " + this.task;
        }else{
            return "[T][✗] " + this.task;
        }
    }
}
