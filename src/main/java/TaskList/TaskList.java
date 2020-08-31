package main.java.TaskList;

import main.java.TaskList.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> thingsOnList = new ArrayList<>();

    /**
     * Returns size of current list of tasks.
     * @return size of current list of tasks.
     */
    public static int getThingsOnListSize() {
        return thingsOnList.size();
    }

    /**
     * Adds a task to list.
     * @param task
     */
    public static void addToList(Task task) {
        thingsOnList.add(task);
    }

    /**
     * Deletes the xth task from the list.
     * @param x Number of the task to be deleted.
     */
    public static void deleteFromList(int x) {
        thingsOnList.remove(x);
    }

    /**
     * Gets the last task from the list.
     * @return last task on list.
     */
    public static Task getLastTask() {
        return thingsOnList.get(thingsOnList.size() - 1);
    }

    /**
     * Returns an ArrayList of the list.
     * @return ArrayList of tasks on the list.
     */
    public static ArrayList<Task> getThingsOnList() {
        return thingsOnList;
    }

    /**
     * Marks task x as done.
     * @param x the task to be marked as done.
     */
    public static void markDone(int x) {
        thingsOnList.get(x).markAsDone();
    }

    /**
     * Prints the list.
     */
    public static void viewList() {
        if (thingsOnList.size() == 0) {
            System.out.println("    Bark bark. (No tasks right now.)");
        } else {
            for (int i = 0; i < thingsOnList.size(); i++) {
                System.out.println("    " + (i + 1) + ". " + thingsOnList.get(i));
            }
        }
    }


}
