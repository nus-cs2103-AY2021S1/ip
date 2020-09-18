package willy.store;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import willy.task.Task;
import willy.task.TaskSymbol;
import willy.task.TodoTask;
import willy.task.DeadlineTask;
import willy.task.EventTask;

/**
 * Stores the tasks recorded by the bot in a hard drive.
 */
public class TaskStore {
    private static final String FILE_PATH = "./storage/tasks.txt";
    private File taskStorage;

    public TaskStore() { }

    /**
     * Create a file if file does not exist
     */
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

    /**
     * Updates the storage file with the array list of tasks provided.
     *
     * @param listOfTasks Tasks that user input for the bot to keep track of.
     */
    public void updateStorage(ArrayList<Task> listOfTasks) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (int i = 0; i < listOfTasks.size(); i++) {
                Task task = listOfTasks.get(i);
                String combinedTask = "";
                String taskType = listOfTasks.get(i).getTaskType().toString();

                if (taskType.equals("[T]")) {
                    String taskStatus = task.getStatusInStore();
                    String taskContent = task.getTask();
                    combinedTask = taskType + "|" + taskStatus + "|" + taskContent;

                } else if (taskType.equals("[D]")) {
                    DeadlineTask deadlineTask = (DeadlineTask) listOfTasks.get(i);
                    String taskDeadline = deadlineTask.getStringDeadline();
                    String taskStatus = deadlineTask.getStatusInStore();
                    String taskContent = deadlineTask.getTask();
                    combinedTask = taskType + "|" + taskStatus + "|" +
                            taskContent + "|" + taskDeadline;

                } else if (taskType.equals("[E]")) {
                    EventTask eventTask = (EventTask) listOfTasks.get(i);
                    String taskPeriod = eventTask.getStringPeriod();
                    String taskStatus = eventTask.getStatusInStore();
                    String taskContent = eventTask.getTask();
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

    /**
     * Retrieves the list of tasks kept in the storage file.
     *
     * @return List of Tasks the user requested the bot to store.
     */
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
                    TodoTask newTask = new TodoTask(activity, TaskSymbol.TODO);
                    if (taskStatus.contains("O")) { //done
                        newTask.setTaskDone(true);
                    }
                    listOfTasks.add(newTask);

                } else if (taskType.contains("D")) {
                    String deadline = taskComponents[3];
                    DeadlineTask newTask = new DeadlineTask(deadline, activity, TaskSymbol.DEADLINE);
                    if (taskStatus.contains("O")) { //done
                        newTask.setTaskDone(true);
                    }
                    listOfTasks.add(newTask);

                } else if (taskType.contains("E")) {
                    String duration = taskComponents[3];
                    EventTask newTask = new EventTask(duration, activity, TaskSymbol.EVENT);
                    if (taskStatus.contains("O")) { //done
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

    /**
     * To clear the data in the storage file.
     */
    public void clearFile() { // For test purposes
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
