public abstract class Task {

    //Store the messages
    private final String message;

    //Boolean to check if task is completed
    private boolean completed;

    //Constructor for Task
    public Task(String description){

        //Store the message
        this.message = description;

        //Mark the current task as not done
        this.markUnDone();
    }

    public String getMessage(){

        //Return the message
        return this.message;
    }

    public boolean isDone(){

        //returns true if the task is done
        return this.completed;
    }

    public abstract String getType();

    public void markDone(){

        //Mark the task as complete
        this.completed = true; //mark the task as done

    }

    public void markUnDone(){

        //Mark the test as not done
        this.completed = false; //mark the task as undone
    }

    public String getStatusIcon(){

        //Get the statusIcon based on state
        return (this.isDone() ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString(){

        //Convert the object to string
        return String.format("[%s] %s", this.getStatusIcon(), this.getMessage());
    }
}
