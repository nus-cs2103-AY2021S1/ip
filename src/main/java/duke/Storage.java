package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;



/**
 * Storage links the TaskList from Duke to the user's local storage.
 */
public class Storage {
    private final Path pathToStorage;
    private List<String> allTasks;

    /**
     * Constructor to create a Storage object, which will be used when Duke creates, updates, or deletes Tasks.
     *
     * @param pathToStorage the path to the user's storage where the Tasks will be stored.
     */
    public Storage(Path pathToStorage) {
        this.pathToStorage = pathToStorage;
        assert pathToStorage != null : "Path to storage cannot be null";
        try {
            // Create directory if needed
            Path parentPath = pathToStorage.getParent();
            Files.createDirectories(parentPath);

            if (!Files.exists(pathToStorage)) {
                Files.createFile(pathToStorage);
            }
            // Get stored info if any
            this.allTasks = Files.readAllLines(pathToStorage);
        } catch (IOException e) {
            System.out.println("Can't read file " + e.getMessage());
        }
    }

    /**
     * Converts string from task storage file to a task list for Duke to use.
     *
     * @return a list of Tasks readable by duke.Duke.
     */
    public ArrayList<Task> getAllTasks() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        // Convert string to Tasks
        for (String taskString : this.allTasks) {
            char taskType = taskString.charAt(0);
            assert taskType == 'T' || taskType == 'E' || taskType == 'D' : "Task type is wrong";

            boolean isDone = false;

            int descriptionIndex = taskString.lastIndexOf("|") + 2;
            String taskDetails = taskString.substring(descriptionIndex);

            if (taskString.charAt(4) == '1') {
                isDone = true;
            }

            String eventOrDeadlineDescription = "";
            if (taskType == 'E' || taskType == 'D') {
                eventOrDeadlineDescription = taskString.substring(8, taskString.lastIndexOf("|") - 1);
            }

            switch (taskType) {
            case 'T':
                taskList.add(new ToDo(taskDetails, isDone));
                break;
            case 'E':
                taskList.add(new Event(eventOrDeadlineDescription, isDone, formatTaskDateTime(taskDetails)));
                break;
            case 'D':
                taskList.add(new Deadline(eventOrDeadlineDescription, isDone, formatTaskDateTime(taskDetails)));
                break;
            default:
                System.out.println("Unable to determine type of task");
                break;
            }
        }

        return taskList;
    }

    private String formatTaskDateTime(String dateTime) {
        assert dateTime.length() != 0 : "Date and time cannot be empty";
        String[] dateTimes = dateTime.split(",");
        String date = dateTimes[0]; // MMM DD YYYY
        String time = dateTimes[1]; // HH:MM:SS

        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy");
        LocalDate newDate = LocalDate.parse(date, format);
        int monthInt = newDate.getMonthValue();
        String year = String.valueOf(newDate.getYear());
        int dayInt = newDate.getDayOfMonth();

        String month = monthInt >= 10
                ? String.valueOf(monthInt)
                : "0".concat(String.valueOf(monthInt));

        String day = dayInt >= 10
                ? String.valueOf(dayInt)
                : "0".concat(String.valueOf(dayInt));

        String hourAndMinutes = time.substring(0, time.lastIndexOf(":"));

        // Return YYYY/MM/DD HH:MM
        String ans = year + "/" + month + "/" + day + hourAndMinutes;
        return ans;
    }

    /**
     * Saves all the Tasks from the TaskList into the user's local storage.
     */
    public void writeToStorage() {
        try {
            assert pathToStorage != null : "Path to storage cannot be empty";
            File storageFile = new File(String.valueOf(pathToStorage));
            FileWriter fw = new FileWriter(storageFile);
            for (String task : this.allTasks) {
                fw.write(task + '\n');
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds a new Task to the local storage.
     *
     * @param task the Task to be added.
     */
    public void createTask(Task task) {
        assert task != null : "Task cannot be null";
        this.allTasks.add(task.toString());
        writeToStorage();
    }

    /**
     * Updates an existing task in the local storage.
     *
     * @param task the task to be updated.
     * @param taskIndex the index of the Task in the List of all the Tasks.
     */
    public void updateTask(Task task, int taskIndex) {
        assert task != null : "Task cannot be null";
        assert taskIndex < allTasks.size() : "Task index must be within range of allTask size";
        this.allTasks.set(taskIndex, task.toString());
        writeToStorage();
    }

    /**
     * Removes an existing from the local storage.
     *
     * @param taskIndex the index of the task in the List of all the Tasks to be deleted.
     */
    public void deleteTask(int taskIndex) {
        assert taskIndex < allTasks.size() && taskIndex >= 0 : "Task index must be within range of allTask size";
        this.allTasks.remove(taskIndex);
        writeToStorage();
    }

    /**
     * Updates the local storage when Duke undoes changes.
     *
     * @param taskList is the new TaskList to be written to local storage.
     */
    public void undoChanges(TaskList taskList) {
        List<String> newList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            newList.add(taskList.get(i).toString());
        }
        this.allTasks = newList; // Substitute old List with new List which is the previous taskList state
        writeToStorage();
    }
}
