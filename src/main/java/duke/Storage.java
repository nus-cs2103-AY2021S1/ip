package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents Storage class for reading and writing tasks.
 */
public class Storage {

    /** Path of file */
    private final Path path;

    /**
     * Constructs a new instance of storage object.
     * @param filePath Path of file.
     * @throws DukeException When there is an error in creating file.
     */
    public Storage(String filePath) throws DukeException {
        String dir = System.getProperty("user.dir");
        path = Path.of(dir, filePath);
        File file = new File(String.valueOf(path));
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException ex) {
            throw new DukeException("Error in creating duke.txt");
        }
    }

    /**
     * Adds tasks to file.
     * @param tasks Tasks in an arraylist.
     * @throws IOException When writing to file fails.
     */
    public void writeToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(path.toString());
        for (Task task : tasks) {
            String isDone = task.isDone ? "done" : "undone";
            String description = task.getTask();
            Type taskType = task.getType(); //null
            String stringTaskType = taskType.toString();
            switch (taskType) {
            case TODO:
                String writeTodo = String.format("%s | %s | %s\n",
                        stringTaskType, isDone, description);
                fileWriter.write(writeTodo);
                break;
            case DEADLINE:
            case EVENT:
                String dateAndTime = task.getTime();
                String write = String.format("%s | %s | %s | %s\n", stringTaskType, isDone,
                        description, dateAndTime);
                fileWriter.write(write);
                break;
            default:
                System.out.println("Write failed :(");
                break;
            }
        }
        fileWriter.close();
    }

    /**
     * Retrieves all tasks when bot starts running.
     * @return ArrayList of tasks.
     * @throws FileNotFoundException When file with specified pathname does not exist.
     */
    public ArrayList<Task> readFromFile() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = path.toFile();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            String[] splitString = input.split(" \\| ");
            boolean isDone = splitString[1].equals("done");
            switch (splitString[0]) {
            case "ToDo":
                Task todo = new ToDo(splitString[2], isDone);
                tasks.add(todo);
                break;
            case "Deadline":
                Task deadline = new Deadline(splitString[2], splitString[3], isDone);
                tasks.add(deadline);
                break;
            case "Event":
                Task event = new Event(splitString[2], splitString[3], isDone);
                tasks.add(event);
                break;
            default:
                System.out.println("Cannot read file! :(");
                break;
            }
        }
        return tasks;
    }
}
