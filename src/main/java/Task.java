public class Task {

    private final String message; //string var to store the message
    private boolean completed; //boolean to keep track if the task is done

    public Task(String description){
        this.message = description; //store the description
        this.markUnDone(); //mark the task as not done
    }

    public String getMessage(){
        return this.message;
    }

    public boolean isDone(){
        return this.completed; //returns if the task is done
    }

    public void markDone(){
        this.completed = true; //mark the task as done
    }

    public void markUnDone(){
        this.completed = false; //mark the task as undone
    }

    public String getStatusIcon(){
        return (this.isDone() ? "\u2713" : "\u2718"); //return tick or X symbols
    }
}
