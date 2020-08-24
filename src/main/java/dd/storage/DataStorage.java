package dd.storage;

import dd.tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataStorage {

    public File loadData() throws IOException {
        File f = new File("../src/data/duke.txt"); // create a File for the given file path
        if (f.createNewFile()) {
            System.out.println("New data created: " + f.getName());
        } else {
            System.out.println("Data already exists.");
        }
        return f;
    }

    public void convertData(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter("../src/data/duke.txt");
        String input = taskList.get(0).saveString();
        taskList.remove(0);

        while (!taskList.isEmpty()) {
            input = input + "\n" + taskList.get(0).saveString();
            taskList.remove(0);
        }

        fw.write(input);
        fw.close();
    }

    public void writeData(ArrayList<Task> taskList) {
        try {
            convertData(taskList);
            System.out.println("Updated your data!");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
