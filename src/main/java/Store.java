public class Store {
    private Task[] taskStore;
    private int nextIndx;

    public Store() {
        this.taskStore = new Task[100];
        this.nextIndx = 0;
    }

    public void add(String str) {
        Task newTask = new Task(str);
        taskStore[nextIndx] = newTask;
        StringUtils.printWithWrapper(new String[]{"Added: " + str}, false);
        nextIndx++;
    }

    public void markTaskAsDone(int i) {
        taskStore[i - 1] = taskStore[i - 1].markAsDone();
        StringUtils.printWithWrapper(new String[]{
                "OK! I have marked the following task as done:",
                taskStore[i - 1].toString()}, false);
    }
    public void list() {
        StringUtils.printWithWrapper(this.taskStore, true);
    }
}
