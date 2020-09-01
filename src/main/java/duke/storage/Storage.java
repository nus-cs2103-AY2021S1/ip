package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Task;

/** Represents the storage system that saves the list of tasks. */
public class Storage {

    /** The path to the directory.*/
    private static final String DIRECTORY_PATH = "src/main/data";
    /** The path to the dataFile.*/
    private static final String DATAFILE_PATH = "src/main/data/data.txt";
    /** The file that stores in the list of tasks in the hard disk. */
    private File dataFile;

    /** Constructor. */
    public Storage() {
        try {
            File directory = new File(DIRECTORY_PATH);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            dataFile = new File(DATAFILE_PATH);
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
     * @param tasks The list of tasks to be saved into the hard disk.
     */
    public void saveTaskList(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
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
