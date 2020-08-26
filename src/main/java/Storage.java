import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final String SAVE_FILE_PATH =
            System.getProperty("user.home") + File.separator + ".duke" + File.separator + "tasks.txt";

    // TODO: 26/8/20 Add more relevant error for parsing
    public static List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        try (var br = new BufferedReader(new FileReader(SAVE_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task task = TaskParser.parse(line);
                tasks.add(task);
            }
        }
        return tasks;
    }

    // TODO: 26/8/20 consider a more robust check
    public static boolean hasSavedTasks() {
        return new File(SAVE_FILE_PATH).exists();
    }

    public static void saveTasks(List<Task> tasks) throws IOException {
        File saveFile = new File(SAVE_FILE_PATH);
        if (!saveFile.exists()) {
            boolean directoryCreated = saveFile.getParentFile().mkdirs();
            if (!directoryCreated) {
                throw new IOException("Unable to create parent directories to save file");
            }
            boolean saveFileCreated = saveFile.createNewFile();
            if (!saveFileCreated) {
                throw new IOException("Unable to create save file");
            }
        }

        // Use PrintWriter wrapping BufferedWriter in FileWriter
        try (var out = new PrintWriter(new BufferedWriter(new FileWriter(SAVE_FILE_PATH)))) {
            for (Task task : tasks) {
                out.println(task.toSaveString());
            }
        }
    }

}
