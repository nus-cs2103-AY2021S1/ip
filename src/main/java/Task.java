package main.java;

public class Task {
    protected TaskStates state;
    protected String task;

    Task(String task){
        state = TaskStates.UNDONE;
        this.task = task;
    }

    // set task as done
    public void markAsDone(){
        state = TaskStates.DONE;
    }

    //return tick or X symbols
    public String getStatusIcon() {
        return (state == TaskStates.DONE ? "\u2713" : "\u2718");
    }

    @Override
    public String toString() {
        switch(state){
            case DONE:
                return "[\u2713] " + task;
            case UNDONE:
                return "[\u2718] " + task;
            default:
                return task;
        }
    }
}
