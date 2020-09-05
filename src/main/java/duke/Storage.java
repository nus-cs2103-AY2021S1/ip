package duke;

import duke.exceptions.WrongDateFormatException;
import duke.parser.DateParser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the state of the storage file used to store user's Tasks
 */
public class Storage implements Copiable {

    private final  String DIRECTORY_PATH = "data";
    private final  String STORAGE_PATH = "data/duke.txt";

    /* the current state of the storage file */
    private File currentState;

    /**
     * Constructs a Storage by checking if storage file path exists and if not create the appropriate directory and file.
     *
     * @throws IOException if error when creating the storage directory and/or file
     */
    public Storage() throws IOException {
        File storageDirectory = new File(DIRECTORY_PATH);
        if (!storageDirectory.exists()) {
            storageDirectory.mkdir();
        }
        File storagePath = new File(STORAGE_PATH);
        if (!storagePath.exists()) {
            storagePath.createNewFile();
        }
        currentState = new File(STORAGE_PATH);
    }

    private Storage(File currentState) {
        this.currentState = currentState;
    }

    @Override
    public Storage getCopyOf() {
        return new Storage(currentState);
    }

    /**
     * Parse the storage file and convert each line in the file from the storage String to the appropriate Task
     * object.
     *
     * @return a List of Tasks containing all Tasks stored in the storage file
     */
    public List<Task> getTasks() {
        File storageFile = new File(STORAGE_PATH);
        List<Task> tasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(storageFile);
            while (scanner.hasNext()) {
                String storageString = scanner.nextLine();
                Task task = parseStorageString(storageString);
                tasks.add(task);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            System.out.println("Storage file not found");
            return tasks;
        } catch (WrongDateFormatException e) {
            System.out.println("Wrong date formatting in storage file");
            return tasks;
        }
    }

    /**
     * Replace the contents of the storage file with the Tasks in the given List of tasks.
     *
     * @param taskList a List containing Tasks to replace existing Tasks stored in the storage file
     * @return A new Storage object representing the new state of the storage file
     * @throws IOException if problem with reading/writing to storage file
     */
    public void updateTasks(List<Task> taskList) throws IOException {
        FileWriter storageWriter = new FileWriter(STORAGE_PATH, true);
        clearTasks();
        for (Task t : taskList) {
            addTask(storageWriter, t);
        }
        storageWriter.close();
        currentState = new File(STORAGE_PATH);
    }

    /**
     * Replaces the storage file with the currentState of this Storage object
     */
    public void updateStorage() {
        Path from = currentState.toPath();
        Path to = Paths.get(STORAGE_PATH);
        try {
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Could not replace storage file");
        }

    }

    private Task parseStorageString(String storageString) throws WrongDateFormatException {
        String[] taskComponents = storageString.trim().split(" \\| ");

        final String type = taskComponents[0].trim();
        final boolean isCompleted = taskComponents[1].trim().equals("1");
        final String description = taskComponents[2].trim();

        assert(type.equals("T") || type.equals("E") || type.equals("D"));

        if (type.equals("T")) {
            return new Todo(description, isCompleted);
        } else {
            final String date = taskComponents[3].trim();
            return type.equals("E")
                    ? new Event(description, DateParser.parseIso(date), isCompleted)
                    : new Deadline(description, DateParser.parseIso(date), isCompleted);
        }
    }

    private void clearTasks() throws IOException {
        FileWriter storageWriter = new FileWriter(STORAGE_PATH, false);
        storageWriter.write("");
        storageWriter.close();
    }

    private void addTask(FileWriter storageWriter, Task task) throws IOException {
        storageWriter.write(task.getStorageString());
        storageWriter.write('\n');
    }

}
