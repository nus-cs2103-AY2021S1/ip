package main.java;

import java.time.LocalDate;

public class Deadline extends Task{
    public LocalDate deadline;
    public Deadline(String task, boolean complete,LocalDate deadline) {
        super(task, complete);
        this.deadline = deadline;
    }

    @Override
    public String stringify(){
        if(this.complete == true) {
            return "[D][✓] " + this.task +"(by:" +this.deadline+")" ;
        }else{
            return "[D][✗] " + this.task +"(by:" +this.deadline+")" ;
        }
    }
}
