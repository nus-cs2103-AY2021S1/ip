package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private File file;

    public Storage(String filePath) {
        try {
            Path directory = Paths.get(filePath).getParent();
            if (!Files.exists(directory)) {
                Files.createDirectory(directory);
            }
            file = new File(filePath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] task = sc.nextLine().split("\\|");
                switch (task[0].trim()) {
                case "T":
                    ToDo todo = new ToDo(task[2].trim());
                    if (task[1].equals("1")) {
                        todo.doneTask();
                    }
                    taskList.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(task[2].trim(), task[3].trim());
                    if (task[1].equals("1")) {
                        deadline.doneTask();
                    }
                    taskList.add(deadline);
                    break;
                case "E":
                    Event event = new Event(task[2].trim(), task[3].trim());
                    if (task[1].equals("1")) {
                        event.doneTask();
                    }
                    taskList.add(event);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return taskList;
    }

    public void save(ArrayList<Task> taskList) {
        try {
            String content = "";
            FileWriter fw = new FileWriter(file.getPath());
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                if (task instanceof ToDo) {
                    String taskDetails = String.format("T | %d | %s", task.isDone ? 1 : 0, task.getDescription());
                    content += taskDetails + "\n";
                } else if (task instanceof Deadline) {
                    String taskDetails = String.format("T | %d | %s |%s",
                            task.isDone ? 1 : 0, task.getDescription(), ((Deadline) task).getDate());
                    content += taskDetails + "\n";
                } else {
                    String taskDetails = String.format("T | %d | %s |%s",
                            task.isDone ? 1 : 0, task.getDescription(), ((Event) task).getAt());
                    content += taskDetails + "\n";
                }
            }
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
