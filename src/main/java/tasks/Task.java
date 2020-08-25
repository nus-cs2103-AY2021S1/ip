package tasks;

abstract class Task {
    protected String description;
    protected boolean done;
    public static final String SEP = "#sep#";

    protected Task(String description, boolean done) {
        this.description = description;
        this.done = done;
    }


    /**
     * Returns the check for if the task is already done.
     * @return
     */
    public boolean done(){
        return this.done;
    }

    /**
     * Mark Tasks.Task object as done
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

    /**
     * Takes done status and attributes to encode the sep
     * @return a encoded string version of task for writing to text file.
     */
    public String saveTask(){
        return this.done + SEP + description; 
    }
}
