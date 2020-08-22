package main.java;

public class Task {
    protected TaskStates state;
    protected String description;

    Task(String task){
        state = TaskStates.UNDONE;
        this.description = task;
    }

    // set task as done
    public void markAsDone(){
        state = TaskStates.DONE;
    }

    // returns true if task is done
    public boolean isDone(){
        return state == TaskStates.DONE;
    }

    //return tick or X symbols
    public String getStatusIcon() {
        return (state == TaskStates.DONE ? "\u2713" : "\u2718");
    }

    @Override
    public String toString() {
        switch(state){
            case DONE:
                return "[\u2713] " + description;
            case UNDONE:
                return "[\u2718] " + description;
            default:
                return description;
        }
    }
}
