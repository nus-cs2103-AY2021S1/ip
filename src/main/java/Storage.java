import javafx.application.Platform;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the file handling aspect of reading and writing files.
 */
public class Storage {
    String filePath;
    ArrayList<Task> loadedTasks;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.loadedTasks = new ArrayList<Task>();
    }

    /**
     * Writes task information to the file for permanent storage.
     * @param filePath path to file
     * @param textToAdd text to be added into the file
     * @param isAppending indicates if text is being appended to the file
     * @throws IOException for input/output errors
     */
    private void writeToFile(String filePath, String textToAdd, boolean isAppending) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, isAppending);
        fileWriter.write(textToAdd);
        fileWriter.close();
    }

    /**
     * Returns an ArrayList of tasks from the stored tasks information upon reading the file.
     * @return ArrayList of existing tasks
     * @throws DukeException if file is not found
     * @throws IOException for input/output errors
     */
    public ArrayList<Task> load() throws DukeException, IOException {
        try {
            File file = new File(this.filePath);
            file.getParentFile().mkdir();
            file.createNewFile();

            Scanner s = new Scanner(file);

            while (s.hasNext()) {

                String nextLine = s.nextLine();
                Scanner s2 = new Scanner(nextLine);
                String taskType = s2.next();
                boolean isDone = false;
                Task currTask;

                if (taskType.equals("T")) {
                    s2.useDelimiter(" ~ ");
                    if (s2.nextInt() == 1) {
                        isDone = true;
                    }
                    currTask = new Todo(s2.next());
                    loadedTasks.add(currTask);
                    if (isDone) {
                        currTask.markAsDone();
                    }
                } else if (taskType.equals("D")) {
                    s2.useDelimiter(" ~ ");
                    if (s2.nextInt() == 1) {
                        isDone = true;
                    }
                    currTask = new Deadline(s2.next(), s2.next());
                    loadedTasks.add(currTask);
                    if (isDone) {
                        currTask.markAsDone();
                    }
                } else {
                    s2.useDelimiter(" ~ ");
                    if (s2.nextInt() == 1) {
                        isDone = true;
                    }
                    currTask = new Event(s2.next(), s2.next());
                    loadedTasks.add(currTask);
                    if (isDone) {
                        currTask.markAsDone();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found");
        } catch (IOException e) {
            throw new DukeException("Input-Output error");
        }
        return this.loadedTasks;
    }

    /**
     * Saves tasks into a file for permanent storage.
     * @param tasks ArrayList of tasks to be saved
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            for (int i = 0; i < tasks.size(); i++) {
                if (i == 0) {
                    writeToFile(filePath, tasks.get(i).getStoringFormat() +
                            System.lineSeparator(), false);
                } else {
                    writeToFile(filePath, tasks.get(i).getStoringFormat() +
                            System.lineSeparator(), true);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
