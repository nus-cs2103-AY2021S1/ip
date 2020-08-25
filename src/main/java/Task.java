public class Task {
    protected String task_info;
    protected boolean task_completion;

    public Task(String task_info) {
        this.task_info = task_info;
        this.task_completion = false;
    }

    public void completeTask() {
        this.task_completion = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", task_completion ?  "Done" : "!!!!", task_info);
    }

}