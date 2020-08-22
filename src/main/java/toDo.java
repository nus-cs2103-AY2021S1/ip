package main.java;

public class toDo extends Task{

    public toDo(String task, boolean complete) {
        super(task, complete);
    }

    @Override
    public String stringify(){
        if(this.complete == true) {
            return "[T][✓] " + this.task;
        }else{
            return "[T][✗] " + this.task;
        }
    }
}