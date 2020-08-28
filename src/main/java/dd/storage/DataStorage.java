package dd.storage;

import dd.exception.DukeException;
import dd.tasks.Task;
import dd.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A data storage loads the data from an existing file, if any,
 * into the system when it starts and saves the data
 * into the file when the system exits.
 */
public class DataStorage {

    private Ui ui = new Ui();

    /**
     * Returns file created or opened to read and save data.
     *
     * @return File created or opened
     */
    public File loadData() throws IOException {
        File f = new File("./src/data/duke.txt"); // create a File for the given file path

        if (f.createNewFile()) {
            ui.dataCreate(f.getName());
        } else {
            ui.dataExists();
        }
        return f;
    }

    private void convertData(ArrayList<Task> taskList) throws IOException, RuntimeException {
        FileWriter fw = new FileWriter("./src/data/duke.txt");
        String input = taskList.get(0).saveString();
        taskList.remove(0);

        while (!taskList.isEmpty()) {
            input = input + "\n" + taskList.get(0).saveString();
            taskList.remove(0);
        }

        fw.write(input);
        fw.close();
    }

    /**
     * Writes data to file based on ArrayList of tasks given.
     *
     * @param taskList ArrayList of tasks to be written to file.
     * @throws DukeException If no tasks are in taskList,
     * and no tasks are to be written to file.
     */
    public void writeData(ArrayList<Task> taskList) throws DukeException {
        try {
            convertData(taskList);
            ui.updateData();
        } catch (IOException e) {
            ui.showError("Error writing to file.");
        } catch (RuntimeException e) {
            throw new DukeException().noData();
        }
    }
}
