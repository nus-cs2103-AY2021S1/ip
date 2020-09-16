package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePathTasks;
    private String filePathStats;
    private File fileTasks;
    private File fileStats;

    /**
     * Creates a Storage instance.
     *
     * @param filePathTasks A string representing the file path tasks are saved to.
     * @throws DukeException  If unable to create a file in given path.
     */
    public Storage(String filePathTasks, String filePathStats) throws DukeException {
        this.filePathTasks = filePathTasks;
        this.filePathStats = filePathStats;
        fileTasks = new File(this.filePathTasks);
        fileStats = new File(this.filePathStats);
        if (!fileTasks.exists()) {
            fileTasks.getParentFile().mkdirs();
            try {
                fileTasks.createNewFile();
            } catch (IOException e) {
                throw new DukeException("Unable to create task list file...");
            }
        }
        if (!fileStats.exists()) {
            fileStats.getParentFile().mkdirs();
            try {
                fileStats.createNewFile();
                FileWriter fw = new FileWriter(this.fileStats);
                fw.write("0\n0\n0");
                fw.close();
            } catch (IOException e) {
                throw new DukeException("Unable to create statistics file...");
            }
        }
    }

    /**
     * Returns an array list containing tasks from previous session by extracting details
     * of tasks from local file and creating corresponding task objects.
     *
     * @return An array list containing tasks from previous session.
     * @throws DukeException  If there is no file available to load.
     */
    public ArrayList<Task> loadTasks() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(this.fileTasks);
            while (scanner.hasNextLine()) {
                // extract task details eg. type of task, if it is done, task description and date
                String[] currLine = scanner.nextLine().split(" \\| ");
                String typeOfTask = currLine[0];
                // adds task objects to current task list
                switch (typeOfTask) {
                case "T":
                    Task todo = new Todo(currLine[2]);
                    if (Integer.parseInt(currLine[1]) == 1) {
                        todo.doTask();
                    }
                    list.add(todo);
                    break;
                case "D":
                    Task deadline = new Deadline(currLine[2], currLine[3]);
                    if (Integer.parseInt(currLine[1]) == 1) {
                        deadline.doTask();
                    }
                    list.add(deadline);
                    break;
                case "E":
                    Task event = new Event(currLine[2], currLine[3]);
                    if (Integer.parseInt(currLine[1]) == 1) {
                        event.doTask();
                    }
                    list.add(event);
                    break;
                default:
                    throw new DukeException("Trouble encountered when loading file :c");
                }
            }
            return list;
        } catch (FileNotFoundException e) {
            throw new DukeException("Unable to load task list file.. :c");
        }
    }

    /**
     * Returns an array of stats from saved local file.
     *
     * @return An array of stats.
     * @throws DukeException  If there is no file available to load or contents in local file is corrupted.
     */
    public int[] loadStats() throws DukeException {
        int[] stats = new int[3];
        try {
            Scanner scanner = new Scanner(this.fileStats);
            for (int i = 0; i < 3; i++) {
                stats[i] = scanner.nextInt();
            }
            return stats;
        } catch (FileNotFoundException e) {
            throw new DukeException("Unable to load statistics file.. :c");
        } catch (NoSuchElementException e) {
            throw new DukeException("Statistics file is corrupted.. :c");
        }
    }


    /**
     * Saves the tasks in the hard disk when the task list changes.
     *
     * @param taskToAdd A string representing details of the task to be added to the file.
     * @throws DukeException  If unable to edit file.
     */
    public void saveTaskList(String taskToAdd) throws DukeException {
        try {
            // appends task to local saved file
            FileWriter fw = new FileWriter(this.fileTasks, true);
            fw.write(taskToAdd + "\n");
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error editing file!");
        }
    }

    /**
     * Edit the existing tasks in the hard disk when the task list changes.
     *
     * @param newTask A string representing new details of the task already present in the file.
     * @param taskNum Position of task in file.
     * @param delete If the action is to delete the task in file.
     * @throws DukeException  If unable to edit file.
     */
    public void editTaskList(String newTask, int taskNum, boolean delete) throws DukeException {
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(this.filePathTasks)));
            if (delete) {
                fileContent.remove(taskNum - 1);
            } else {
                fileContent.set(taskNum - 1, newTask);
            }
            Files.write(Paths.get(this.filePathTasks), fileContent);
        } catch (IOException e) {
            throw new DukeException("Error editing file!");
        }
    }

    /**
     * Updates the statistics in the hard disk when task items in App changes.
     *
     * @throws DukeException  If unable to edit file.
     */
    public void updateStats(int lineNum) throws DukeException {
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(this.filePathStats)));
            int curr = Integer.parseInt(fileContent.get(lineNum));
            curr++;
            fileContent.set(lineNum, String.valueOf(curr));
            Files.write(Paths.get(this.filePathStats), fileContent);
        } catch (IOException e) {
            throw new DukeException("Error editing file!");
        }
    }

}
