package tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<Task> scannedTasks = new ArrayList<>();

        while (s.hasNext()) {
            String[] temp = s.nextLine().split(" , ");

            if (temp[0].equals("T")) {
                scannedTasks.add(new Todo(temp[2]));
            }
            else if (temp[0].equals("E")) {
                scannedTasks.add(new Event(temp[2], temp[3]));
            }
            else {
                scannedTasks.add(new Deadline(temp[2], temp[3]));
            }

            if (temp[1].equals("1")) {
                scannedTasks.get(scannedTasks.size() - 1).markAsDone();
            }
        }
        this.taskList = scannedTasks;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public int getTaskSize() {
        return taskList.size();
    }

    public Task getLastTask() {
        return taskList.get(taskList.size()-1);
    }

    public Task getTask(int i) {
        return taskList.get(i);
    }

    public void addTask(Task t) {
        taskList.add(t);
    }

    public void deleteTask(int i) {
        taskList.remove(i);
    }
}
