public class Task {
    protected String description;
    protected boolean done;

    private Task(String description, boolean done) {
        this.description = description;
        this.done = done;
    }

    /**
     * Takes in a description and a given id for the task and creates
     * a Task object as entry in Duke
     * @param desc
     * @return
     */
    public static Task makeTask( String desc) {
        return new Task(desc, false);
    }

    /**
     * Returns the check for if the task is already done.
     * @return
     */
    public boolean done(){
        return this.done;
    }

    /**
     * Mark Task object as done
     */
    public void doTask(){
        this.done = true;
    }

    /**
     * Get the description of the task
     * @return description of task
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Checklist icon
      * @return either done or not done
     */
    public String statusIcon(){
        return this.done ? "[\u2713] " : "[\u2718] ";
    }

    @Override
    public String toString(){
        return this.statusIcon()+this.getDescription();
    }

}
