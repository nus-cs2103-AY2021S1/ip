package main.java.Task;

import java.time.LocalDate;

public class Event extends Deadline{

    public Event(String task, boolean isComplete,LocalDate date) {
        super(task, isComplete ,date);
    }

    @Override
    public String stringify(){
        if(this.isComplete == true) {
            return "[E][✓] " + this.task + "(at:" +this.deadline+")" ;
        }else{
            return "[E][✗] " + this.task + "(at:" +this.deadline+")" ;
        }
    }
}