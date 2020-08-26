package duke.exceptions;

public class TaskOutOfBoundException extends Exception{
    private int taskNum;
    public TaskOutOfBoundException(String err, int taskNum){
        super(err);
        this.taskNum = taskNum;
    }

    @Override
    public String getMessage(){
        return "😝OOPS!!! You do not have " + taskNum + (taskNum > 1 ? " task " : "duke/tasks ") + "in the schedule.";
    }
}
