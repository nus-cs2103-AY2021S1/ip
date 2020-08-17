import java.util.ArrayList;

public class Store {
    private ArrayList<Task> taskStore;
    private int nextIndx;

    public Store() {
        this.taskStore = new ArrayList<>();
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
        taskStore.add(newTask);
        int storeSize = taskStore.size();
        StringUtils.printWithWrapper(new String[]{
                "Sure! I have added the following task to your list: ",
                newTask.toString(),
                "There " + (storeSize > 1 ? "are " : "is ") + "now " + storeSize + " " +
                        (storeSize > 1 ? "tasks " : "task ") + "in your list!"}, false);
    }

    public void markTaskAsDone(int i) {
        taskStore.set(i - 1, taskStore.get(i - 1).markAsDone());
        StringUtils.printWithWrapper(new String[]{
                "OK! I have marked the following task as done:",
                taskStore.get(i - 1).toString()}, false);
    }

    public void list() {
        StringUtils.printWithWrapper(this.taskStore.toArray(new Task[]{}), true);
    }
}
