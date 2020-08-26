package main.java;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

/**
 * Stores the tasks recorded by the bot in a hard drive.
 */
public class TaskStore {
    private Path filePath;
    private static final String FILE_PATH = "./storage/tasks.txt";
    private File taskStorage;

    public TaskStore() {
            this.filePath = Paths.get(FILE_PATH);
    }

    public void createFile() {
        try {
            taskStorage = new File(FILE_PATH);
            if (taskStorage.exists()) {
            } else {
                // Make sure that parent directories exist
                taskStorage.getParentFile().mkdirs();
                taskStorage.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating storage file");
        }
    }

    public static void updateStorage(ArrayList<Task> listOfTasks) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (int i = 0; i < listOfTasks.size(); i++) {
                Task task = listOfTasks.get(i);
                String combinedTask = "";
                String taskType = listOfTasks.get(i).taskType.toString();

                if (taskType.equals("[T]")) {
                    String taskStatus = task.getStatusIcon();
                    String taskContent = task.task;
                    combinedTask = taskType + "|" + taskStatus + "|" + taskContent;

                } else if (taskType.equals("[D]")) {
                    DeadlineTask deadlineTask = (DeadlineTask) listOfTasks.get(i);
                    String taskDeadline = deadlineTask.deadline.toString();
                    String taskStatus = deadlineTask.getStatusIcon();
                    String taskContent = deadlineTask.task;
                    combinedTask = taskType + "|" + taskStatus + "|" +
                            taskContent + "|" + taskDeadline;

                } else if (taskType.equals("[E]")) {
                    EventsTask eventsTask = (EventsTask) listOfTasks.get(i);
                    String taskPeriod = eventsTask.period.toString();
                    String taskStatus = eventsTask.getStatusIcon();
                    String taskContent = eventsTask.task;
                    combinedTask = taskType + "|" + taskStatus + "|" +
                            taskContent + "|" + taskPeriod;

                }
                fw.write(combinedTask + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
