package main.java;

import java.util.ArrayList;
import java.util.List;

public class Tasks {
    private static List<Task> database = new ArrayList<>();

    public static void addTask(Task task) {
        database.add(task);
    }

    public static Task getTask(int index) {
        if (index <= 0 || index > database.size()) {
            throw new IllegalArgumentException("Invalid argument for the LIST command.");
        }
        return database.get(index - 1);
    }

    public static void iDone(int index) {
        if (index <= 0 || index > database.size()) {
            throw new IllegalArgumentException("Out of range argument for DONE command.");
        }
        database.get(index - 1).markAsDone();
    }

    public static Task iRemove(int index) {
        if (index <= 0 || index > database.size()) {
            throw new IllegalArgumentException("Out of range argument for DELETE command.");
        }
        return database.remove(index - 1);
    }

    public static int count() { return database.size();}

    public static void printTasks() {
        for (int i = 0; i < database.size(); i++) {
            System.out.println((i + 1) + "." + database.get(i));
        }
    }
}
