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
    private final static String filePath = "list.txt";

    /**
     * Converts parameters of a task into a string representation used in storage
     * @param type task type
     * @param done whether the task is done (0 for undone, 1 for done)
     * @param name task name
     * @param time task time (empty string if not applicable)
     * @return a string representation
     */
    private static String convertToText(TaskType type, int done, String name, String time) {
        String t = type == TaskType.DEADLINE ? "D"
                : type == TaskType.EVENT ? "E" : "T";
        return t + "," + done + "," + name
                + (time.isEmpty() ? "" : "," + time) + "\n";
    }

    /**
     * Converts from a string representation to a task
     * @param text string representation
     * @return represented task
     * @throws Exception if text is invalid
     */
    private static Task convertFromText(String text) throws Exception {
        try {
            String[] ans = text.split(",");
            if (ans[0].equals("T")) {
                Todo todo = new Todo(ans[2]);
                if (ans[1].equals("1")) {
                    todo.setDone();
                }
                return todo;
            }
            else if (ans[0].equals("D")) {
                Deadline deadline = new Deadline(ans[2], ans[3]);
                if (ans[1].equals("1")) {
                    deadline.setDone();
                }
                return deadline;
            }
            else {
                Event event = new Event(ans[2], ans[3]);
                if (ans[1].equals("1")) {
                    event.setDone();
                }
                return event;
            }
        }
        catch (Exception e) {
            throw e;
        }
    }

    /**
     * Creates a storage file
     * @throws IOException if error occurs in file accessing
     */
    public static void createListFile() throws IOException {
        Files.createFile(Paths.get(filePath));
    }

    /**
     * Adds parameters of a task to the storage file
     * @param type task type
     * @param done whether the task is done (0 for undone, 1 for done)
     * @param name task name
     * @param time task time (empty string if not applicable)
     * @throws IOException if error occurs in file accessing
     */
    public static void addToList(TaskType type, int done, String name, String time) throws IOException {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                createListFile();
            }
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(convertToText(type, done, name, time));
            fw.close();
        }
        catch (IOException e) {
            throw new IOException("File access failed");
        }
    }

    /**
     * Reloads the stored task list
     * @return stored list of task
     * @throws IOException if error occurs in file accessing
     */
    public static List<Task> readList() throws IOException {
        try {
            File file = new File(filePath);
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
        }
        catch (Exception e) {
            throw new IOException("File access failed");
        }
    }

    /**
     * Updates the task of a given index to "done" in the storage file
     * @param index index of task to be updated
     * @throws IOException if error occurs in file accessing
     */
    public static void setDone(int index) throws IOException {
        try{
            File file = new File(filePath);
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
            FileWriter fw = new FileWriter(filePath);
            fw.write(content.toString());
            fw.close();
        }
        catch(IOException e){
            throw new IOException("File access failed");
        }
    }

    /**
     * Deletes the task of a given index in the storage file
     * @param index index of task to be updated
     * @throws IOException if error occurs in file accessing
     */
    public static void delete(int index) throws IOException {
        try{
            File file = new File(filePath);
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
            FileWriter fw = new FileWriter(filePath);
            fw.write(content.toString());
            fw.close();
        }
        catch(IOException e){
            throw new IOException("File access failed");
        }
    }

}
