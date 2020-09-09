package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Deals with reading and saving user's task list from/into the file.
 */
public class Storage {
    private final String filePath;
    private final String archivedFilePath;

    /**
     * Storage class construction.
     *
     * @param filePath A string of file path to store the task list.
     */
    public Storage(String filePath, String archivedFilePath) {
        this.filePath = filePath;
        this.archivedFilePath = archivedFilePath;
    }

    /**
     * Loads the user's task list from the file.
     *
     * @return An ArrayList of the task list.
     */
    public ArrayList<Task> load() {
        assert !filePath.isEmpty() : "Data filePath is missing.";
        return getSavedTasks(filePath);
    }

    private void addDifferentTypeOfTask(ArrayList<Task> tasks, String[] lineSegment, boolean isDone) {
        switch (lineSegment[0]) {
        case "T":
            ToDos.loadTodoTask(lineSegment[2], isDone, tasks);
            break;
        case "D":
            Deadlines.loadDeadlineTask(lineSegment[2], lineSegment[3], isDone, tasks);
            break;
        case "E":
            Events.loadEventTask(lineSegment[2], lineSegment[3], isDone, tasks);
            break;
        default:
            break;
        }
    }

    /**
     * Saves the user's task list into the file.
     *
     * @param taskList The overall user's task list.
     */
    public void writeTasks(TaskList taskList) {
        assert !filePath.isEmpty() : "Data filePath is missing.";
        try {
            ArrayList<Task> tasks = taskList.getTaskList();
            saveUpdatedTaks(tasks, filePath);
        } catch (IOException e) {
            Warnings.getInvalidFileOutputMsg(e);
        }
    }

    /**
     * Loads saved archived tasks.
     *
     * @return An ArrayList contains all previous saved archived tasks.
     */
    public ArrayList<Task> loadArchivedTasks() {
        assert !archivedFilePath.isEmpty() : "Archived data filePath is missing.";
        return getSavedTasks(archivedFilePath);
    }

    private ArrayList<Task> getSavedTasks(String archivedFilePath) {
        ArrayList<Task> archivedTasks = new ArrayList<>();
        try {
            File archivedFile = new File(archivedFilePath);
            if (archivedFile.exists()) {
                FileReader fileReader = new FileReader(archivedFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String lineData = bufferedReader.readLine();
                while (lineData != null) {
                    String[] lineSegment = lineData.split(" \\| ");
                    boolean isDone = lineSegment[1].equals("1")
                            ? true
                            : false;

                    addDifferentTypeOfTask(archivedTasks, lineSegment, isDone);
                    lineData = bufferedReader.readLine();
                }
                bufferedReader.close();
                fileReader.close();
            }
        } catch (IOException e) {
            Warnings.getInvalidFileInputMsg(e);
        }
        return archivedTasks;
    }

    /**
     * Saves the updated archived task list into the file.
     *
     * @param archivedTaskList The updated archived task list.
     */
    public void writeArchivedTasks(ArchivedTaskList archivedTaskList) {
        assert !archivedFilePath.isEmpty() : "Archived data filePath is missing.";
        try {
            ArrayList<Task> archivedTasks = archivedTaskList.getArchivedTaskList();
            saveUpdatedTaks(archivedTasks, archivedFilePath);
        } catch (IOException e) {
            Warnings.getInvalidFileOutputMsg(e);
        }
    }

    private void saveUpdatedTaks(ArrayList<Task> archivedTasks, String archivedFilePath) throws IOException {
        File archivedFile = new File(archivedFilePath);
        archivedFile.getParentFile().mkdirs();
        FileWriter fileWriter;

        fileWriter = new FileWriter(archivedFile, false);

        for (Task task : archivedTasks) {
            fileWriter.write(task.writeToFile() + "\n");
        }
        fileWriter.close();
    }
}
