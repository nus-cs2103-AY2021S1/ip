package main.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import main.exception.InvalidOptionException;
import main.task.Deadline;
import main.task.Event;
import main.task.TaskList;
import main.task.Todo;

/**
 * Handles the reading and writing of tasks to disk.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.3
 * @since v0.1
 */
public class Storage {

    /**
     * Writes the list of tasks to disk as a csv file.
     * @param tasks the list of tasks.
     * @throws IOException if there are any issues regarding files.
     */
    public static void write(TaskList tasks) throws IOException {
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
        bw.write("Task Type,Recurrence,Task Time,Done State,Tags,Task Name\n");

        for (int i = 0; i < tasks.size(); i++) {
            bw.write(tasks.get(i).write());
        }

        bw.close();
    }

    /**
     * Reads the list of tasks from the csv file and sets the task list
     * to the tasks from the file.
     * @param tasks the list of tasks.
     * @throws IOException if there are any issues regarding files.
     */
    public static void setTasks(TaskList tasks) throws IOException,
            InvalidOptionException {
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
            String[] task = line.split(",");
            line = br.readLine();

            String taskType = task[0];
            String taskRecurrenceAlias = task[1];
            String taskTime = task[2];
            String taskName = task[5];
            String[] tags = task[4].split(";");
            boolean isTaskDone = task[3].equals("1");

            switch (taskType) {
            case "T":
                tasks.add(new Todo(taskName, isTaskDone, tags));
                break;
            case "D":
                tasks.add(new Deadline(
                        taskName,
                        taskRecurrenceAlias,
                        taskTime,
                        isTaskDone,
                        tags
                    ));
                break;
            case "E":
                tasks.add(new Event(taskName,
                        taskRecurrenceAlias,
                        taskTime,
                        isTaskDone,
                        tags
                    ));
                break;
            default:
                break;
            }
        }
    }
}
