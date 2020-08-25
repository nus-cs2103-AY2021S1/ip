import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataStorage {
    private String filePath;
    private Scanner sc;

    public DataStorage(String filePath) throws IOException {
        if (!Files.exists(Paths.get(filePath))) {
            Files.createFile(Paths.get(filePath));
        }
        this.filePath = filePath;
    }

    public List<Task> getTaskList() throws FileNotFoundException, DukeException {
        List<Task> tasks = new ArrayList<>();
        List<String> linesOfFile = new ArrayList<>();
        try {
            linesOfFile = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            System.err.println(e);
        }

        for (String inputLine : linesOfFile) {
            String[] parsedTask = inputLine.split(" \\| ");
            Task newTask = null;
            String taskName = parsedTask[0];

            switch (TaskType.getCommand(taskName)) {
            case TODO:
                if (parsedTask.length < 3) {
                    throw DukeException.badToDo();
                }
                newTask = new ToDo(parsedTask[2]);
                break;
            case DEADLINE:
                if (parsedTask.length < 3) {
                    throw DukeException.badDeadlineTask();
                } else if (parsedTask.length < 4) {
                    throw DukeException.badDeadlineDate();
                }
                newTask = new Deadline(parsedTask[2], parsedTask[3]);
                break;
            case EVENT:
                if (parsedTask.length < 3) {
                    throw DukeException.badEventTask();
                } else if (parsedTask.length < 4) {
                    throw DukeException.badEventDate();
                }
                newTask = new Event(parsedTask[2], parsedTask[3]);
                break;
            }

            if (parsedTask[1].equals("1")) {
                newTask.doTask();
            }

            tasks.add(newTask);
        }

        return tasks;
    }

    public void writeTask(Task task) throws IOException {
        Files.writeString(Paths.get(filePath), "\n" + task.getStorageFormat(), StandardOpenOption.APPEND);
    }
}
