package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {

    private final File folder;
    private final File file;

    /**
     * Creates a Storage.
     *
     * @param folderPath for Duke to load saved TaskList if there exists
     * @param filePath for Duke to load saved TaskList if there exists
     */
    public Storage(String folderPath, String filePath) {
        this.folder = new File(folderPath);
        this.file = new File(filePath);
    }

    /**
     * Reads from a .txt file and change the Strings into tasks.
     *
     * @return ArrayList that contains tasks
     * @throws DukeException DukeException
     */
    public ArrayList<Task> readFromFile() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            if (!this.folder.exists()) {
                boolean isFolderCreated = this.folder.mkdir();
                if (isFolderCreated) {
                    System.out.println("Folder did not exist. New folder was created!");
                } else {
                    System.out.println("Folder did not exist but new folder was not created.");
                }
            }
            if (!this.file.exists()) {
                boolean isFileCreated = this.file.createNewFile();
                if (isFileCreated) {
                    System.out.println("File did not exist. New file was created!");
                } else {
                    System.out.println("Folder did not exist but new file was not created.");
                }
            }

            Scanner sc = new Scanner(this.file);

            while (sc.hasNext()) {
                String[] taskArray;
                taskArray = sc.nextLine().split(" \\| ");
                String taskType = taskArray[0];
                String isDone = taskArray[1];
                String taskName = taskArray[2];
                Task task = null;
                switch (taskType) {
                case "T":
                    task = new Todo(taskName);
                    break;
                case "D":
                    task = new Deadline(taskName,
                            LocalDateTime.parse(taskArray[3].replace(", " , "T"),
                                    DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HHmm")));
                    break;
                case "E":
                    task = new Event(taskName,
                            LocalDateTime.parse(taskArray[3].replace(", ", "T"),
                                    DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HHmm")));
                    break;
                default:
                    break;
                }
                if (isDone.equals("1")) {
                    assert task != null;
                    task.markDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("File is empty when loading");
        }
        return tasks;
    }

    /**
     * Appends a String to a .txt file.
     *
     * @param textToAppend text to append to the .txt file
     * @throws IOException IOException
     */
    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(this.file, true); // create a FileWriter in append mode
        fw.write(textToAppend + "\n");
        fw.close();
    }

    /**
     * Overwrites a .txt file.
     *
     * @param tasks ArrayList of tasks
     * @throws IOException IOException
     */
    public void rewriteFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(this.file);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (i == 0) {
                fw.write(task.toText() + "\n");
                fw.close();
            } else {
                appendToFile(task.toText());
            }
        }
    }

    private void printFileContents() {
        Scanner s = null; // create a Scanner using the File as the source
        try {
            s = new Scanner(this.file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }
}
