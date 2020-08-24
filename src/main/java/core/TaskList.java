package core;

import tasks.Task;

import java.util.ArrayList;
import java.util.stream.Stream;

public class TaskList {

    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void add(Task t) {
        tasks.add(t);
    }

    public static Task get(int id) {
        return tasks.get(id);
    }

    public static Task remove(int id) {
        return tasks.remove(id);
    }

    public static int size() {
        return tasks.size();
    }

    public static Stream<Task> stream() {
        return tasks.stream();
    }

}
