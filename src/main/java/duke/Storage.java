package duke;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;

/**
 * Deals with reading and saving user's task list from/into the file.
 */
public class Storage {
    private final String filePath;

    /**
     * Storage class construction.
     *
     * @param filePath A string of file path to store the task list.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the user's task list from the file.
     *
     * @return An ArrayList<Task> of the task list.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (file.exists()) {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String lineData = bufferedReader.readLine();
                while (lineData != null) {
                    String[] lineSegment = lineData.split(" \\| ");
                    boolean isDone = lineSegment[1].equals("1")
                            ? true
                            : false;

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
                    }
                    lineData = bufferedReader.readLine();
                }
                bufferedReader.close();
                fileReader.close();
            }
        } catch (IOException e) {
            Warnings.invalidFileInput(e);
        }
        return tasks;
    }

    /**
     * Saves the user's task list into the file.
     *
     * @param tasklist The overall user's task list.
     */
    public void writeTasks(TaskList tasklist) {
        try {
            ArrayList<Task> tasks = tasklist.getTaskList();
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            FileWriter fileWriter;

            fileWriter = new FileWriter(file, false);

            for (Task task : tasks) {
                fileWriter.write(task.writeToFile() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            Warnings.invalidFileOutput(e);
        }
    }
}
