package duke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return this.taskList;
    }

    public void add(Task newTask) {
        this.taskList.add(newTask);
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public int numTask() {
        return this.taskList.size();
    }

    public void list() {
        int counter = 1;
        for (Task item : taskList) {
            System.out.println(counter + ". " + item);
            counter++;
        }
    }

    public Task removeTask(int index) {
        return this.taskList.remove(index);
    }
}