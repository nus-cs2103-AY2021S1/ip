package seedu.duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that store, read and write data to local txt file.
 */
public class Storage {
    private static final String DIRECTORY_PATH = "data";
    private static final String STORAGE_PATH = "data/duke.txt";
    private static final String TODO_SYMBOL = "T";
    private static final String EVENT_SYMBOL = "E";
    private static final String DEADLINE_SYMBOL = "D";
    private static final String INVALID_SYMBOL = "Invalid Symbol!";
    private static final String DONE = "1";
    private static final String NOT_DONE = "0";

    /**
     * Initializes an instance of Storage class.
     * Creates new folder and file if directory did not exist.
     *
     * @throws IOException If invalid path name given.
     */
    public Storage() throws IOException {
        File folderDirectory = new File(DIRECTORY_PATH);
        if (!folderDirectory.exists()) {
            folderDirectory.mkdir();
        }
        File txt = new File(STORAGE_PATH);
        if (!txt.exists()) {
            txt.createNewFile();
        }

    }

    /**
     * Erases and wipe out all data in the txt file.
     *
     * @throws IOException If invalid path name given.
     */
    private static void clearTasks() throws IOException {
        FileWriter storageWriter = new FileWriter(STORAGE_PATH, false);
        storageWriter.write("");
        storageWriter.close();
    }

    /**
     * Writes new task to local txt file.
     *
     * @param task Name of the task to be written.
     * @throws IOException If invalid path name given.
     */
    public static void addTask(String task) throws IOException {
        File data = new File(STORAGE_PATH);
        FileWriter writer = new FileWriter(data, true);
        writer.write(task);
        writer.write('\n');
        writer.flush();
        writer.close();
    }

    /**
     * Loads up existing data in a txt file stored in locally when Duke program starts.
     *
     * @param listOfTasks of tasks stored in ArrayList.
     *
     * @return Arraylist storing Tasks.
     * @throws FileNotFoundException If no file is found on the local directory.
     * @throws IOException If invalid input given.
     */
    public static ArrayList<Task> loadFromStorage(ArrayList<Task> listOfTasks)
            throws FileNotFoundException, IOException {
        File data = new File(STORAGE_PATH);
        FileReader fr = new FileReader(data);
        BufferedReader br = new BufferedReader(fr);
        String task = "";
        while (true) {
            task = br.readLine();
            if (task == null) {
                break;
            }
            String[] arrOfTasks = splitString(task);
            String typeOfTask = arrOfTasks[0];
            String isDone = arrOfTasks[1];
            String nameOfTask = arrOfTasks[2];
            switch (typeOfTask) {
            case TODO_SYMBOL:
                loadToDo(nameOfTask, isDone, listOfTasks);
                break;
            case DEADLINE_SYMBOL:
                String date = arrOfTasks[3];
                loadDeadline(nameOfTask, isDone, date, listOfTasks);
                break;
            case EVENT_SYMBOL:
                date = arrOfTasks[3];
                loadEvent(nameOfTask, isDone, date, listOfTasks);
                break;
            default:
                System.out.println(INVALID_SYMBOL);
                break;
            }
        }
        return listOfTasks;
    }

    /**
     * Splits the string of task from storage to an array.
     *
     * @param taskFromStorage String of task from storage.
     * @return Array of strings resulted from taskFromStorage being partitioned.
     */
    private static String[] splitString(String taskFromStorage) {
        String[] arrOfTasks = taskFromStorage.split(" ~ ");
        String typeOfTask = arrOfTasks[0];
        assert arrOfTasks.length >= 3; //Must be a valid Task string
        assert typeOfTask.equals(TODO_SYMBOL) || typeOfTask.equals(DEADLINE_SYMBOL)
                || typeOfTask.equals(EVENT_SYMBOL); //Valid Symbol
        return arrOfTasks;
    }

