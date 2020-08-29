package duke.task;

public class Task {
    protected String name;
    protected boolean isDone;

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
