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

    /**
     * Add task given by user into list the bot use to keep track of the task.
     *
     * @param task Task that the user wants the bot to keep track of.
     */
    public void addToList(Task task) {
        listOfTasks.add(task);
        storage.updateStorage(listOfTasks);
        System.out.println(Willy.getStyle() +
                "\tAy here is the task you just added:\n" +
                "\t  " + task + "\n" +
                "\tNow you have " + listOfTasks.size() +
                " task(s) ah dun forget\n" +
                Willy.getStyle());
    }

    /**
     * Add task given by user into list the bot use to keep track of the task. (For GUI)
     *
     * @param task Task that the user wants the bot to keep track of.
     */
    public String javaFXAddToList(Task task) {
        listOfTasks.add(task);
        storage.updateStorage(listOfTasks);
        return Willy.getStyle() +
                "\tAy here is the task you just added:\n" +
                "\t  " + task + "\n" +
                "\tNow you have " + listOfTasks.size() +
                " task(s) ah dun forget\n" +
                Willy.getStyle();
    }

    /**
     * Remove task instructed by user from the list that bot use to keep track of the task.
     *
     * @param taskNum Task number that the user wants the bot to remove.
     */
    public void removeTask(int taskNum) {
        int i = taskNum - 1;
        Task task = listOfTasks.get(i);
        listOfTasks.remove(i);
        storage.updateStorage(listOfTasks);
        System.out.println(Willy.getStyle() +
                "\tOkai here is the task you just deleted:\n" +
                "\t  " + task + "\n" +
                "\tNow you have " + listOfTasks.size() +
                " task(s) left ~\n" +
                Willy.getStyle());
    }

    /**
     * Remove task instructed by user from the list that bot use to keep track of the task. (For GUI)
     *
     * @param taskNum Task number that the user wants the bot to remove.
     */
    public String javaFXRemoveTask(int taskNum) {
        int i = taskNum - 1;
        Task task = listOfTasks.get(i);
        listOfTasks.remove(i);
        storage.updateStorage(listOfTasks);
        return Willy.getStyle() +
                "\tOkai here is the task you just deleted:\n" +
                "\t  " + task + "\n" +
                "\tNow you have " + listOfTasks.size() +
                " task(s) left ~\n" +
                Willy.getStyle();
    }

    /**
     * Print through all the tasks in the list.
     */
    public void readList() {
        System.out.println(Willy.getStyle());
        System.out.print("\tHere are the tasks in your list to jolt ur memory:>\n");
        for (int i = 0; i < listOfTasks.size(); i++) {
            Task task = listOfTasks.get(i);
            System.out.println("\t" + (i + 1) + ". " + task);
        }
        System.out.println(Willy.getStyle());
    }

    /**
     * Prints out all the tasks in the list. (For GUI)
     *
     * @return All the tasks in the list.
     */
    public String javaKFReadList() {
        String list = Willy.getStyle() +
                "\tHere are the tasks in your list to jolt ur memory:>\n";
        for (int i = 0; i < listOfTasks.size(); i++) {
            Task task = listOfTasks.get(i);
            list = list + "\t" + (i + 1) + ". " + task + "\n";
        }
        list = list + Willy.getStyle();
        return list;
    }

    /**
     * Update task in the list that is done by the user.
     *
     * @param taskNum Index of the task according to the list shown to the user.
     */
    public void setTaskDone(int taskNum) {
        int i = taskNum - 1;
        Task task = listOfTasks.get(i);
        task.setTaskDone(true);
        storage.updateStorage(listOfTasks);
        System.out.println(Willy.getStyle());
        System.out.println("\tNiceee I've marked this task as done!");
        System.out.println("\t   " + task);
        System.out.println(Willy.getStyle());
    }

    /**
     * Update task in the list that is done by the user. (For GUI)
     *
     * @param taskNum Index of the task according to the list shown to the user.
     */
    public String javaFXSetTaskDone(int taskNum) {
        int i = taskNum - 1;
        Task task = listOfTasks.get(i);
        task.setTaskDone(true);
        storage.updateStorage(listOfTasks);
        return Willy.getStyle() + "\n" +
        "\tNiceee I've marked this task as done!" + "\n" +
        "\t   " + task + "\n" +
        Willy.getStyle() + "\n";
    }

    /**
     * Filter out list of tasks that contains the keyword and prints the list of related tasks.
     *
     * @param keyword The word that the user wants to use to find related tasks.
     */
    public void findTask(String keyword) {
        ArrayList<Task> keyList = new ArrayList<>();
        for (int i = 0; i < listOfTasks.size(); i++) {
            Task tempTask = listOfTasks.get(i);
            if (tempTask.getTask().contains(keyword)) {
                keyList.add(tempTask);
            }
        }

        System.out.println(Willy.getStyle());
        System.out.println("\t Here are the matching tasks in your list:");
        for (int i = 0; i < keyList.size(); i++) {
            Task task = keyList.get(i);
            System.out.println("\t  " + (i + 1) + "." + task);
        }
        System.out.println(Willy.getStyle());
    }

    /**
     * Filter out list of tasks that contains the keyword. (For GUI)
     *
     * @param keyword The word that the user wants to use to find related tasks.
     * @return The list of related tasks.
     */
    public String javaFXFindTask(String keyword) {
        ArrayList<Task> keyList = new ArrayList<>();
        for (int i = 0; i < listOfTasks.size(); i++) {
            Task tempTask = listOfTasks.get(i);
            if (tempTask.getTask().contains(keyword)) {
                keyList.add(tempTask);
            }
        }

        String filteredList = Willy.getStyle() + "\n" +
        "\t Here are the matching tasks in your list:" + "\n";
        for (int i = 0; i < keyList.size(); i++) {
            Task task = keyList.get(i);
            filteredList = filteredList + "\t  " + (i + 1) + "." + task + "\n";
        }
        filteredList = filteredList + Willy.getStyle();
        return filteredList;
    }
}
