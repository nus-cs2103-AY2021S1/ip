package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <h>duke.Storage</h>
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private final File file;

    /**
     * Constructor for duke.Storage class.
     * @param filePath The path of the file todolist.txt.
     * @throws IOException On input error.
     */
    public Storage(String filePath) throws IOException {
        this.file = new File(filePath);
        boolean exists = file.exists();
        if (!exists) {
            file.createNewFile();
        }
    }

    /**
     * Getter to get the file.
     * @return File The file.
     */
    public File getFile() {
        return file;
    }

    /**
     * Get the path of the file.
     * @return String the absolute path of the file.
     */
    public static String getFilePath() {
        String filePath = new File("").getAbsolutePath();
        filePath += "\\todolist.txt";
        return filePath;
    }

    /**
     * Loads the file from todolist.txt.
     * @return An arraylist of task that was stored.
     */
    protected ArrayList<Task> loadFile() {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Task> taskList = new ArrayList<>();

        scanFile(result);
        loadToTaskList(result, taskList);
        return taskList;
    }

    private void loadToTaskList(ArrayList<String> result, ArrayList<Task> taskList) {
        for (int i = 0; i < result.size(); i++) {
            String str = result.get(i);
            String[] arr = str.split(" >> ", -1);

            switch (arr[0]) {
            case "T":
                todoLoad(taskList, i, arr);
                break;
            case "D":
                deadlineLoad(taskList, i, arr);
                break;
            case "E":
                eventLoad(taskList, i, arr);
                break;
            default:
                // No such type
                Ui.printFormatError(i);
                break;
            }
        }
    }

    private void eventLoad(ArrayList<Task> taskList, int i, String[] arr) {
        try {
            if (Integer.valueOf(arr[1]).equals(0)) {
                // Incomplete task
                Task task = new Event(arr[2], arr[3]);
                taskList.add(task);
            } else if (Integer.valueOf(arr[1]).equals(1)) {
                // Completed task
                Task task = new Event(arr[2], arr[3]);
                task.markAsDone();
                taskList.add(task);
            } else {
                // Not recognised format
                Ui.printFormatError(i);
            }
        } catch (Exception ex) {
            Ui.printFormatError(i);
        }
    }

    private void deadlineLoad(ArrayList<Task> taskList, int i, String[] arr) {
        try {
            if (Integer.valueOf(arr[1]).equals(0)) {
                // Incomplete task
                Task task = new Deadline(arr[2], arr[3]);
                taskList.add(task);
            } else if (Integer.valueOf(arr[1]).equals(1)) {
                // Completed task
                Task task = new Deadline(arr[2], arr[3]);
                task.markAsDone();
                taskList.add(task);
            } else {
                // Not recognised format
                Ui.printFormatError(i);
            }
        } catch (Exception ex) {
            Ui.printFormatError(i);
        }
    }

    private void todoLoad(ArrayList<Task> taskList, int i, String[] arr) {
        try {
            if (Integer.valueOf(arr[1]).equals(0)) {
                // Incomplete task
                Task task = new Todo(arr[2]);
                taskList.add(task);
            } else if (Integer.valueOf(arr[1]).equals(1)) {
                // Completed task
                Task task = new Todo(arr[2]);
                task.markAsDone();
                taskList.add(task);
            } else {
                // Not recognised format
                Ui.printFormatError(i);
            }
        } catch (NumberFormatException ex) {
            Ui.printFormatError(i);
        }
    }

    private void scanFile(ArrayList<String> result) {
        try (Scanner s = new Scanner(new FileReader(getFile()))) {
            while (s.hasNext()) {
                result.add(s.nextLine());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }
    }

    /**
     * Save and write the file into todolist.txt.
     * @param f File The file.
     * @param ls duke.TaskList the list of the tasks to be written.
     * @throws IOException On input error.
     */
    protected static void saveFile(File f, TaskList ls) throws IOException {
        ArrayList<String> arrayList = new ArrayList<>();
        FileWriter fw = new FileWriter(f.getAbsolutePath());

        if (ls.size() == 0) {
            fw.write("");
        } else {
            for (int i = 0; i < ls.size(); i++) {
                Task task = ls.get(i);
                String str = task.convertToText();
                arrayList.add(str);
            }
            for (String str : arrayList) {
                fw.write(str + System.lineSeparator());
            }
        }

        fw.close();
    }
}
