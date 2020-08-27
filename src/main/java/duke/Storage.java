package duke;


import duke.tasks.Task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


/**
 * Storage for Duke.
 * Reads and stores list in a database file (.txt).
 */
public class Storage {

    private static final String DELIMITER = ",";

    private final static String projectRoot = System.getProperty("user.dir");

    private final Path path;
    private final File storageFile;
    private boolean pathExists;

    private final ArrayList<String> fileBuffer;


    /**
     * Constructor for Parser.
     *
     * @param pathStrArr path to storage file.
     */
    public Storage(String... pathStrArr) {
        this.path = Paths.get(projectRoot, pathStrArr);
        this.storageFile = new File(this.path.toString());
        this.pathExists = Files.exists(this.path);
        this.fileBuffer = new ArrayList<>();
        System.out.println("File exists?" + this.getFileStatus());
    }


    /**
     * Gets file status string.
     *
     * @return file status string.
     */
    public String getFileStatus() {
        return this.pathExists ? "exists" : "not found";
    }


    /**
     * Checks if file exists.
     *
     * @return if file exists.
     */
    public boolean fileExists() {
        return Files.exists(this.path);
    }


    /**
     * Creates file.
     *
     * @return is file created successfully.
     * @throws IOException IOException.
     */
    public boolean createFile() throws IOException {
        File storageFile = new File(path.toString());
        boolean isFileCreated = storageFile.createNewFile();
        this.pathExists = isFileCreated;
        return isFileCreated;
    }


    /**
     * Adds task to file buffer.
     *
     * @param task task to be added to file buffer.
     */
    public void addToFileBuffer(Task task) {
        String[] storageStrArr = task.toStorageStringArr();
        String joinedStorageStr = String.join(DELIMITER, storageStrArr);
        this.fileBuffer.add(joinedStorageStr);
    }


    /**
     * Writes file buffer to file.
     */
    public void writeToFile() {
        try {
            if (fileBuffer.size() > 0) {
                if (!fileExists()) {
                    // creates the file if not found
                    boolean isFileCreated = this.createFile();
                }

                FileWriter fWriter = new FileWriter(storageFile);
                BufferedWriter writer = new BufferedWriter(fWriter);

                for (String bufferedLine : this.fileBuffer) {
                    writer.append(bufferedLine).append("\n");
                }

                writer.close();
            }
            // else don't need to write
        } catch (IOException exception) {
            System.out.println("Error writing to file!");
            exception.printStackTrace();
        }
    }


    /**
     * Parses line input to an array.
     *
     * @param inputString input string.
     * @return parsed array, Array[0] => itemString, Array[1] => isDone.
     */
    private static String[] parseLine(String inputString) {
        String[] splitStr = inputString.split(DELIMITER);

        String taskType = splitStr[0];
        String status = splitStr[1];
        String taskStr = splitStr[2];

        StringBuilder parsedStringBuilder = new StringBuilder();

        switch (taskType) {
        case ("T"):
            parsedStringBuilder.append("todo ").append(taskStr);
            break;
        case ("E"):
            parsedStringBuilder.append("event ").append(taskStr).append(" /at ").append(splitStr[3]);
            break;
        case ("D"):
            parsedStringBuilder.append("deadline ").append(taskStr).append(" /by ").append(splitStr[3]);
            break;
        }

        return new String[]{parsedStringBuilder.toString(), status};
    }


    /**
     * Reads file for saved tasks.
     *
     * @return 2D array of saved tasks. Each task is given in an array of size 2.
     */
    public String[][] readFromFile() {
        try {
            ArrayList<String[]> parsedLines = new ArrayList<>();

            BufferedReader reader = new BufferedReader(new FileReader(this.storageFile));

            String currLine = reader.readLine();
            while (currLine != null) {
                parsedLines.add(parseLine(currLine));
                currLine = reader.readLine();
            }

            return parsedLines.toArray(new String[0][0]);

        } catch (FileNotFoundException exception) {
            return new String[0][0];
        } catch (IOException exception) {
            System.out.println("Error reading file!");
            exception.printStackTrace();
            return new String[0][0];
        }
    }

}
