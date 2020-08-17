package main.java;

public class Deadline extends Task{
    public String deadline;
    public Deadline(String task, boolean complete,String deadline) {
        super(task, complete);
        this.deadline = deadline;
    }

    @Override
    public String stringify(){
        if(this.complete == true) {
            return "[D][DONE] " + this.task + " " +"(by: " +this.deadline+")" ;
        }else{
            return "[D][UNDONE] " + this.task + " " +"(by: " +this.deadline+")" ;
        }
    }
}