    /**
     * Retrieves Todo tasks from local text file to Duke.
     *
     * @param nameOfTask name of Todo task.
     * @param isDone status of Todo task.
     * @param listOfTasks Arraylist storing all the tasks.
     */
    private static void loadToDo(String nameOfTask, String isDone, ArrayList<Task> listOfTasks) {
        Todo tempTodo;
        if (isDone.equals(DONE)) {
            tempTodo = new Todo(nameOfTask, true);
        } else {
            tempTodo = new Todo(nameOfTask, false);
        }
        listOfTasks.add(tempTodo);
    }

    /**
     * Retrieves Deadlube tasks from local text file to Duke.
     *
     * @param nameOfTask name of Deadline task.
     * @param isDone status of Deadline task.
     * @param listOfTasks Arraylist storing all the tasks.
     */
    private static void loadDeadline(String nameOfTask, String isDone, String date, ArrayList<Task> listOfTasks) {
        Deadline tempDeadline;
        if (isDone.equals(DONE)) {
            tempDeadline = new Deadline(nameOfTask, true, date);
        } else {
            tempDeadline = new Deadline(nameOfTask, false, date);
        }
        listOfTasks.add(tempDeadline);
    }

    /**
     * Retrieves Event tasks from local text file to Duke.
     *
     * @param nameOfTask name of Event task.
     * @param isDone status of Event task.
     * @param listOfTasks Arraylist storing all the tasks.
     */
    private static void loadEvent(String nameOfTask, String isDone, String date, ArrayList<Task> listOfTasks) {
        Event tempEvent;
        if (isDone.equals(DONE)) {
            tempEvent = new Event(nameOfTask, true, date);
        } else {
            tempEvent = new Event(nameOfTask, false, date);
        }
        listOfTasks.add(tempEvent);
    }



    /**
     * Helper method to mark task as complete in a local text file.
     *
     * @param index Index of the task.
     * @param size Size of tasklist.
     */
    public static void completeTaskOnFile(int index, int size) {
        try {
            File data = new File(STORAGE_PATH);
            FileReader fr = new FileReader(data);
            BufferedReader br = new BufferedReader(fr);
            ArrayList<String> tasks = new ArrayList<>();
            completeTask(br, tasks, index, size);
        } catch (IOException ee) {
            System.out.println(ee.getMessage());
        }
    }

    /**
     * Marks task as complete in a local text file.
     *
     * @param br BufferedReader to read lines from text file.
     * @param tasks Arraylist of tasks to track.
     * @param index Index of the task to be mark as completed.
     * @param size Size of the TaskList.
     */
    private static void completeTask(BufferedReader br, ArrayList<String> tasks, int index, int size)
            throws IOException {
        for (int i = 0; i < size; i++) {
            String task = br.readLine();
            if (i == index) {
                assert task.contains(NOT_DONE); //Must contain an uncompleted task
                task = task.replaceFirst(NOT_DONE, DONE);
            }
            tasks.add(task);
        }
        Storage.clearTasks();
        for (int i = 0; i < size; i++) {
            Storage.addTask(tasks.get(i));
        }
    }

    /**
     * Helper method to delete a task locally from the txt file.
     *
     * @param index Index of the task.
     * @param size Size of the TaskList.
     */
    public static void deleteTaskOnFile(int index, int size) {
        try {
            File data = new File(STORAGE_PATH);
            FileReader fr = new FileReader(data);
            BufferedReader br = new BufferedReader(fr);
            ArrayList<String> tasks = new ArrayList<>();
            deleteTask(br, tasks, index, size);
        } catch (IOException ee) {
            System.out.println(ee.getMessage());
        }
    }

    /**
     * Deletes task in a local text file.
     *
     * @param br BufferedReader to read lines from text file.
     * @param tasks Arraylist of tasks to track.
     * @param index Index of the task to be deleted.
     * @param size Size of the TaskList.
     */
    private static void deleteTask(BufferedReader br, ArrayList<String> tasks, int index, int size) throws IOException {
        for (int i = 0; i < size; i++) {
            String task = br.readLine();
            if (i != index) {
                tasks.add(task);
            }
        }
        Storage.clearTasks();
        for (int i = 0; i < size - 1; i++) {
            Storage.addTask(tasks.get(i));
        }
    }

}
