import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskStorage {
    List<Task> taskList;
    static String taskStoragePath;

    static {
        taskStoragePath = "./tmp/storage.txt";
    }

    private TaskStorage(List<Task> taskList) {
        this.taskList = taskList;
    }

    public static TaskStorage init() throws IOException, DukeException {
        File tempStorage = new File(taskStoragePath);
        if (tempStorage.exists() && tempStorage.isFile()) {
            return TaskStorage.load(tempStorage);
        } else if (tempStorage.isDirectory()) {
            throw new IOException("Save file is a directory, cannot create");
        } else {
            return TaskStorage.create(tempStorage);
        }
    }

    private static TaskStorage create(File file) throws IOException {
        file.getParentFile().mkdirs();
        file.createNewFile();
        return new TaskStorage(new ArrayList<>());
    }

    private static TaskStorage load(File file) throws FileNotFoundException, DukeException {
        FileInputStream fileInputStream = new FileInputStream(file);
        Scanner scanner = new Scanner(fileInputStream);
        List<Task> taskList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Task task = parseLine(line);
            taskList.add(task);
        }
        return new TaskStorage(taskList);
    }

    private static Task parseLine(String line) throws DukeException {
        String[] lineSplit = line.split("/");
        try {
            String taskType = lineSplit[0];
            Boolean isTaskComplete = convertToBoolean(lineSplit[1]);
            String content = lineSplit[2];
            String datetime = lineSplit[3];
            switch (taskType) {
                case "D":
                    return new Deadline(content, datetime, isTaskComplete);
                case "E":
                    return new Event(content, datetime, isTaskComplete);
                case "T":
                    return new Todo(content, isTaskComplete);
                default:
                    throw new DukeException("Error in parsing line, invalid task type.");
            }
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException(exception.getMessage());
        }
    }

    private static boolean convertToBoolean(String value) {
        boolean returnValue = false;
        if ("1".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value) ||
                "true".equalsIgnoreCase(value) || "on".equalsIgnoreCase(value))
            returnValue = true;
        return returnValue;
    }

    public TaskStorage save() throws IOException {
        Object[] objectArray = taskList.stream().map(Task::toSaveString).toArray();
        String[] lineArray = Arrays.copyOf(objectArray, objectArray.length, String[].class);
        String fileString = String.join("\n", lineArray);
        FileWriter fileWriter = new FileWriter(taskStoragePath);
        fileWriter.write(fileString);
        fileWriter.close();
        return this;
    }

    public int getTaskCount() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void addTask(Task task) throws IOException {
        taskList.add(task);
        this.save();
    }

    public Task removeTask(int index) throws IOException {
        Task task = taskList.remove(index);
        this.save();
        return task;
    }
}
