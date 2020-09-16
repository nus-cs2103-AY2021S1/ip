package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

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
    private static final DateTimeFormatter SAVE_READ_DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private File dukeFile;
    private String filePath;
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
        assert dukeFile.exists();
    }

    /**
     * Loads the data from the dukeFile if there is any into the current TaskList.
     *
     * @param ui the ui that will inform the user of errors in loading the tasks.
     * @return the lists of task that will be given to TaskList.
     */
    public ArrayList<Task> load(Ui ui) throws DukeException {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        try {
            Scanner sc1 = new Scanner(dukeFile);
            while (sc1.hasNext()) {
                String loadedInput = sc1.nextLine();
                String taskType = loadedInput.substring(0, 3);
                String description = loadedInput.substring(7);
                boolean isComplete;
                isComplete = loadedInput.substring(4, 7).equals("[✓]");
                switch (taskType) {
                case ("[T]") :
                    loadTodo(listOfTasks, description, isComplete);
                    break;
                case("[D]") :
                    loadDeadline(listOfTasks, description, isComplete);
                    break;
                case("[E]"):
                    loadEvent(listOfTasks, description, isComplete);
                    break;
                default:
                    assert false : taskType;
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

    private void loadTodo(ArrayList<Task> listOfTasks, String description, boolean isComplete) {
        Todo newTodo = new Todo(description);
        if (isComplete) {
            newTodo.completeTask();
        }
        listOfTasks.add(newTodo);
    }

    private void loadDeadline(ArrayList<Task> listOfTasks, String description, boolean isComplete) {
        boolean isRepeated = false;
        if (!description.substring(0, 5).equals("[NRP]")) {
            isRepeated = true;
        } else {
            assert description.substring(0, 5).equals("[NRP]");
        }
        int byPosition = description.indexOf("by:");
        Deadline newDeadline = new Deadline(description.substring(5, byPosition));
        if (isComplete) {
            newDeadline.completeTask();
        }
        LocalDateTime date = LocalDateTime.parse(description.substring(byPosition + 3),
                SAVE_READ_DATETIME_FORMAT);
        newDeadline.setTime(date);
        if (isRepeated) {
            Repeater.setRecurrence(newDeadline, description.substring(0, 5));
            date = Repeater.correctDate(newDeadline);
            newDeadline.setTime(date);
        }
        listOfTasks.add(newDeadline);
    }

    private void loadEvent(ArrayList<Task> listOfTasks, String description, boolean isComplete) {
        boolean isRepeated = false;
        if (!description.substring(0, 5).equals("[NRP]")) {
            isRepeated = true;
        } else {
            assert description.substring(0, 5).equals("[NRP]");
        }
        int atPosition = description.indexOf("at:");
        Event newEvent = new Event(description.substring(5, atPosition));
        if (isComplete) {
            newEvent.completeTask();
        }
        LocalDateTime date = LocalDateTime.parse(description.substring(atPosition + 3),
                SAVE_READ_DATETIME_FORMAT);
        newEvent.setTime(date);
        if (isRepeated) {
            Repeater.setRecurrence(newEvent, description.substring(0, 5));
            date = Repeater.correctDate(newEvent);
            newEvent.setTime(date);
        }
        listOfTasks.add(newEvent);
    }
}
