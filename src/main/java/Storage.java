import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a storage system to load tasks from file and append/overwrite tasks.
 */
public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Scan through file at specified filePath and make use of the information to initialise tasks arraylist.
     * If directory or file does not exist, it creates the necessary directories and file.
     * @return A list of tasks found in the file at specified filePath.
     * @throws DukeException if unable to load file.
     */
    public List<Task> load() throws DukeException {
        try {
            List<Task> tasks = new ArrayList<>();
            int numTasks = 0;
            File f = new File(filePath); // create a File for the given file path

            if (f.exists()) {
                Scanner s = new Scanner(f); // create a Scanner using the File as the source

                // go through file contents and initialise the tasks arraylist
                while (s.hasNext()) {
                    String currString = s.nextLine();
                    String[] currStringArray = currString.split(" \\| ");
                    boolean isDone = currStringArray[1].equals("1");

                    if (currStringArray[0].equals("T")) {
                        tasks.add(numTasks, new ToDo(currStringArray[2], isDone));
                        numTasks++;
                    } else if (currStringArray[0].equals("D")) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        LocalDateTime byLocalDate = LocalDateTime.parse(currStringArray[3], formatter);

                        tasks.add(numTasks, new Deadline(currStringArray[2],
                                byLocalDate, isDone));
                        numTasks++;
                    } else {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        LocalDateTime atLocalDate = LocalDateTime.parse(currStringArray[3], formatter);

                        tasks.add(numTasks, new Event(currStringArray[2],
                                atLocalDate, isDone));
                        numTasks++;
                    }
                }

            } else {
                File directory = new File("data");
                directory.mkdir(); // creates the directory if it does not exist

                File file = new File(filePath);
                file.createNewFile();
            }

            return tasks;
        } catch (IOException e) {
            throw new DukeException("There was a problem loading the file.");
        }
    }

    /**
     * Add text to the file at specified filepath.
     * @param textToAppend Text to be added to the file.
     * @throws DukeException if unable to append a line to the file.
     */
    public void appendToFile(String textToAppend) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
            fw.write(textToAppend);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Failed to update tasks to storage. Please try again.");
        }

    }

    /**
     * Rewrite the entire file based on the tasks arraylist, looping through each task and formatting it.
     * @param tasks
     * @throws DukeException
     */
    public void overwriteFile(List<Task> tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            String tasksList = "";

            for (int i = 0; i < tasks.size(); i++) {
                if (i == 0) {
                    tasksList = tasks.get(i).toTxtFileFormat();
                } else {
                    tasksList = tasksList + "\n" + tasks.get(i).toTxtFileFormat();
                }
            }

            fw.write(tasksList);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Failed to update tasks to storage. Please try again.");
        }

    }
}
