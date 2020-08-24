package duke;

import duke.task.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Loads the tasks.csv file and saves the tasks.csv file upon booting up
 * of bot and shutting down of the bot respectively.
 */
public class Storage {

    /**
     * Searches for tasks.csv file and loads it into the TaskList of bot.
     * If tasks.csv file does not exist, do nothing.
     * @param tasks TaskList object to be updated by existing tasks.csv file.
     * @throws IOException If loading of file fails.
     */
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

    /**
     * Saves tasks in TaskList object of the bot into existing tasks.csv file
     * If tasks.csv does not exist, create one and save the tasks in it.
     * @param tasks TaskList object of the chat bot with tasks to be saved onto
     *              tasks.csv file.
     * @throws IOException If saving of file fails.
     */
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
