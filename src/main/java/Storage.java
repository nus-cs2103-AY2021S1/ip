import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to load and write tasks objects to the local drive
 * @author vanGoghhh
 */

public class Storage {

    private String filepath;

    /**
     * Constructor for Storage
     * @param filepath specified filepath in the user's local drive
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads data from the user's local files
     * @return the tasks in the users local files in an arraylist
     * @throws IOException
     */
    protected ArrayList<Task> loadData() throws IOException {
            ArrayList<Task> taskList = new ArrayList<>();
            Files.createDirectories(Paths.get("data"));
            File dukeData = new File(this.filepath);
            if (dukeData.exists()) {
                Scanner dukeScanner = new Scanner(dukeData);
                while (dukeScanner.hasNext()) {
                    String txtLine = dukeScanner.nextLine();
                    String[] txtLineArr = txtLine.split("//");
                    Task task;
                    if (txtLineArr[0].equals("T")) {
                        task = new Todo(txtLineArr[2]);
                        if (txtLineArr[1].equals("1")) {
                            task.markAsDone();
                        }
                    } else if (txtLineArr[0].equals("E")) {
                        task = new Event(txtLineArr[2], LocalDate.parse(txtLineArr[3]));
                        if (txtLineArr[1].equals("1")) {
                            task.markAsDone();
                        }
                    } else {
                        task = new Deadline(txtLineArr[2], LocalDate.parse(txtLineArr[3]));
                        if (txtLineArr[1].equals("1")) {
                            task.markAsDone();
                        }
                    }
                    taskList.add(task);
                }
                return taskList;
            } else {
                File newDukeData = new File(this.filepath);
                newDukeData.createNewFile();
                return taskList;
        }
    }

    /**
     * Writes data to the user's local files
     * @param tasks TaskList containing all tasks
     * @throws IOException
     */
    protected void writeData(TaskList tasks) throws IOException {
        FileWriter dukeWriter = new FileWriter("data/duke.txt", false);
        for (Task task : tasks.getTaskList()) {
            dukeWriter.write(task.inputInFile() + "\n");
        }
        dukeWriter.close();
    }
}
