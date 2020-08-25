package duke.storage;

import duke.tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/** Represents the storage system that saves the list of tasks. */
public class Storage {

    /** The file that stores in the list of tasks in the hard disk. */
    File dataFile;

    /** Constructor.
     *
     * @param directoryPath The path of the dataFile's directory.
     * @param dataFilePath The path of the dataFile.
     */
    public Storage(String directoryPath, String dataFilePath) {
        try {
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            dataFile = new File(dataFilePath);
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    /** Generates a list of user inputs from the list saved in the hard disk for the Parser to process.
     *
     * @return A list of user inputs in the list saved in the hard disk.
     */
    public ArrayList<String> readFile() {
        ArrayList<String> lines = new ArrayList<>();
        try {
            Scanner sc = new Scanner(dataFile);
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
            return lines;
    }

    /** Saves the list of tasks into the hard disk.
     *
     * @param lst The list of tasks to be saved into the hard disk.
     */
    public void saveTaskList(ArrayList<Task> lst) {
        StringBuilder sb = new StringBuilder();
        for (Task task : lst) {
            sb.append(task);
            sb.append("\n");
        }
        try {
            writeToFile(sb.toString());
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fileWriter = new FileWriter(dataFile.getPath());
        fileWriter.write(textToAdd);
        fileWriter.close();
    }
}
