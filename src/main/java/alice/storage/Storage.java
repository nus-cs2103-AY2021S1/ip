package alice.storage;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public List<String> load() throws AliceStorageException {
        boolean fileExists = Files.exists(filePath);
        if (fileExists) {
            return readFile();
        } else {
            // Create data file and directory
            createFile();
            return null;
        }
    }

    private List<String> readFile() throws AliceStorageException {
        try {
            return Files.readAllLines(filePath);
        } catch (IOException ex) {
            throw new AliceStorageException("Save file is corrupted!");
        }
    }

    private void createFile() throws AliceStorageException {
        try {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
        } catch (IOException ex) {
            throw new AliceStorageException("Problem occurred creating file!");
        }
    }

    public void save(List<String> tasks) throws AliceStorageException {
        try {
            BufferedWriter writer = Files.newBufferedWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i));
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            throw new AliceStorageException("File save error!");
        }
    }

    public void saveToLastLine(String taskToAdd) throws AliceStorageException {
        try {
            Files.write(filePath,
                    (taskToAdd + "\n").getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException ex) {
            throw new AliceStorageException("File save error!");
        }
    }
}
