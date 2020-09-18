package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Data and file manager of Duke
 */
public class Storage {
    static String path;
    File file;
    ArrayList<Task> itemList = new ArrayList<>();


    /**
     * Record a task into the data management file.
     * @param task Task to be recorded.
     * @throws IOException If file fails to be created or accessed
     */
    static void todoToFile(Task task) throws IOException {
        FileWriter appendFile = new FileWriter(path, true);
        System.out.println(task.toStore());
        appendFile.write(task.toStore());
        appendFile.write("\n");
        appendFile.close();
    }


    /**
     * Constructor of the class.
     * Creates a new Storage object.
     * @param path The path to create (or access) the data management file.
     * @throws IOException If file fails to be created or accessed.
     */
    public Storage (String path) throws IOException {
        this.path = path;
        file = new File(path);
        if (! file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * Rewrites the data management file with a new array of tasks.
     * @param list List of tasks to be written.
     * @throws IOException If data management file fails to be created or accessed.
     */

    public void modifyWithList(ArrayList<Task> list) throws IOException {
        FileWriter clearFile = new FileWriter(path);
        clearFile.write("");
        clearFile.close();
        FileWriter appendFile = new FileWriter(path, true);
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            appendFile.write(task.toStore());
            appendFile.write("\n");
        }
        appendFile.close();
    }

    /**
     * Load the content in the file to an ArrayList of tasks.
     * @return An ArrayList of tasks as recorded in the data management file.
     * @throws DukeException If other error occurs.
     * @throws FileNotFoundException If data management file fails to be created or accessed.
     */
    public ArrayList<Task> load() throws DukeException, FileNotFoundException {
        //load data onto itemList
        Scanner fileSc = new Scanner(file);
        itemList = new ArrayList<>();

        while (fileSc.hasNextLine()) {
            Task newItem;
            String taskString = fileSc.nextLine();
            newItem = Parser.parseFileItemToTask(taskString);
            itemList.add(newItem);
        }
        return itemList;
    }


}