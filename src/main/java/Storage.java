package main.java;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static final String folderPath = "data/";
    private final String storagePath;

    public Storage(String fileName) {
        this.storagePath = folderPath + fileName;
    }

    public void writeToFile(String textToAdd) {
        try {
            FileWriter fileWriter = new FileWriter(storagePath);
            fileWriter.write(textToAdd);
            fileWriter.close();
        } catch (IOException err) {
            System.out.println(err);
        }
    }

//    public void addToFile(String textToAdd) throws IOException {
//        FileWriter fileWriter = new FileWriter(storagePath, true);
//        fileWriter.write(textToAdd);
//        fileWriter.close();
//    }

    /**
     * Method to take the list of tasks from the tasklist and then write it to the file
     */
    public void writeTasks(TaskList taskList) {
        List<Task> tasks = taskList.getTaskList();
        StringBuilder data = new StringBuilder();
        for (Task task : tasks) {
            data.append(task.getStorageString());
        }
        writeToFile(data.toString());
    }

    /**
     * Method to read a file
     */
    public List<String> readStorageFile() throws IOException {
        File folder = new File(this.folderPath);
        File file = new File(this.storagePath);
        if (!folder.exists()) {
            folder.mkdirs();
            throw new IOException("Folder data does not exist");
        } else if (folder.exists() && !file.isFile()) {
            file.createNewFile();
            throw new IOException("duke.txt does not exist");
        }
        Scanner sc = new Scanner(file);
        List<String> tasks = new ArrayList<>();
        while (sc.hasNextLine()) {
            String task = sc.nextLine();
            tasks.add(task);
        }
        return tasks;
    }

    /**
     * Method to write from storage file to a task list
     */
    public TaskList formTaskList() throws IOException {
        List<String> taskListInString = this.readStorageFile();
        TaskList taskList = new TaskList();
        for (String taskInString : taskListInString) {
            // parser work
            String[] arr = taskInString.split(" \\| ");
            String command = arr[0];
            String isDone = arr[1];
            String description = arr[2];
            String timeNotProcessed = arr.length == 3 ? "" : arr[3];
            boolean hasTime = !timeNotProcessed.equals("");
            String timeProcessed = timeNotProcessed.replace("T", " ");
            Task task;
            if (command.equals("T")) {
                task = new ToDo(description, isDone.equals("\u2713"));
            } else if (command.equals("E")) {
                task = new Event(description, timeProcessed, hasTime, isDone.equals("\u2713"));
            } else {
                task = new DeadLine(description, timeProcessed, hasTime, isDone.equals("\u2713"));
            }
            taskList.addTask(task);
        }
        return taskList;
    }


}
