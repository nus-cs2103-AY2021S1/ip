package willy.store;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import willy.task.Task;
import willy.task.TaskSymbol;
import willy.task.ToDoTask;
import willy.task.DeadlineTask;
import willy.task.EventsTask;

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

    public void updateStorage(ArrayList<Task> listOfTasks) {
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
                    String taskDeadline = deadlineTask.stringDeadline;
                    String taskStatus = deadlineTask.getStatusIcon();
                    String taskContent = deadlineTask.task;
                    combinedTask = taskType + "|" + taskStatus + "|" +
                            taskContent + "|" + taskDeadline;

                } else if (taskType.equals("[E]")) {
                    EventsTask eventsTask = (EventsTask) listOfTasks.get(i);
                    String taskPeriod = eventsTask.stringPeriod;
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

    // Format of File Content: [T]|✘|go home
    public ArrayList<Task> retrieveStorage() {
        try {
            File f = new File(FILE_PATH);
            Scanner s = new Scanner(f);
            ArrayList<Task> listOfTasks = new ArrayList<>();
            while (s.hasNextLine()) {
                String[] taskComponents = s.nextLine().split("\\|");
                String taskType = taskComponents[0];
                String taskStatus = taskComponents[1];
                String activity = taskComponents[2];
                if (taskType.contains("T")) {
                    ToDoTask newTask = new ToDoTask(activity, TaskSymbol.TODO);
                    if (taskStatus.contains("\u2713")) { //done
                        newTask.setTaskDone(true);
                    }
                    listOfTasks.add(newTask);
                } else if (taskType.contains("D")) {
                    String deadline = taskComponents[3];
                    DeadlineTask newTask = new DeadlineTask(deadline, activity, TaskSymbol.DEADLINE);
                    if (taskStatus.contains("\u2713")) { //done
                        newTask.setTaskDone(true);
                    }
                    listOfTasks.add(newTask);
                } else if (taskType.contains("E")) {
                    String duration = taskComponents[3];
                    EventsTask newTask = new EventsTask(duration, activity, TaskSymbol.EVENT);
                    if (taskStatus.contains("\u2713")) { //done
                        newTask.setTaskDone(true);
                    }
                    listOfTasks.add(newTask);
                }
            }
            return listOfTasks;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }


}
