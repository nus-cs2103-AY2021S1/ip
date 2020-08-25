package main.java;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;


public class TaskStore {
    private final Path filePath;
    private static final String FILE_PATH = "./storage/tasks.txt";
    private File taskStorage;

    public TaskStore() {
        filePath = Paths.get(FILE_PATH);
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

//    public void writeTaskToFile(String taskToAdd) {
//        try {
//            FileWriter fw = new FileWriter(FILE_PATH);
//            fw.write(taskToAdd);
//            fw.close();
//        } catch (IOException e){
//            System.out.println(e.getMessage());
//        }
//    }

//    public void updateTaskInFile(String taskToAdd) {
//        try {
//            FileWriter fw = new FileWriter(FILE_PATH);
//            fw.write(taskToAdd + "\n") ;
//            fw.close();
//        } catch (IOException e){
//            System.out.println(e.getMessage());
//        }
//    }

//    // Loads Task in 'T | 1 | read book' format
//    public void loadTasksFromStorage() throws FileNotFoundException {
//        Scanner s = new Scanner(taskStorage); // create a Scanner using the File as the source
//        ArrayList<Task> listOfTasks = new ArrayList<>();
//        while (s.hasNext()) {
//            String line = s.nextLine();
//            String[] taskContents = line.split("\\|");
//            String taskType = taskContents[0];
//            String taskStatus = taskContents[1];
//            String taskName = taskContents[2];
//            String taskDate = taskContents[3];
//
//
//
//        }
//    }

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
                    String taskDeadline = deadlineTask.deadline;
                    String taskStatus = deadlineTask.getStatusIcon();
                    String taskContent = deadlineTask.task;
                    combinedTask = taskType + "|" + taskStatus + "|" + taskContent + "|" + taskDeadline;
                } else if (taskType.equals("[E]")) {
                    EventsTask eventsTask = (EventsTask) listOfTasks.get(i);
                    String taskPeriod = eventsTask.period;
                    String taskStatus = eventsTask.getStatusIcon();
                    String taskContent = eventsTask.task;
                    combinedTask = taskType + "|" + taskStatus + "|" + taskContent + "|" + taskPeriod;
                }
                fw.write(combinedTask + "\n") ;
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }



}
