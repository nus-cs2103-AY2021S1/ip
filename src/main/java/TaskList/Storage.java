package TaskList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Tasks.Task;
import Tasks.Todo;
import Tasks.Deadline;
import Tasks.Event;

/**
 * TaskList.Storage saves the list in a file so the state of the list can be preserved
 * when the program restarts.
 */
public class Storage {
    private File file;

    public Storage(File file) {
        try {
            this.file = file;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Storage() {
        try {
            String dir = System.getProperty("user.dir") + "/data";
            File path = new File(dir);
            if (!path.exists()) {
                path.mkdir();
            }
            file = new File(path + "/duke.txt");
            boolean result = file.createNewFile();
            if (result) {
                System.out.println("file created " + file.getCanonicalPath());
            } else {
                System.out.println("file exists at: " + file.getCanonicalPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Makes the storage file empty.
     */
    void reset() {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(file.getAbsolutePath()))) {
            bufferedWriter.write("");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Parses the text file into tasks and returns them in a list.
     */
    ArrayList<Task> load() {
        System.out.println("reading... ");
        ArrayList<Task> list = new ArrayList<>();
        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader(file.getAbsolutePath()))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] parts = line.split("</>");
                Task task = null;
                if (parts[0].equals("TODO") && parts.length == 3) {
                    task = new Todo(parts[2]);
                } else if (parts[0].equals("DEADLINE") && parts.length == 4) {
                    task = new Deadline(parts[2], parts[3]);
                } else if (parts[0].equals("EVENT") && parts.length == 4) {
                    task = new Event(parts[2], parts[3]);
                }
                assert (task != null);
                if (parts[1].equals("true")) {
                    task.setCompleted();
                }
                list.add(task);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    /**
     * Adds one task to the storage file.
     * @param task the task to be added
     */
    void addTask(Task task) {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(file.getAbsolutePath(), true))) {
            String separator = "</>";
            Task.Type type = task.getType();
            String fileContent = type.toString() + separator
                    + task.getCompleted() + separator + task.getName();
            if (type == Task.Type.DEADLINE) {
                assert (task instanceof Deadline);
                fileContent += separator + ((Deadline) task).getDeadline();
            } else if (type == Task.Type.EVENT) {
                assert (task instanceof Event);
                fileContent += separator + ((Event) task).getTime();
            }
            bufferedWriter.write(fileContent);
            bufferedWriter.newLine();
        } catch (IOException e) {
            // Exception handling
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds all the tasks in the given list to the file.
     * @param list the list containing the tasks to be added
     */
    public void addAll(TaskList list) {
        for (Task t : list.getTaskList()) {
            addTask(t);
        }
    }
}
