package main.java.Task;

public class toDo extends Task{

    public toDo(String task, boolean complete) {
        super(task, complete);
    }

    /**
     * Returns the description of the task and its completion in a string.
     *
     * @return Description of task in a String.
     */
    @Override
    public String stringify(){
        if(this.complete == true) {
            return "[T][✓] " + this.task;
        }else{
            return "[T][✗] " + this.task;
        }
    }
}