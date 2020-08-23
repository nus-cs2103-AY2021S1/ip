import java.io.File;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Storage {
    
    private String filePathString;
    
    public Storage(String filePathString) {
        this.filePathString = filePathString;
    }

    public void saveToDisk(List<Task> tasks) {
        Path filePath = Paths.get(filePathString);
        List<String> savedData = new ArrayList<>();

        for (Task task : tasks) {
            savedData.add(task.toTaskData());
        }
        
        try {
            Files.write(filePath, savedData, StandardCharsets.UTF_8);
        } catch (IOException e) {
            // TODO handle
        }
    }

    public List<Task> loadFromDisk() throws IOException {
        Path filePath = Paths.get(filePathString);
        boolean doesFileExist = Files.exists(filePath);
        List<Task> tasks = new ArrayList<>();

        if (doesFileExist) {
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
                    } else {
                        // TODO handle corrupted data
                    }
                }
            }
        } else {
            Files.write(filePath, new ArrayList<String>(), StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
        }
        
        return tasks;
    }
}
