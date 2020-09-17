package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Stores the data in a file path and update the storage
 */


public class Storage {
    public String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the data from the file storage in lists of Strings
     * If the fire storage is not created, creates a new storage file
     *
     * @return inputs List<String> inputs data from the storage.
     */

    public List<String> load() {

        List<String> inputs = new ArrayList<>();

        try {
            File myFile = new File(filePath);
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                inputs.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Creating the file to save");
            try {
                String folderPath = "./data";
                Path path = Paths.get(folderPath);

                Files.createDirectories(path);

                System.out.println("Directory is created!");
            } catch (IOException error) {
                System.err.println("Failed to create directory!" + error.getMessage());
            }
            try {
                File fileCreator = new File(filePath);
                if (fileCreator.createNewFile()) {
                    System.out.println("File created: " + fileCreator.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException ex) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return inputs;
    }

    /**
     * Updates the task list in the storage after changes
     *
     * @param count  int number of tasks in the list
     * @param list List<Task> to be updated
     * @param filePath String the data storage file to be updated
     */

    public static void updateTasks(int count, List<Task> list, String filePath) {
        String data = "";

        for (int i = 1; i < count + 1; i++) {
            Task task = list.get(i - 1);
            String currentTask = i + "." + task + "\n";
            data = data + currentTask;
        }

        try {
            FileEditing.writeToFile(filePath, data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}