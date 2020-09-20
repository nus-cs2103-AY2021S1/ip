package duke.task;

import java.util.ArrayList;

/**
 * Class to hold Tasks added by user.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Creates a new TaskList.
     */
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

    public String list() {
        String listOfItemsInString = "";
        int counter = 1;
        for (Task item : taskList) {
            String currLine = counter + ". " + item;
            listOfItemsInString += currLine + "\n";
            counter++;
        }
        return listOfItemsInString;
    }

    public Task removeTask(int index) {
        return this.taskList.remove(index);
    }

}
