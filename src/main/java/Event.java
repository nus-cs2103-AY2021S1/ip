package main.java;

public class Event extends Deadline{

    public Event(String task, boolean complete,String deadline) {
        super(task, complete,deadline);
    }

    @Override
    public String stringify(){
        if(this.complete == true) {
            return "[E][DONE] " + this.task + " " +"(at: " +this.deadline+")" ;
        }else{
            return "[E][UNDONE] " + this.task + " " +"(at: " +this.deadline+")" ;
        }
    }
}