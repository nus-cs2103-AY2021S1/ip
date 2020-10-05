/**
 * Handles all Files input-output related functions.
 */
package storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.ToDo;

public class Storage {

    public final String filePath;
    private File data;

    /**
     * Creates the directories and the file given the filepath
     * if file does not exists. Then, returns Storage which handles
     * the manipulation of asset in the filepath.
     *
     * @param filepath file path for an existing asset or to initialise a new asset.
     * @throws StorageException io exception generated from creating file.
     */
    public Storage(String filepath) throws StorageException {
        this.filePath = filepath;
        String[] tokens = filepath.split("/");
        int pathLength = tokens.length;
        if (pathLength == 1) {
            throw new StorageException("Invalid file path: " + filepath, new Throwable("bad path"));
        } else {
            String path = tokens[0];
            for (int i = 1; i < pathLength; i++) {
                File directory = new File(path);
                if (!directory.exists()) {
                    directory.mkdir();
                }
                path += "/" + tokens[i];
            }
            try {
                data = new File(path);
                data.createNewFile();
            } catch (IOException e) {
                throw new StorageException("Error occurred while loading asset file: " + filepath, e);
            }
        }
    }

    /**
     * Storage read the asset in the filepath and loads the
     * Tasks in the asset into an ArrayList.
     *
     * @return ArrayList list of tasks
     * @throws StorageException exception generated from reading asset file.
     */
    public ArrayList<Task> load() throws StorageException {
        ArrayList<Task> items = new ArrayList<>();
        try {
            FileReader input = new FileReader(data.getAbsoluteFile());
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                items.add(dataToTask(scanner.nextLine()));
            }
            input.close();
            scanner.close();
        } catch (IOException e) {
            throw new StorageException("Error occurred while reading asset.", e);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new StorageException("Asset file is corrupted.", e);
        }
        return items;
    }

    /**
     * Reads the tasks in the taskList and updates the tasks in the
     * filepath.
     *
     * @param taskList
     * @return boolean returns true if taskList is successfully persisted.
     * @throws StorageException IOException when writing to asset file.
     */
    public boolean persistTaskList(TaskList taskList) throws StorageException {
        assert taskList != null : "taskList cannot be null";

        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(filePath));
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                String isLoaded = task.isDone() ? "1" : "0";
                if (task.getClass().isAssignableFrom(ToDo.class)) {
                    String s = "T@" + isLoaded + "@" + task.getDescription();
                    output.write(s);
                } else if (task.getClass().isAssignableFrom(Event.class)) {
                    String s = "E@" + isLoaded + "@" + task.getDescription() + "@" + ((Event) task).getTime();
                    output.write(s);
                } else {
                    String s = "D@" + isLoaded + "@" + task.getDescription() + "@" + ((Deadline) task).getBy();
                    output.write(s);
                }
                output.newLine();
            }
            output.close();
        } catch (IOException e) {
            throw new StorageException("Error writing to " + filePath + " when persisting data", e);
        }
        return true;
    }

    /**
     * Finds a keyword in the asset file
     * and returns the corresponding tasks found in
     * a taskList.
     *
     * @param keywords keywords to search for.
     * @return TaskList containing the tasks with the keywords
     * @throws StorageException Throws exception from reading asset file.
     */
    public TaskList find(String... keywords) throws StorageException {
        TaskList tasksFound = new TaskList();
        try {
            Scanner scanner = new Scanner(data);
            while (scanner.hasNextLine()) {
                String item = scanner.nextLine();
                for (String keyword : keywords) {
                    if (item.contains(keyword)) {
                        tasksFound.add(dataToTask(item));
                    }
                }
            }
        } catch (IOException e) {
            throw new StorageException("Error was encountered when reading asset file: " + filePath, e);
        }
        return tasksFound;
    }

    /**
     * Converts data to Task.
     *
     * @param data data stored in the asset file.
     * @return Task task created from data.
     */
    private Task dataToTask(String data) {
        assert data != null : "data cannot be null!";

        String[] dataTokens = data.split("@", 4);
        Task task;
        switch (dataTokens[0]) {
        case "T":
            task = new ToDo(dataTokens[2]);
            break;
        case "D":
            task = new Deadline(dataTokens[2], LocalDateTime.parse(dataTokens[3]));
            break;
        default:
            task = new Event(dataTokens[2], dataTokens[3]);
        }
        if (dataTokens[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }
}
