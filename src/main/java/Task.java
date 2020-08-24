/**
 * Parent class of all tasks that Duke can handle
 */

public class Task {

    protected String taskDesc;
    protected boolean taskStatus = false;

    public Task(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    /**
     * set the status of this task to be completed
     */
    public void completeTask() {
        taskStatus = true;
    }

    /**
     * Depending on taskStatus, return a tick if complete, else a cross
     * @return a tick or cross, depending on taskStatus
     */
    public String getIcon() {
        return ( taskStatus ? "\u2713" : "\u2718" );
    }
    @Override
    public String toString() {
        return "["+this.getIcon()+"] " + this.taskDesc;
    }
}
