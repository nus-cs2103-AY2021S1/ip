package duke;

import task.Task;
import task.TaskType;
import task.Todo;
import task.Deadline;
import task.Event;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final static String FILE_PATH = "list.txt";

    private static String convertToText(TaskType type, int done, String name, String time) {
        String t = type == TaskType.DEADLINE ? "D"
                : type == TaskType.EVENT ? "E" : "T";
        return t + "," + done + "," + name
                + (time.isEmpty() ? "" : "," + time) + "\n";
    }

    private static Task convertFromText(String text) throws Exception {
        try {
            String[] ans = text.split(",");
            if (ans[0].equals("T")) {
                Todo todo = new Todo(ans[2]);
                if (ans[1].equals("1")) {
                    todo.setDone();
                }
                return todo;
            } else if (ans[0].equals("D")) {
                Deadline deadline = new Deadline(ans[2], ans[3]);
                if (ans[1].equals("1")) {
                    deadline.setDone();
                }
                return deadline;
            } else {
                Event event = new Event(ans[2], ans[3]);
                if (ans[1].equals("1")) {
                    event.setDone();
                }
                return event;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public static void createListFile() throws IOException {
        Files.createFile(Paths.get(FILE_PATH));
    }

    public static void addToList(TaskType type, int done, String name, String time) throws IOException {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                createListFile();
            }
            FileWriter fw = new FileWriter(FILE_PATH, true);
            fw.write(convertToText(type, done, name, time));
            fw.close();
        } catch (IOException e) {
            throw new IOException("File access failed");
        }
    }

    public static List<Task> readList() throws IOException {
        try {
            File file = new File(FILE_PATH);
            List<Task> list = new ArrayList<>();
            if (!file.exists()) {
                createListFile();
                return list;
            } else {
                Scanner s = new Scanner(file);
                while (s.hasNext()) {
                    Task task = convertFromText(s.nextLine());
                    list.add(task);
                }
                s.close();
                return list;
            }
        } catch (Exception e) {
            throw new IOException("File access failed");
        }
    }

    public static void setDone(int index) throws IOException {
        try {
            File file = new File(FILE_PATH);
            Scanner s = new Scanner(file);
            StringBuilder content = new StringBuilder();
            int count = 0;
            while (s.hasNext()) {
                String text = s.nextLine();
                if (count == index) {
                    text = text.replaceFirst("0", "1");
                }
                content.append(text).append(System.lineSeparator());
                count++;
            }
            FileWriter fw = new FileWriter(FILE_PATH);
            fw.write(content.toString());
            fw.close();
        } catch (IOException e) {
            throw new IOException("File access failed");
        }
    }

    public static void delete(int index) throws IOException {
        try {
            File file = new File(FILE_PATH);
            Scanner s = new Scanner(file);
            StringBuilder content = new StringBuilder();
            int count = 0;
            while (s.hasNext()) {
                String text = s.nextLine();
                if (count != index) {
                    content.append(text).append(System.lineSeparator());
                }
                count++;
            }
            FileWriter fw = new FileWriter(FILE_PATH);
            fw.write(content.toString());
            fw.close();
        } catch (IOException e) {
            throw new IOException("File access failed");
        }
    }

}
