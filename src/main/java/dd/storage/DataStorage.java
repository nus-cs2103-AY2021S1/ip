package dd.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import dd.exception.DukeException;
import dd.tasks.Task;
import dd.ui.Ui;

/**
 * A data storage loads the data from an existing file, if any,
 * into the system when it starts and saves the data
 * into the file when the system exits.
 */
public class DataStorage {

    private Ui ui = new Ui();
    private String loadResults;

    /**
     * Returns file created or opened to read and save data.
     *
     * @return File created or opened.
     * @throws IOException When file is not successfully created.
     */
    public File loadData() throws IOException {
        // create a File for the given file path
        File f = new File("data/duke.txt");
        f.getParentFile().mkdirs();

        if (f.createNewFile()) {
            loadResults = ui.dataCreate(f.getName());
        } else {
            loadResults = ui.dataExists();
        }
        return f;
    }

    /**
     * Indicates whether data was loaded successfully.
     *
     * @return String that indicates whether loading the file was successful.
     */
    public String getLoadResults() {
        return loadResults;
    }

    /**
     * Parse data to file at exit.
     *
     * @param taskList TaskList to be converted and written to file.
     * @throws IOException when it is unable to be written to file.
     * @throws RuntimeException when taskList is empty.
     */
    private void convertData(ArrayList<Task> taskList) throws IOException, RuntimeException {
        FileWriter fw = new FileWriter("data/duke.txt");
        String input = taskList.get(0).saveString();
        taskList.remove(0);

        while (!taskList.isEmpty()) {
            input = input + "\n" + taskList.get(0).saveString();
            int initialNum = taskList.size();
            taskList.remove(0);

            assert initialNum - taskList.size() == 1 : "error iterating through task list";
        }

        fw.write(input);
        fw.close();
    }

    /**
     * Writes data to file based on ArrayList of tasks given.
     *
     * @param taskList ArrayList of tasks to be written to file.
     * @return String to show result of writing data to file.
     * @throws DukeException If no tasks are in taskList,
     * and no tasks are to be written to file.
     */
    public String writeData(ArrayList<Task> taskList) throws DukeException {
        try {
            convertData(taskList);
            return ui.updateData();
        } catch (IOException e) {
            return ui.showError("Error writing to file.");
        } catch (RuntimeException e) {
            throw new DukeException().noData();
        }
    }
}
