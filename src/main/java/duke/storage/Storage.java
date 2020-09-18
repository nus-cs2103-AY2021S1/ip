package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents the storage capabilities of Duke.
 */
public class Storage {

    private String directoryPath;
    private String fileName;

    /**
     * Creates an instance of a Storage object with the appropriate
     * directory path and file name to use.
     *
     * @param directoryPath directory path containing text file to load.
     * @param fileName name of text file to load.
     */
    public Storage(String directoryPath, String fileName) {
        this.directoryPath = directoryPath;
        this.fileName = fileName;
    }

    /**
     * Tries to load data from the given path.
     * If unable, creates a new file for Duke to use.
     *
     * @param ui Ui object.
     * @return File that Duke will use to read/write data.
     */
    public File loadData(Ui ui) {
        assert ui != null;
        assert directoryPath != null;
        assert fileName != null;
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(directoryPath + "/" + fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            ui.showLoadingError(e.getMessage());
        }
        return file;
    }

    /**
     * Tries to save data to the given path.
     * If unable, displays and error to the user.
     *
     * @param tasks List of tasks.
     * @param ui Ui object.
     */
    public String saveData(TaskList tasks, Ui ui) {
        assert ui != null;
        assert tasks != null;
        assert directoryPath != null;
        assert fileName != null;
        File file = new File(directoryPath + "/" + fileName);
        try {
            resetFileContents(directoryPath + "/" + fileName);
            appendTasksToFile(tasks);
        } catch (IOException e) {
            return ui.showError("Something went wrong while trying to save your data... :/");
        } catch (IndexOutOfBoundsException e) {
            return ui.showError("There's no such element!");
        }
        return "";
    }

    private void appendTasksToFile(TaskList tasks) throws IOException {
        for (int i = 0; i < tasks.getListSize(); i++) {
            Task task = tasks.getTaskAtIndex(i);
            appendToFile(directoryPath + "/" + fileName, task.getSaveFormat() + "\n");
        }
    }

    //Resets the contents of the file at the given file path.
    private void resetFileContents(String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write("");
        fileWriter.close();
    }

    //Appends data to file.
    private void appendToFile(String filePath, String lineToAdd) throws IOException {
        assert lineToAdd.length() > 0;
        FileWriter fileWriter = new FileWriter(filePath, true);
        fileWriter.write(lineToAdd);
        fileWriter.close();
    }
}
