package Duke.Task;

import java.time.LocalDate;

public class Deadline extends Task{
    public LocalDate deadline;

    public Deadline(String task, boolean isComplete,LocalDate deadline) {
        super(task, isComplete);
        this.deadline = deadline;
    }

    /**
     * Returns the description of the task and its completion in a string.
     *
     * @return Description of task in a String.
     */
    @Override
    public String stringify(){
        if(this.isComplete == true) {
            return "[D][✓] " + this.task +"(by:" +this.deadline+")" ;
        }else{
            return "[D][✗] " + this.task +"(by:" +this.deadline+")" ;
        }
    }
}
