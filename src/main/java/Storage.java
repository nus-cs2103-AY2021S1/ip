import java.util.List;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.nio.file.FileAlreadyExistsException;
import java.io.IOException;

public class Storage {
    private final Path filePath;

    private Storage(Path filePath) {
        this.filePath = filePath;
    }

    public static Storage init(String fileName) {
        Path path = Paths.get(fileName);
        Path parentDirPath = path.getParent();

        try {
            if (parentDirPath != null) {
                Files.createDirectories(parentDirPath);
            }

            path = Files.createFile(path);
        } catch (FileAlreadyExistsException e) {
            // Treat file as valid if it already exists
        } catch (IOException e) {
            System.err.println("ERROR: Unable to create file for saving and loading tasks!\n");
            e.printStackTrace();
            System.exit(1);
        }

        return new Storage(path);
    }

    public void save(List<String> lines) {
        try {
            Files.write(filePath, lines, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println("ERROR: Unable to save tasks to file\n");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public List<String> load() {
        List<String> lines = null;

        try {
            lines = Files.readAllLines(filePath);
        } catch (IOException e) {
            System.err.println("ERROR: Unable to load tasks from file!\n");
            e.printStackTrace();
            System.exit(1);
        }

        return lines;
    }
}
