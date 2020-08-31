package duke.tasks;

/**
 * A task has a name/description and a status of whether it is done or not.
 */
public class Task {
    public String name;
    public boolean isDone;
    /** Construct a new Task
     * @param name Take in the name of the task.
     */
    public Task(String name){
        this.name = name;
        this.isDone = false;
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone(){
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
    }

    /**
     * Print out the name of the task.
     */
    public void showName(){
        System.out.println(this.name);
    }

    /**
     * Print out the description of the task.
     * @param index The position index of the task in a list of tasks.
     */
    public void showTask(int index){
        System.out.println(index + ". " +this.toString());
    }


    @Override
    /**
     * The toString method ofTask
     * Print the correct format of a task.
     */
    public String toString(){

        String isDoneSymbol;

        if(isDone){
            isDoneSymbol= "\u2713";
        }else{
            isDoneSymbol= "\u2718";
        }

        return "[" + isDoneSymbol + "] " + name;

    }
}
