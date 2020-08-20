public class Task {
    private int task_num;
    private String task_info;
    private boolean task_completion;

    public Task(int task_num, String task_info, Boolean task_completion) {
        this.task_num = task_num;
        this.task_info = task_info;
        this.task_completion = task_completion;
    }

    public void completeTask() {
        this.task_completion = true;
    }

    @Override
    public String toString() {
        return String.format("%d.[%s] %s", task_num, task_completion ?  "Done" : "!!!!", task_info);
    }

}