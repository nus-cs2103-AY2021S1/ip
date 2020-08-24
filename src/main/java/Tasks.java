package main.java;

import java.io.File;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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

    public static void clearAll() {
        database.clear();
    }

    public static void readFile() {
        File dir = new File("./tmp/data");
        dir.mkdirs();
        File f = new File(dir, "storage.txt");
        try {
            f.createNewFile();
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] tokens = s.nextLine().split("%%%");
                List<String> tokenss = new ArrayList<>(Arrays.asList(tokens));
                //tokenss.add(2, "null");
                addTask(tokenss);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void writeFile() {
        File dir = new File("./tmp/data");
        try {
            FileWriter fw = new FileWriter(new File(dir, "storage.txt"));
            for (int i = 0; i < database.size(); i++) {
                Task task = database.get(i);
                fw.write(task.serialize() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    public static void printTasks() {
        for (int i = 0; i < database.size(); i++) {
            System.out.println((i + 1) + "." + database.get(i));
        }
    }
}
