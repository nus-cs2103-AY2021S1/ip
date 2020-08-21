import java.util.ArrayList;

public class Store {
    private ArrayList<Task> taskStore;

    public Store() {
        this.taskStore = new ArrayList<>();
    }

    private Task processTaskType(String[] inputs, String type) throws DukeException {
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

    public void add(String[] inputs, String type) throws DukeException {
        Task newTask = processTaskType(inputs, type);
        taskStore.add(newTask);
        StringUtils.printWithWrapper(new String[]{
                "Sure! I have added the following task to your list: ",
                newTask.toString(),
                getListStatus()}, false);
    }

    public void markTaskAsDone(int i) throws DukeException {
        taskStore.set(i - 1, taskStore.get(i - 1).markAsDone());
        StringUtils.printWithWrapper(new String[]{
                "OK! I have marked the following task as done:",
                taskStore.get(i - 1).toString()}, false);
    }

    public void delete(int i) {
        Task deletedTask = taskStore.remove(i - 1);
        StringUtils.printWithWrapper(new String[]{
                "OK! I have deleted the following task for your list:",
                deletedTask.toString(),
                getListStatus()}, false);
    }

    public void list() {
        StringUtils.printWithWrapper(this.taskStore.toArray(new Task[]{}), true);
    }

    private String getListStatus() {
        int storeSize = taskStore.size();
        return "There " + (storeSize > 1 ? "are " : "is ") + "now " + storeSize + " " +
                (storeSize > 1 ? "tasks " : "task ") + "in your list!";
    }
}
