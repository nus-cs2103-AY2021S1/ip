import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * The storage class is the class responsible for loading and saving the list of tasks into a file
 * It can load tasks from a file that is formatted correctly.
 * And write a list of tasks to a file.
 */
public class Storage {
    private Path savePath;

    /**
     * Creates a storage object with a savePath to load and save the file to
     * @param savePath
     */
    Storage(Path savePath) {
        this.savePath = savePath;
    }

    /**
     * Saves the TaskList to the object path
     * @param taskList
     */
    public void save(TaskList taskList) {
        File saveFile = savePath.toFile();
        try {
            if (!saveFile.exists()) {
                saveFile.getParentFile().mkdirs();
                saveFile.createNewFile();
            }
            FileWriter writer = new FileWriter(saveFile);
            List<Task> tasks = taskList.getTasks();
            for (Task task : tasks) {
                writer.append(task.toSaveFormat() + "\n");
            }
            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't save to file :(");
        }
    }

    /**
     * Loads the Task List from the file savePath.
     * @return a task list with the tasks loaded from the file
     */
    public List<Task> load() {
        List<Task> taskList = new ArrayList<>();
        if (!savePath.toFile().exists()) {
            return taskList;
        }

        try (Stream<String> stream = Files.lines(savePath)) {
            stream.forEach((s -> {
                String[] saveParts = s.split(" \\| ");
                switch (saveParts[0]) {
                    case "T":
                        taskList.add(new Todo(saveParts[2], saveParts[1].equals("1")));
                        break;
                    case "D":
                        taskList.add(new Deadline(saveParts[2], saveParts[3], saveParts[1].equals("1")));
                        break;
                    case "E":
                        taskList.add(new Event(saveParts[2], saveParts[3], saveParts[1].equals("1")));
                        break;
                }
            }));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return taskList;

    }

}
