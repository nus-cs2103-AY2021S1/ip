package main.java.Task;

public class toDo extends Task{

    public toDo(String task, boolean isComplete) {
        super(task, isComplete);
    }

    @Override
    public String stringify(){
        if(this.isComplete == true) {
            return "[T][✓] " + this.task;
        }else{
            return "[T][✗] " + this.task;
        }
    }
}