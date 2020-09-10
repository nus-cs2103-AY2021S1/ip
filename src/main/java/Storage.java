import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    /** Destination path of the duke.txt storage file. */
    protected String filePath;

    /**
     * Constructs new Storage object.
     *
     * @param path Destination path of duke.txt file.
     */
    public Storage(String path) {
        filePath = path;
    }

    /**
     * Creates the data folder and duke.txt storage file if absent.
     *
     * @return Destination path of duke.txt file.
     */
    public static String getFilePath() {
        try {
            boolean hasStorageFile = new File("./data").exists();
            if (!hasStorageFile) {
                new File("./data").mkdir();
                new File("./data/duke.txt").createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong in creating the storage file...");
        }
        return "./data/duke.txt";
    }

    /**
     * Loads the contents from the (saved) duke.txt storage file.
     *
     * @return List of (saved) Tasks.
     */
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        try {
            ReadFile file = new ReadFile(filePath);
            String[] dataArr = file.openFile();
            for (String taskStr : dataArr) {
                tasks.add(Parser.getTask(taskStr));
            }
        } catch (DukeException e) {
            System.out.println(e.print());
        } catch (Exception e) {
            System.out.println("Something went wrong in reading data...");
        }
        return tasks;
    }

    /**
     * Writes updated list of tasks into the duke.txt storage file.
     *
     * @param tasks List of Tasks to be saved.
     */
    public void save(List<Task> tasks) {
        try {
            if (tasks.isEmpty()) {
                WriteFile emptyData = new WriteFile(filePath);
                emptyData.writeToFile("");
            } else {
                WriteFile firstData = new WriteFile(filePath);
                firstData.writeToFile(tasks.get(0).toStorageString());

                if (tasks.size() > 1) {
                    WriteFile appendData = new WriteFile(filePath, true);
                    for (int i = 1; i < tasks.size(); ++i) {
                        appendData.writeToFile(tasks.get(i).toStorageString());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong in writing data...");
        }
    }
}
