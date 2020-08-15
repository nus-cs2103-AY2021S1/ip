public class Task {

    protected String taskDesc;
    protected boolean taskStatus = false;

    public Task(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public void completeTask() {
        taskStatus = true;
    }

    public String getIcon() {
        return ( taskStatus ? "\u2713" : "\u2718" );
    }
}
