public abstract class Task {

    /** Alphabet tag for the respective task **/
    protected String tag;
    /** Boolean variable to check if the task is completed **/
    protected boolean isDone = false;
    /** Name of the task **/
    protected String taskName;

    /**
     *Class constructor
     *
     */
    public Task(String taskName){
        this.taskName = taskName;
    }

    /**
     * Mark the task as completed
     *
     */
    public void complete(){
        this.isDone = true;
    }

    /**
     * Prints add task message for the current task
     *
     */
    public void printAddTask(){
        System.out.println(
                String.format("Got it. I've added this task:\n  %s",this.toString())
        );
    }

    /**
     * Prints delete task message for the current task
     *
     */
    public void printDeleteTask(){
        System.out.println(
                String.format("Noted. I've removed this task:\n %s",this.toString())
        );
    }

    /**
     * get the string format of the stored task in the hard drive
     *
     * @return String of the stored task
     */
    public String safeFileFormat(){
        return "";
    }

}
