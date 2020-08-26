package duke.tasks;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;
    private int activeTasks;

    public TaskList() {
        this.list = new ArrayList<>();
        this.activeTasks = 0;
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
        activeTasks = 0;
        for (Task t : list) {
            if (t.getStatus()) {
                activeTasks++;
            }
        }
    }

    public void addTask(Task task) {
        list.add(task.getStatus() ? list.size() : activeTasks, task);
        activeTasks += task.getStatus() ? 0 : 1;
    }

    public void deleteTask(Task task) {
        list.remove(task);
        activeTasks -= task.getStatus() ? 0 : 1;
    }

    public void moveDoneToLast(Task task) {
        deleteTask(task);
        addTask(task);
    }

    public void markTaskAsDone(Task task) {
        task.setDone();
        activeTasks--;
        moveDoneToLast(task);
    }

    public Task getTaskAtIndex(int index) {
        return list.get(index - 1);
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public void printList(String mode) {
        int i = 1;
        for (Task t : list) {
            switch (mode) {
                case "Undone":
                    if (t.getStatus()) {
                        continue;
                    }
                    break;
            }
            System.out.println("\t" + i + ". " + t);
            i++;
        }
    }

    public void printList(LocalDate date) {
        int i = 1;
        for (Task t : list) {
            if (!t.hasDate() || !t.getDate().equals(date)) {
                continue;
            }
            System.out.println("\t" + i + ". " + t);
            i++;
        }
    }
}
