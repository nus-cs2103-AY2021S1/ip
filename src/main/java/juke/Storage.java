package juke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import juke.exception.UnknownTaskException;
import juke.task.Deadline;
import juke.task.Event;
import juke.task.Task;
import juke.task.Todo;

/**
 * Represents a Storage class that handles all the hard-disk storage of the Tasklist.
 */
public class Storage {

    /**
     * Loads an existing stored list of tasks (if any).
     *
     * @return ArrayList of Tasks.
     */
    public ArrayList<Task> load() {
        try {
            String root = System.getProperty("user.dir");
            File file = this.checkAndCreateFile(root);

            assert file.exists() : "File not created";

            ArrayList<Task> list = new ArrayList<>();
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String[] next = scanner.nextLine().split("/");
                Task task = this.assignTask(next);
                list.add(task);
            }

            return list;
        } catch (FileNotFoundException ex) {
            return new ArrayList<>();
        } catch (UnknownTaskException ex) {
            System.out.println("Task File Corrupted");
            return new ArrayList<>();
        }
    }

    /**
     * Checks and identifies storage file (if available), or else
     * creates a new and empty file.
     *
     * @param root User project root path.
     * @return Storage file.
     */
    protected File checkAndCreateFile(String root) {
        try {
            boolean directoryExists = Files.exists(Paths.get(root, "data"));
            boolean fileExists = Files.exists(Paths.get(root, "data", "dukeTaskList.txt"));
            if (!directoryExists) {
                Files.createDirectory(Paths.get(root, "data"));
            }
            if (!fileExists) {
                Files.createFile(Paths.get(root, "data", "dukeTaskList.txt"));
            }
            return new File(Paths.get(root, "data", "dukeTaskList.txt").toString());
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
            return null;
        }
    }

    /**
     * Saves the current list of tasks to file.
     *
     * @throws IOException
     */
    public void saveTasksToFile() throws IOException {
        String root = System.getProperty("user.dir");

        assert Files.exists(Paths.get(root, "data", "dukeTaskList.txt")) : "File does not exist";

        FileWriter fw = new FileWriter(Paths.get(root, "data", "dukeTaskList.txt").toString());
        for (int i = 0; i < TaskList.getListSize(); i++) {
            Task task = TaskList.getTask(i);
            String toAdd = task.taskSaver();
            fw.write(toAdd + "\n");
        }
        fw.close();
    }

    private boolean taskIsDone(String task) {
        return task.equals("1");
    }

    private Task assignTask(String[] next) throws UnknownTaskException {
        Task task;

        switch (next[0]) {
        case "T":
            task = new Todo(next[2]);
            break;
        case "D":
            task = new Deadline(next[2], next[3]);
            break;
        case "E":
            task = new Event(next[2], next[3]);
            break;
        default:
            throw new UnknownTaskException("A task seems to be corrupted");
        }

        if (this.taskIsDone(next[1])) {
            task.markAsDone();
        }

        return task;
    }
}
