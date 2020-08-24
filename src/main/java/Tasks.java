package main.java;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Tasks {
    private static List<Task> database = new ArrayList<>();

    public static Task addTask(List<String> tokens) {
        Task task;
        String datetimeString = tokens.get(2).trim();
        LocalDateTime datetime = LocalDateTime.MIN;
        if (!datetimeString.equals("null")) {
            String[] timeTokens = datetimeString.split(" |/");
            int time = timeTokens.length >= 4 ? Integer.parseInt(timeTokens[3]) : 0;
            datetime = LocalDateTime.of(Integer.parseInt(timeTokens[2]),
                    Integer.parseInt(timeTokens[1]),
                    Integer.parseInt(timeTokens[0]),
                    time / 100, time % 100);
        }
        switch (tokens.get(0)) {
            case "todo":
                task = new Todo(tokens.get(1).trim());
                break;
            case "deadline":
                task = new Deadline(tokens.get(1).trim(), datetime);
                break;
            case "event":
                task = new Event(tokens.get(1).trim(), datetime);
                break;
            default:
                throw new Error("An unexpected error has occurred");
        }
        if (tokens.get(3).equals("1")) task.markAsDone();
        database.add(task);
        return task;
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
