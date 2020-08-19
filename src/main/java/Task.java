public class Task{
    private String name;        // stores the name of this task
    private Boolean closed = false;     //indicates the status of this task
    private String taskType = " ";

    Task(){}

    Task(String taskName){
        this.name = taskName;
    }

    String getName(){
        return this.name;
    }

    /**
     * Returns the current status of this task and close current task
     * @return status of current task
     */
    Boolean closeTask(){
        this.closed = true;
        return this.closed;
    }

    /**
     *
     * @return status of current task
     */
    Boolean checkDone(){
        return this.closed;
    }

    public String getTaskType(){
        return this.taskType;
    }

    @Override
    public String toString() {
        return this.name;
    }
}