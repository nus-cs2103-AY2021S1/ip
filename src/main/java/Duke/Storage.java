package duke;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage saves data and loads data from the hard drive so that tasks saved previously
 * can be accessed again.
 *
 * @author Joshua
 */
public class Storage {
    /**
     * dukeFile is the file where data is saved and loaded to.
     * filePath is the file path that leads to dukeFile.
     * SAVE_READ_DATETIME_FORMAT is the date time format that dates are stored in Duke.
     */
    private File dukeFile;
    private String filePath;
    private final static DateTimeFormatter SAVE_READ_DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Creates the Storage class with the given filePath as a String.
     * This initializes the dukeFile or creates the dukeFile if it does not exist.
     *
     * @param filePath the filePath that leads to the previously created dukeFile if it exists.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        dukeFile = new File(filePath);
        if (!dukeFile.exists()) {
            try {
                File dukeParent = new File(dukeFile.getParent());
                if (!dukeParent.exists()) {
                    dukeParent.mkdirs();
                }
                dukeFile.createNewFile();
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }
    }

    /**
     * Loads the data from the dukeFile if there is any into the current TaskList.
     *
     * @param ui the ui that will inform the user of errors in loading the tasks.
     * @return the lists of task that will be given to TaskList.
     */
    public ArrayList<Task> load(Ui ui) {
        ArrayList<Task> listOfTasks = new ArrayList<Task>();
        try {
            Scanner sc1 = new Scanner(dukeFile);
            while(sc1.hasNext()) {
                String loadedInput = sc1.nextLine();
                if (loadedInput.substring(0, 3).equals("[T]")) {
                    Todo newTodo = new Todo(loadedInput.substring(7));
                    if (loadedInput.substring(4, 7).equals("[✓]")) {
                        newTodo.completeTask();
                    }
                    listOfTasks.add(newTodo);
                } else if (loadedInput.substring(0, 3).equals("[D]")) {
                    int byPosition = loadedInput.indexOf("by:");
                    Deadline newDeadline = new Deadline(
                            loadedInput.substring(7, byPosition));
                    if (loadedInput.substring(4, 7).equals("[✓]")) {
                        newDeadline.completeTask();
                    }
                    LocalDateTime date = LocalDateTime.parse(loadedInput.substring(byPosition + 3), SAVE_READ_DATETIME_FORMAT);
                    newDeadline.setTime(date);
                    listOfTasks.add(newDeadline);
                } else if (loadedInput.substring(0, 3).equals("[E]")) {
                    int atPosition = loadedInput.indexOf("at:");
                    Event newEvent = new Event(
                            loadedInput.substring(7, atPosition));
                    if (loadedInput.substring(4, 7).equals("[✓]")) {
                        newEvent.completeTask();
                    }
                    LocalDateTime date = LocalDateTime.parse(loadedInput.substring(atPosition + 3), SAVE_READ_DATETIME_FORMAT);
                    newEvent.setTime(date);
                    listOfTasks.add(newEvent);
                }
            }
        } catch (FileNotFoundException e) {
            ui.showError(e.toString());
        }
        return listOfTasks;
    }

    /**
     * Updates the storage with the current TaskList.
     *
     * @param taskList the TaskList with the info to be updated.
     */
    public void updateStorage(TaskList taskList) {
        try {
            FileWriter dukeFileWriter = new FileWriter(filePath, false);
            for (int i = 0; i < taskList.getTaskList().size(); i++) {
                dukeFileWriter.write(taskList.getTaskList().get(i).saveFormat() + "\n");
            }
            dukeFileWriter.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
