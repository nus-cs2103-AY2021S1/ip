package dd.storage;

import dd.exception.DukeException;
import dd.tasks.Task;
import dd.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataStorage {

    Ui ui = new Ui();

    public File loadData() throws IOException {
        File f = new File("./src/data/duke.txt"); // create a File for the given file path

        if (f.createNewFile()) {
            ui.dataCreate(f.getName());
        }
        else {
            ui.dataExists();
        }
        return f;
    }

    public void convertData(ArrayList<Task> taskList) throws IOException, RuntimeException {
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

    public void writeData(ArrayList<Task> taskList) throws DukeException {
        try {
            convertData(taskList);
            ui.updateData();
        }
        catch (IOException e) {
            ui.showError("Error writing to file.");
        }
        catch (RuntimeException e) {
            throw new DukeException().noData();
        }
    }
}
