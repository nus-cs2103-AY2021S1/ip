package duke;

import duke.task.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {

    public static void loadTasks(TaskList tasks) throws IOException {
        String currDir = System.getProperty("user.dir");
        Path filePath = Paths.get(currDir, "data", "tasks.csv");
        File file = filePath.toFile();
        if (!file.exists()) {
            return;
        }
        BufferedReader br = Files.newBufferedReader(filePath);
        br.readLine();
        String line = br.readLine();
        while (line != null) {
            String[] taskEntry = line.split(",");
            String type = taskEntry[0];
            String done = taskEntry[1];
            String description = taskEntry[2];
            if (type.equals("todo")) {
                Task task = new Todo(description, done.equals("1"));
                tasks.addTask(task);
            }
            if (type.equals("deadline")) {
                String date = taskEntry[3];
                Task task = new Deadline(description, done.equals("1"), date);
                tasks.addTask(task);
            }
            if (type.equals("event")) {
                String date = taskEntry[3];
                Task task = new Event(description, done.equals("1"), date);
                tasks.addTask(task);
            }
            line = br.readLine();
        }
    }

    public static void saveTasks(TaskList tasks) throws IOException {
        String currDir = System.getProperty("user.dir");
        Path folderPath = Paths.get(currDir, "data");
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }
        Path filePath = Paths.get(currDir, "data", "tasks.csv");
        File file = filePath.toFile();
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Task,Done?,Description,Date");
        for (int i = 0; i < tasks.getSize(); i++) {
            bw.write(tasks.getTask(i).write());
        }
        bw.close();
    }
}
