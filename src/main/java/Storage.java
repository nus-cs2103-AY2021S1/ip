package main.java;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    String dataDirectoryPath;
    String savedFilePath;

    public Storage(String dataDirectoryPath, String savedFilePath) {
        this.dataDirectoryPath = dataDirectoryPath;
        this.savedFilePath = savedFilePath;
    }

    public List<Task> loadSavedFile() throws IOException {

        File directory = new File(dataDirectoryPath);
        directory.mkdir(); // create the directory if it not existed

        File savedFile = new File(savedFilePath);
        List<Task> tasks = new ArrayList<>();
        try {
            savedFile.createNewFile();
            Scanner sc = new Scanner(savedFile);

            while (sc.hasNext()) {
                String taskString = sc.nextLine();
                String[] taskComponents = taskString.split(" - ");
                String taskType = taskComponents[0];
                boolean isDone = taskComponents[1].equals("1");
                String description = taskComponents[2];

                Task toAdd;
                switch (taskType) {
                    case "T":
                        toAdd = new ToDo(description, isDone);
                        break;
                    case "D":
                        toAdd = new Deadline(description, LocalDateTime.parse(taskComponents[3], DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")), isDone);
                        break;
                    case "E":
                        toAdd = new Event(description, LocalDateTime.parse(taskComponents[3], DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")), isDone);
                        break;
                    default:
                        throw new IllegalArgumentException("Saved file contains incorrect format");
                }
                tasks.add(toAdd);
            }
        } catch (IOException e) {
            throw e;
        }
        return tasks;
    }

    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(savedFilePath);
        for (Task task : tasks.all()) {
            fw.write(task.simplifiedTaskString() + "\n");
        }
        fw.close();
    }
}
