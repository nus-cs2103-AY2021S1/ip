package willy.task;

import willy.store.TaskStore;
import willy.task.Task;
import willy.ui.Willy;

import java.util.ArrayList;

/**
 * Handles the different actions that can be done by the bot.
 * Bot can add tasks, remove tasks, read list of tasks,
 * update tasks that are done and find tasks.
 */
public class TaskList {
    private ArrayList<Task> listOfTasks;
    private TaskStore storage;

    public TaskList(ArrayList<Task> listOfTasks, TaskStore storage) {
        this.listOfTasks = listOfTasks;
        this.storage = storage;
    }

    public ArrayList<Task> getList() {
        return listOfTasks;
    }

    // Add Tasks to list
    public void addToList(Task task) {
        listOfTasks.add(task);
        storage.updateStorage(listOfTasks);
        System.out.println(Willy.style +
                "\tAy here is the task you just added:\n" +
                "\t  " + task + "\n" +
                "\tNow you have " + listOfTasks.size() +
                " task(s) ah dun forget\n" +
                Willy.style);
    }

    public String javaFXAddToList(Task task) {
        listOfTasks.add(task);
        storage.updateStorage(listOfTasks);
        return Willy.style +
                "\tAy here is the task you just added:\n" +
                "\t  " + task + "\n" +
                "\tNow you have " + listOfTasks.size() +
                " task(s) ah dun forget\n" +
                Willy.style;
    }


    public void removeTask(int taskNum) {
        int i = taskNum - 1;
        Task task = listOfTasks.get(i);
        listOfTasks.remove(i);
        storage.updateStorage(listOfTasks);
        System.out.println(Willy.style +
                "\tOkai here is the task you just deleted:\n" +
                "\t  " + task + "\n" +
                "\tNow you have " + listOfTasks.size() +
                " task(s) left ~\n" +
                Willy.style);
    }

    public String javaFXRemoveTask(int taskNum) {
        int i = taskNum - 1;
        Task task = listOfTasks.get(i);
        listOfTasks.remove(i);
        storage.updateStorage(listOfTasks);
        return Willy.style +
                "\tOkai here is the task you just deleted:\n" +
                "\t  " + task + "\n" +
                "\tNow you have " + listOfTasks.size() +
                " task(s) left ~\n" +
                Willy.style;
    }

    // Reads through all the tasks in the list
    public void readList() {
        System.out.println(Willy.style);
        System.out.print("\tHere are the tasks in your list to jolt ur memory:>\n");
        for (int i = 0; i < listOfTasks.size(); i++) {
            Task task = listOfTasks.get(i);
            System.out.println("\t" + (i + 1) + ". " + task);
        }
        System.out.println(Willy.style);
    }

    public String javaKFReadList() {
        String list = Willy.style +
                "\tHere are the tasks in your list to jolt ur memory:>\n";
        for (int i = 0; i < listOfTasks.size(); i++) {
            Task task = listOfTasks.get(i);
            list = list + "\t" + (i + 1) + ". " + task + "\n";
        }
        list = list + Willy.style;
        return list;
    }

    // Update Tasks to be done
    public void setTaskDone(int taskNum) {
        int i = taskNum - 1;
        Task task = listOfTasks.get(i);
        task.setTaskDone(true);
        storage.updateStorage(listOfTasks);
        System.out.println(Willy.style);
        System.out.println("\tNiceee I've marked this task as done!");
        System.out.println("\t   " + task);
        System.out.println(Willy.style);
    }

    public String javaFXSetTaskDone(int taskNum) {
        int i = taskNum - 1;
        Task task = listOfTasks.get(i);
        task.setTaskDone(true);
        storage.updateStorage(listOfTasks);
        return Willy.style + "\n" +
        "\tNiceee I've marked this task as done!" + "\n" +
        "\t   " + task + "\n" +
        Willy.style + "\n";
    }

    public void findTask(String keyword) {
        ArrayList<Task> keyList = new ArrayList<>();
        for (int i = 0; i < listOfTasks.size(); i++) {
            Task tempTask = listOfTasks.get(i);
            if (tempTask.getTask().contains(keyword)) {
                keyList.add(tempTask);
            }
        }

        System.out.println(Willy.style);
        System.out.println("\t Here are the matching tasks in your list:");
        for (int i = 0; i < keyList.size(); i++) {
            Task task = keyList.get(i);
            System.out.println("\t  " + (i + 1) + "." + task);
        }
        System.out.println(Willy.style);
    }

    public String javaFXFindTask(String keyword) {
        ArrayList<Task> keyList = new ArrayList<>();
        for (int i = 0; i < listOfTasks.size(); i++) {
            Task tempTask = listOfTasks.get(i);
            if (tempTask.getTask().contains(keyword)) {
                keyList.add(tempTask);
            }
        }

        String filteredList = Willy.style + "\n" +
        "\t Here are the matching tasks in your list:" + "\n";
        for (int i = 0; i < keyList.size(); i++) {
            Task task = keyList.get(i);
            filteredList = filteredList + "\t  " + (i + 1) + "." + task + "\n";
        }
        filteredList = filteredList + Willy.style;
        return filteredList;
    }
}
