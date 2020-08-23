package main.storage;

import main.task.Deadline;
import main.task.Event;
import main.task.TaskList;
import main.task.Todo;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {

    public static void write(TaskList tasks) throws IOException {
        String currDir = System.getProperty("user.dir");
        Path folderPath = Paths.get(currDir, "data");

        if (!Files.exists(folderPath)) Files.createDirectories(folderPath);

        Path filePath = Paths.get(currDir, "data", "tasks.csv");
        File file = filePath.toFile();

        if (!file.exists()) file.createNewFile();

        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Task Type,Task Time,Done State,Task Name\n");

        for (int i = 0; i < tasks.size(); i++) bw.write(tasks.get(i).write());

        bw.close();
    }

    public static void setTasks(TaskList tasks) throws IOException {
        String currDir = System.getProperty("user.dir");
        Path filePath = Paths.get(currDir, "data", "tasks.csv");
        File file = filePath.toFile();

        if (!file.exists()) return;

        BufferedReader br = Files.newBufferedReader(filePath);
        br.readLine();
        String line = br.readLine();

        while (line != null) {
            String[] task = line.split(",");
            line = br.readLine();

            String taskType = task[0];
            String taskTime = task[1];
            String taskName = task[3];
            boolean taskDoneState = task[2].equals("1");

            switch (taskType) {
            case "T":
                tasks.add(new Todo(taskName, taskDoneState));
                break;
            case "D":
                tasks.add(new Deadline(taskName, taskTime, taskDoneState));
                break;
            case "E":
                tasks.add(new Event(taskName, taskTime, taskDoneState));
                break;
            default:
                break;
            }
        }
    }
}
