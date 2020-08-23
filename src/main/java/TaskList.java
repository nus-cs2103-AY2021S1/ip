import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> arrayOfTasks;

    public TaskList() {
        this.arrayOfTasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> arrayOfTasks) {
        this.arrayOfTasks = arrayOfTasks;
    }

    public void addTask(Task newTask) {
        arrayOfTasks.add(newTask);
    }

    public void deleteTask(int taskIndex) {
        arrayOfTasks.remove(taskIndex);
        Task.totalTasks--;
    }

    public Task get(int taskIndex) {
        return arrayOfTasks.get(taskIndex);
    }

    public int taskArraySize() {
        return arrayOfTasks.size();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        int size = arrayOfTasks.size();
        int index = 0;
        while (index < size) {
            Task curr = arrayOfTasks.get(index);
            output.append(index + 1);
            output.append(".");
            output.append(curr.toString());
            output.append("\n");
            index++;
        }
        String finalOutput = output.toString().trim();
        return finalOutput;
    }
}