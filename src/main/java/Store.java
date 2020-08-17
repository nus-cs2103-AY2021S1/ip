public class Store {
    private Task[] taskStore;
    private int nextIndx;

    public Store() {
        this.taskStore = new Task[100];
        this.nextIndx = 0;
    }

    private Task processTaskType(String[] inputs, String type) {
        switch (type) {
            case "todo":
                return new TodoTask(inputs[0]);
            case "deadline":
                return new DeadlineTask(inputs[0], inputs[1]);
            case "event":
                return new EventTask(inputs[0], inputs[1]);
            default:
                return null;
        }
    }

    public void add(String[] inputs, String type) {
        Task newTask = processTaskType(inputs, type);
        taskStore[nextIndx] = newTask;
        nextIndx++;
        StringUtils.printWithWrapper(new String[]{
                "Sure! I have added the following task to your list: ",
                newTask.toString(),
                "There " + (nextIndx > 1 ? "are " : "is ") + "now " + nextIndx + " " +
                        (nextIndx > 1 ? "tasks " : "task ") + "in your list!"}, false);
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
