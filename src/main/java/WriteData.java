package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WriteData {

    public static ArrayList<Task> getPreviousTask(ArrayList<Task> taskList) {
        java.nio.file.Path path = java.nio.file.Paths.get("data").resolve("duke.txt");
        try {
            File file = new File(path.toString());
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] task = sc.nextLine().split("\\|");
                switch (task[0].trim()) {
                case "T":
                    ToDos todo = new ToDos(task[2].trim());
                    if (task[1].equals("1")) {
                        todo.doneTask();
                    }
                    taskList.add(todo);
                    break;
                case "D":
                    Deadlines deadline = new Deadlines(task[2].trim(), task[3].trim());
                    if (task[1].equals("1")) {
                        deadline.doneTask();
                    }
                    taskList.add(deadline);
                    break;
                case "E":
                    Events event = new Events(task[2].trim(), task[3].trim());
                    if (task[1].equals("1")) {
                        event.doneTask();
                    }
                    taskList.add(event);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            File file = new File(path.toString());
        }
        return taskList;
    }

    public static void writeToFile(ArrayList<Task> taskList) {
        java.nio.file.Path path = java.nio.file.Paths.get("data").resolve("duke.txt");
        try {
            String content = "";
            FileWriter fw = new FileWriter(path.toString());
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                if (task instanceof ToDos) {
                    String taskDetails = String.format("T | %d | %s", task.isDone ? 1 : 0, task.getDescription());
                    content += taskDetails + "\n";
                } else if (task instanceof Deadlines) {
                    String taskDetails = String.format("T | %d | %s |%s",
                            task.isDone ? 1 : 0, task.getDescription(), ((Deadlines) task).getBy());
                    content += taskDetails + "\n";
                } else {
                    String taskDetails = String.format("T | %d | %s |%s",
                            task.isDone ? 1 : 0, task.getDescription(), ((Events) task).getAt());
                    content += taskDetails + "\n";
                }
            }
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
