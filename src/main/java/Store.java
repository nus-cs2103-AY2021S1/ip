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

    public void list() {
        StringUtils.printWithWrapper(this.taskStore, true);
    }
}
