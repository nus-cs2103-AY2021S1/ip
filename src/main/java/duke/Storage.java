package duke;

import duke.exception.DukeException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage for loading and saving tasks to file.
 */
public class Storage {
    private String filePath;

    /** Creates a new Storage with the given filePath.
     *
     * @param filePath a relative URL giving the base location of the file to write to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /** Saves the tasks to the file located at filePath.
     * Tasks are saved in the format
     * "<taskCategory> | <taskStatus> | <taskDescription> | <taskTime>".
     *
     * @param tasks
     */
    public void save(TaskList tasks) {
        try {
            String fileData = tasks.getAllTasksPlainText();
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(fileData);
            fw.close();
        } catch (IOException e) {
            System.out.println("An IO exception has occurred writing to ." + this.filePath);
        }
    }

    /** Loads tasks from the file located at filePath.
     *
     * @return A array of strings, each representing a newline found in the file.
     * @throws DukeException if there is no file at filePath.
     */
    public String[] load() throws DukeException {
        try {
            File file = new File(this.filePath);

            // creates data directory if it does not exist
            file.getParentFile().mkdirs();

            // creates duke.txt if it does not exist
            if (!file.exists()) {
                file.createNewFile();
            }

            Scanner s = new Scanner(file);
            ArrayList<String> fileData = new ArrayList<>();

            while (s.hasNext()) {
                String plainText = s.nextLine();
                fileData.add(plainText);
            }

            String[] strAr = new String[fileData.size()];
            return fileData.toArray(strAr);
        } catch (IOException e) {
            throw new DukeException("File could not be found");
        }
    }
}
