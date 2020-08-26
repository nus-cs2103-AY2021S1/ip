/**
 * Task class is the subclass of Deadline, Event and Todo
 * thus it holds common methods and constructors for these classes.
 */
public class Task {

    protected String description;
    protected boolean isDone;


    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, Boolean isDone){
        this.description = description;
        this.isDone = isDone;
    }


    public String getStatusIcon(){
        return (this.isDone ? "\u2713" : "\u2718");
    }

    public Task markAsDone(){

        if(this instanceof ToDo){
            return new ToDo(this.description, true);
        }else if(this instanceof Event){
            return new Event(this.description, ((Event) this).at, true);
        }else if(this instanceof Deadline){
            return new Deadline(this.description, ((Deadline) this).by, true);
        }else{
            return new Task(this.description, true);
        }

    }


    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
