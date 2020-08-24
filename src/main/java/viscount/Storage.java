package viscount;

import viscount.exception.ViscountIOException;
import viscount.task.*;

import java.io.File;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static final String DATA_FILE_NAME = "viscount.txt";
    
    private String dataDirectoryPath;
    private String filePathString;
    
    public Storage(String dataDirectoryPath) {
        this.dataDirectoryPath = dataDirectoryPath;
        this.filePathString = dataDirectoryPath + DATA_FILE_NAME;
    }

    public void saveToDisk(List<Task> tasks) throws ViscountIOException {
        Path filePath = Paths.get(filePathString);
        List<String> savedData = new ArrayList<>();

        for (Task task : tasks) {
            savedData.add(task.toTaskData());
        }
        
        try {
            Files.write(filePath, savedData, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new ViscountIOException("saving");
        }
    }

    public List<Task> loadFromDisk() throws ViscountIOException {
        File directory = new File(dataDirectoryPath);
        
        if (!directory.exists()) {
            directory.mkdir();
        }
        
        Path filePath = Paths.get(filePathString);
        boolean doesFileExist = Files.exists(filePath);
        List<Task> tasks = new ArrayList<>();

        if (doesFileExist) {
            try {
                File f = new File(filePathString);
                Scanner sc = new Scanner(f);

                while (sc.hasNext()) {
                    String line = sc.nextLine();
                    if (line.isEmpty()) {
                        // If the data file has empty lines, skip them
                        continue;
                    } else {
                        List<String> taskData = Arrays.asList(line.split("\\|"));

                        TaskType taskType = TaskType.valueOf(taskData.get(0));
                        boolean isDone = !taskData.get(1).equals("0");
                        String taskDescription = taskData.get(2);

                        if (taskType == TaskType.TODO) {
                            tasks.add(new Todo(taskDescription, isDone));
                        } else if (taskType == TaskType.DEADLINE) {
                            LocalDateTime dueDate = Parser.parseDateTime(
                                    taskData.get(3), Parser.TASK_DATA_DATE_TIME_FORMATTER);
                            tasks.add(new Deadline(taskDescription, isDone, dueDate));
                        } else if (taskType == TaskType.EVENT) {
                            LocalDateTime eventTime = Parser.parseDateTime(
                                    taskData.get(3), Parser.TASK_DATA_DATE_TIME_FORMATTER);
                            tasks.add(new Event(taskDescription, isDone, eventTime));
                        }
                    }
                }
            } catch (Exception e) {
                throw new ViscountIOException("loading");
            }
        } else {
            try {
                Files.write(filePath, new ArrayList<String>(), StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
            } catch (IOException e) {
                throw new ViscountIOException("creating a new file for");
            }
        }
        
        return tasks;
    }
}
