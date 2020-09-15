package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


/**
 * Represents a file location that user's input. Capable of reading from and writing to the file.
 */
@SuppressWarnings("checkstyle:Regexp")
public class Storage {
    private static final String DEFAULT_SAVE_LOCATION = "data/duke.txt";
    private static final DukeException FILE_NOT_FOUND = new DukeException("Can't find the File. Please ensure that:\n"
            + "Filepath is not pointed to a directory,\n"
            + "you have permission to access/create file there and\n"
            + "in this format: dir\\...\\filename.txt\n");

    private final File file;
    private final String filePath;
    /**
     * Points to the default save location.
     */
    public Storage() {
        this.file = new File(DEFAULT_SAVE_LOCATION);
        this.filePath = DEFAULT_SAVE_LOCATION;
    }

    /**
     * Points to the file which filePath is input by User.
     * @param filePath filePath of the file that wish to be load/ write
     */
    public Storage(String filePath) throws DukeException {
        assert !filePath.equals("") : "Empty FilePath";
        File file = new File(filePath);
        if (!isWritableFilePath(filePath)) {
            throw FILE_NOT_FOUND;
        }
        this.file = file;
        this.filePath = filePath;
    }

    private boolean isWritableFilePath(String filePath) {
        return Files.isWritable(Paths.get(filePath));
    }

    private boolean isReadableFilePath(String filePath) {
        return Files.isReadable(Paths.get(filePath));
    }

    /**
     * If there is input in FilePaths, write to File
     * Write the argument into the file destinated during initialisation
     * @param data An array of String to be written to the designated file.
     * @throws DukeException If no permission to create at filePath or filePath is a directory.
     */
    public void saveToFile(String[] data, String... filePaths) throws DukeException {
        try {
            //If not specified, write to assigned filePath during initialisation
            if (filePaths.length == 0) {
                FileWriter fw = new FileWriter(this.file);
                fw.write("");
                for (String s: data) {
                    fw.append(s + "\n");
                }
                fw.close();
                return;
            }

            for (String currentFilePath: filePaths) {
                File currentFile = new File(currentFilePath);
                if (isWritableFilePath(filePath)) {
                    FileWriter fw = new FileWriter(currentFile);
                    fw.write("");
                    for (String s: data) {
                        fw.append(s + "\n");
                    }
                    fw.close();
                }
            }
        } catch (IOException err) {
            throw new DukeException("File Path is a directory -OR- Can't create file at location");
        }
    }

    /** Read and interpret the saved file.
     * @return ArrayList of Task that is saved inside the designated file. If file / directory does not exist, create.
     * @throws DukeException If file is not found.
     */
    public ArrayList<Task> loadDefaultFile() throws DukeException {
        try {
            return loadFile(this.file);
        } catch (FileNotFoundException err) {
            this.createNewFile(this.filePath);
            return new ArrayList<Task>();
        }
    }

    /** Load a file at path specified by the user.
     * @param newFilePath Path of the file that user wish to load.
     * @return ArrayList of Task that is saved inside the designated file. If file / directory does not exist, create.
     * @throws DukeException If file is not found.
     */
    public ArrayList<Task> loadCustomFile(String newFilePath) throws DukeException {
        try {
            if (!isReadableFilePath(newFilePath)) {
                throw FILE_NOT_FOUND;
            }
            ArrayList<Task> result = loadFile(new File(newFilePath));
            return result;
        } catch (FileNotFoundException err) {
            throw FILE_NOT_FOUND;
        }
    }

    private void createNewFile(String filePath) {
        String[] fileDirectory = filePath.split("/");
        String parentDirectory = "";
        for (int i = 0; i < fileDirectory.length - 1; i++) {
            parentDirectory += fileDirectory[i] + "/";
        }
        File f = new File(parentDirectory);
        f.mkdirs();
    }

    private ArrayList<Task> loadFile(File currentFile) throws FileNotFoundException, DukeException {
        ArrayList<Task> loadedTask = new ArrayList<>();
        Scanner scanner = new Scanner(currentFile);
        String[] dataRead;
        while (scanner.hasNext()) {
            dataRead = readSavedLine(scanner.nextLine());
            Task newTask = loadSavedTask(dataRead);
            loadedTask.add(newTask);
        }
        scanner.close();
        return loadedTask;
    }

    /**
     * Split individual saved line into a 3 parts: Command, What, When
     * @param inputLine A line from save file to be interpreted.
     * @return A String array with 2 or 3 parts.
     */
    private String[] readSavedLine(String inputLine) {
        String[] parts = inputLine.split(" \\| ");
        ArrayList<String> result = new ArrayList<>();
        for (String part : parts) {
            result.add(part.trim());
        }
        return result.toArray(parts);
    }

    /**
     * Create a new Task based on the input.
     * @param args The 2 or 3 parts command (Command, What, When).
     * @return Task created based on the command
     * @throws DukeException If the command requires (What, When) parts but one or more parts is missing.
     */
    private Task loadSavedTask(String[] args) throws DukeException {
        Task newTask;
        try {
            switch(args[0]) {
            case "D":
                newTask = new Deadline(args[2], args[3]);
                break;
            case "T":
                newTask = new ToDo(args[2]);
                break;
            case "E":
                newTask = new Event(args[2], args[3]);
                break;
            default:
                throw new DukeException("Error: Saved File is badly corrupted");
            }
        } catch (Exception err) {
            throw new DukeException("Error: Saved File is badly corrupted");
        }

        if (args[1].equals("1")) {
            newTask.setDone();
        }
        return newTask;
    }

}
