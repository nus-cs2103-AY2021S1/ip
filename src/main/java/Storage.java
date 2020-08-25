import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a storage object that manages the retrieval and storage of data from an external text file.
 */
public class Storage {
    private String dataPathName;
    private String dataFilePath;

    public Storage(String dataPathName, String dataFilePath) {
        this.dataPathName = dataPathName;
        this.dataFilePath = dataFilePath;
    }

    /**
     * Retrieves saved data from the text file at the given location. If data is not found, creates a new text file
     * for future storage.
     *
     * @param ui Ui to display results and errors to the user
     * @return File containing the saved data from previous runs of Duke or a new text file if one is not found
     */
    public File getSavedData(Ui ui) {
        File folder = new File(this.dataPathName);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        File file = new File(this.dataFilePath);

        try {
            file.createNewFile();
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }

        return file;
    }

    /**
     * Saves tasks in the TaskList to the stated external .txt file
     * @param tasks TaskList containing the updated tasks
     * @param ui Ui to display results and errors to the user
     */
    public void saveTasks(TaskList tasks, Ui ui) {
        File file = new File(this.dataFilePath);

        try {
            FileWriter writer = new FileWriter(file);
            writer.write("");
            writer.close();

            for (int i = 0; i < tasks.getSize(); i++) {
                Task task = tasks.getTask(i);
                FileWriter fileWriter = new FileWriter(file, true);
                fileWriter.write(task.getSaveFormat() + "\n");
                fileWriter.close();
            }

        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }
}
