import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Encapsulate operations related to storage of tasks.
 */
public class Storage {
    private final Path filePath;

    private Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Factory method for creating StorageManager.
     * Storage file path is created if missing.
     *
     * @param filePath Path to the storage file.
     * @return StorageManager class.
     */
    public static Storage getStorage(String filePath) {
        Storage sm = new Storage(Path.of(filePath));
        try {
            sm.preparePath();
            return sm;
        } catch (IOException e) {
            System.out.println("Error creating file structure!");
        }
        System.exit(-1);
        return null;
    }

    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(String.valueOf(filePath));
        fw.write(textToAdd);
        fw.close();
    }

    private List<String> readFromFile() throws FileNotFoundException {
        File f = new File(String.valueOf(filePath));
        List<String> taskStringList = new ArrayList<>();
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            taskStringList.add(s.nextLine());
        }
        return taskStringList;
    }

    /**
     * Saves a list of strings into the hard disk.
     *
     * @param list A list of string.
     */
    public void save(List<? extends ITask> list) {
        StringBuilder sb = new StringBuilder();
        List<String> encodedList = TaskEncoder.encodeTaskList(list);
        encodedList.forEach(x -> sb.append(x).append("\n"));
        try {
            writeToFile(sb.toString());
        } catch (IOException e) {
            System.out.println("Unable to save to disk!");
            e.printStackTrace();
        }

    }

    public List<ITask> read() {
        try {
            return TaskDecoder.decodeList(readFromFile());
        } catch (FileNotFoundException e) {
            System.out.println("Error reading from file!");
            e.printStackTrace();
        }
        throw new RuntimeException("Unable to read task list from disk, exiting...");
    }

    private void preparePath() throws IOException {
        if (!Files.exists(filePath.getParent())) {
            Files.createDirectory(filePath.getParent());
            Files.createFile(filePath);
        } else if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
    }

}
