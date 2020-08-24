package seedu.duke;

import java.io.*;
import java.util.ArrayList;

/**
 * Class that store, read and write data to local txt file.
 */
public class Storage {
    private final static String DIRECTORY_PATH = "data";
    private final static String STORAGE_PATH = "data/duke.txt";
    private String filepath;

    /**
     * Initialize an instance of Storage class.
     * Creates new folder and file if directory did not exist.
     *
     * @param filePath Directory of file.
     * @throws IOException If invalid path name given.
     */
    public Storage(String filePath) throws IOException {
        File folderDirectory = new File(DIRECTORY_PATH);
        if (!folderDirectory.exists()) {
            folderDirectory.mkdir();
        }
        this.filepath = filePath;
        File txt = new File(STORAGE_PATH);
        if (!txt.exists()) {
            txt.createNewFile();
        }

    }

    /**
     * Erase and wipe out all data in the txt file.
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
     * Load up existing data in a txt file stored in locally when Duke program starts.
     *
     * @param ls of tasks stored in ArrayList.
     *
     * @return Arraylist storing Tasks.
     * @throws FileNotFoundException If no file is found on the local directory.
     * @throws IOException If invalid input given.
     */
    public static ArrayList<Task> load(ArrayList<Task> ls) throws FileNotFoundException, IOException {
        File data = new File(STORAGE_PATH);
        FileReader fr = new FileReader(data);
        BufferedReader br = new BufferedReader(fr);
        String tasks = "";
        while (true) {
            tasks = br.readLine();
            if (tasks == null) {
                break;
            }
            String[] arrTasks = tasks.split(" ~ ");
            String typeOfTask = arrTasks[0];
            String isDone = arrTasks[1];
            String nameOfTask = arrTasks[2];
            if (typeOfTask.equals("T")) {
                if (isDone.equals("1")) {
                    Todo temp = new Todo(nameOfTask, true);
                    ls.add(temp);
                } else {
                    Todo temp = new Todo(nameOfTask, false);
                    ls.add(temp);
                }
            } else if (typeOfTask.equals("D")) {
                String date = arrTasks[3];
                if (isDone.equals("1")) {
                    Deadline temp = new Deadline(nameOfTask, true, date);
                    ls.add(temp);
                } else {
                    Deadline temp = new Deadline(nameOfTask, false, date);
                    ls.add(temp);
                }
            } else if (typeOfTask.equals("E")) {
                String date = arrTasks[3];
                if (isDone.equals("1")) {
                    Event temp = new Event(nameOfTask, true, date);
                    ls.add(temp);
                } else {
                    Event temp = new Event(nameOfTask, false, date);
                    ls.add(temp);
                }
            }
        }
        return ls;
    }

    /**
     * Make task as complete in a local txt file.
     *
     * @param taskNo Index of the task.
     * @param size Size of tasklist.
     */
    public static void completeTask(int taskNo, int size) {
        try {
            File data = new File(STORAGE_PATH);
            FileReader fr = new FileReader(data);
            BufferedReader br = new BufferedReader(fr);
            ArrayList<String> tempArr = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                String task = br.readLine();
                if (i == taskNo) {
                    String temp = task.replaceFirst("0", "1");
                    task = temp;
                }
                tempArr.add(task);
            }
            Storage.clearTasks();
            for (int i = 0; i < size; i++) {
                Storage.addTask(tempArr.get(i));
            }
        } catch (IOException ee) {
            System.out.println(ee.getMessage());
        }
    }

    /**
     * Delete a task locally from the txt file.
     *
     * @param index Index of the task.
     * @param size Size of the tasklist.
     */
    public static void deleteTask(int index, int size) {
        try {
            File data = new File(STORAGE_PATH);
            FileReader fr = new FileReader(data);
            BufferedReader br = new BufferedReader(fr);
            ArrayList<String> tempArr = new ArrayList<>();
            for (int i = 0; i <= size; i++) {
                String task = br.readLine();
                if (i != index) {
                    tempArr.add(task);
                }
            }
            Storage.clearTasks();
            for (int i = 0; i < tempArr.size(); i++) {
                Storage.addTask(tempArr.get(i));
            }
        } catch (IOException ee) {
            System.out.println(ee.getMessage());
        }
    }

}
