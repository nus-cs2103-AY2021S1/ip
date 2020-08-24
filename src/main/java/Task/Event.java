package main.java.Task;

import java.time.LocalDate;

public class Event extends Deadline{

    public Event(String task, boolean isComplete,LocalDate date) {
        super(task, isComplete ,date);
    }

    /**
     * Returns the description of the task and its completion in a string.
     *
     * @return Description of task in a String.
     */
    @Override
    public String stringify(){
        if(this.isComplete == true) {
            return "[E][✓] " + this.task + "(at:" +this.deadline+")" ;
        }else{
            return "[E][✗] " + this.task + "(at:" +this.deadline+")" ;
        }
    }
}