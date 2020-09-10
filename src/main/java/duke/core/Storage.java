package duke.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * An object used to load and save the user's task list during startup and termination of the Duke programme.
 */
public class Storage {

    private File saveData;
    private String filePath;
    private String dirPath;

    /**
     * Public constructor to create a Storage object.
     * @param filePath the path to the saveData.txt file that stores the user's past task list
     * @param dirPath the path to the folder where the saveData.txt is found
     */
    public Storage(String filePath, String dirPath) {
        this.filePath = filePath;
        this.dirPath = dirPath;
    }

    /**
     * Loads old task list data if it exists, and creates the necessary directories and file if it does not exist.
     * The path and name of the save data file to find or create depends on the file path and directory path
     * specified in the construction of this Storage object.
     * If any line in the save data is in an incorrect format
     * the data will not be loaded and an error message will be displayed.
     * @return an ArrayList of Task objects that is used
     */
    public ArrayList<Task> loadData() {
        ArrayList<Task> tasks = new ArrayList<>();
        saveData = new File(filePath);

        boolean dirExists = new File(dirPath).exists();

        if (!dirExists) {
            new File(dirPath).mkdirs();
        }

        // checking if the save file is already there
        try {
            boolean fileCreated = saveData.createNewFile();
            if (fileCreated) {
                System.out.println("Haven't seen a new face around 'ere for awhile, have a seat!");
            } else {
                System.out.println("A regular! The usual, I presume?\n"
                        + "I've still got your order history, care to take a look?");
                Scanner saveReader = new Scanner(saveData);
                while (saveReader.hasNextLine()) {
                    try {
                        String saveEntry = saveReader.nextLine();
                        readTask(saveEntry, tasks);
                    } catch (DateTimeParseException e) {
                        System.out.println("Looks like some of your records got messed up! Sorry 'bout that!");
                    }
                }
            }
        } catch (IOException exception) {
            System.out.println(exception);
        }
        return tasks;
    }

    private void readTask(String saveEntry, ArrayList<Task> tasks) throws DateTimeParseException {
        String[] keywords = saveEntry.split(":");
        Task savedTask = null;
        switch (keywords[0]) {
        case "T":
            savedTask = new Todo(keywords[2]);
            break;
        case "D":
            savedTask = new Deadline(keywords[2], LocalDate.parse(keywords[3]));
            break;
        case "E":
            savedTask = new Event(keywords[2], LocalDate.parse(keywords[3]));
            break;
        default:
            break;
        }
        if (savedTask != null) {
            if (keywords[1].equals("y")) {
                savedTask.markAsDone();
            }
            tasks.add(savedTask);
        }
    }

    /**
     * Saves the current task list data onto a text file in the path specified during the construction
     * of the Storage object.
     * @param taskList an ArrayList of Task objects that become saved as text in a text file
     * @see Task
     */
    public void saveData(ArrayList<Task> taskList) {
        BufferedWriter saveWriter = null;
        try {
            saveWriter = new BufferedWriter(new FileWriter(saveData));
            StringBuffer saveContentBuffer = new StringBuffer();
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                saveContentBuffer.append(task.createSaveDataLine() + "\n");
            }

            String saveContent = saveContentBuffer.toString();

            saveWriter.write(saveContent);
        } catch (IOException exception) {
            System.out.println(exception);
        } finally {
            try {
                if (saveWriter != null) {
                    saveWriter.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
