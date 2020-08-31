package main.java.TaskList;

import main.java.TaskList.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> thingsOnList = new ArrayList<>();

    public static int getThingsOnListSize() {
        return thingsOnList.size();
    }

    public static void addToList(Task task) {
        thingsOnList.add(task);
    }

    public static void deleteFromList(int x) {
        thingsOnList.remove(x);
    }

    public static Task getLastTask() {
        return thingsOnList.get(thingsOnList.size() - 1);
    }

    public static ArrayList<Task> getThingsOnList() {
        return thingsOnList;
    }

    public static void markDone(int x) {
        thingsOnList.get(x).markAsDone();
    }

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
