import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    TaskList() {
        this.taskList = new ArrayList<>();
    }

    TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    public void add(Task newTask) {
       this.taskList.add(newTask);

    }
    public Task delete(int index) {
        return this.taskList.remove(index);
    }

    public void showAllItems() {
        ArrayList<Task> currList = this.taskList;
        currList.forEach(item ->
                System.out.println((currList.indexOf(item) + 1) + "." + item));
    }

    public int size() {
        return this.taskList.size();
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }
}
