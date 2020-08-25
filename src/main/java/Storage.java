import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public ArrayList<Task> load() throws AliceException {
        boolean fileExists = Files.exists(filePath);
        if (fileExists) {
            return readFile();
        } else {
            // Create data file and directory
            createFile();
            return new ArrayList<>();
        }
    }

    private ArrayList<Task> readFile() throws AliceException {
        try {
            List<String> allLines = Files.readAllLines(filePath);
            ArrayList<Task> tasks = new ArrayList<>();

            for (int i = 0; i < allLines.size(); i++) {
                String currTask = allLines.get(i);
                String[] typeAndDetails = currTask.split(" \\| ", 2);

                if (typeAndDetails.length != 2) {
                    throw new AliceException("Corrupted save file");
                }

                switch (typeAndDetails[0]) {
                case "T":
                    tasks.add(Todo.decode(typeAndDetails[1]));
                    break;
                case "D":
                    tasks.add(Deadline.decode(typeAndDetails[1]));
                    break;
                case "E":
                    tasks.add(Event.decode(typeAndDetails[1]));
                    break;
                default:
                    throw new AliceException("Corrupted save file");
                }
            }

            return tasks;
        } catch (IOException ex) {
            throw new AliceException("Save file is corrupted!");
        }
    }

    private void createFile() throws AliceException {
        try {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
        } catch (IOException ex) {
            throw new AliceException("Problem occurred creating file!");
        }
    }

    public void save(ArrayList<Task> tasks) throws AliceException {
        try {
            BufferedWriter writer = Files.newBufferedWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i).encode());
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            throw new AliceException("File save error!");
        }
    }

    public void saveToLastLine(Task taskToAdd) throws AliceException {
        try {
            Files.write(filePath,
                    (taskToAdd.encode() + "\n").getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException ex) {
            throw new AliceException("File save error!");
        }
    }
}
