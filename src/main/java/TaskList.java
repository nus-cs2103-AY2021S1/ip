package main.java;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> thingsOnList = new ArrayList<>();

    protected static int getThingsOnListSize() {
        return thingsOnList.size();
    }

    protected static void addToList(Task task) {
        thingsOnList.add(task);
    }

    protected static void deleteFromList(int x) {
        thingsOnList.remove(x);
    }

    protected static Task getLastTask() {
        return thingsOnList.get(thingsOnList.size() - 1);
    }

    protected static ArrayList<Task> getThingsOnList() {
        return thingsOnList;
    }

    protected static void markDone(int x) {
        thingsOnList.get(x).markAsDone();
    }

    protected static void viewList() {
        if (thingsOnList.size() == 0) {
            System.out.println("    It's getting a little lonely here Chief. No tasks here.");
        } else {
            for (int i = 0; i < thingsOnList.size(); i++) {
                System.out.println("    " + (i + 1) + ". " + thingsOnList.get(i));
            }
        }
    }


}
