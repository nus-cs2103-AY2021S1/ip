package duke;

import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a <code>Storage</code> object that deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String dataDirectoryPath;
    private String savedFilePath;

    public Storage(String dataDirectoryPath, String savedFilePath) {
        this.dataDirectoryPath = dataDirectoryPath;
        this.savedFilePath = savedFilePath;
    }

    /**
     * Returns a List of <code>Task</code> loaded from the file containing the saved tasks.
     *
     * @return a List of <code>Task</code> loaded from the file containing the saved tasks.
     * @throws DukeException If failed to load the file containing the saved tasks.
     */
    public List<Task> loadSavedFile() throws IOException {

        File directory = new File(dataDirectoryPath);
        directory.mkdir(); // create the directory if it not existed

        File savedFile = new File(savedFilePath);
        List<Task> tasks = new ArrayList<>();
        try {
            savedFile.createNewFile();
            Scanner sc = new Scanner(savedFile);

            while (sc.hasNext()) {
                String taskString = sc.nextLine();
                String[] taskComponents = taskString.split(" - ");
                String taskType = taskComponents[0];
                boolean isDone = taskComponents[1].equals("1");
                String description = taskComponents[2];

                Task toAdd;
                switch (taskType) {
                    case "T":
                        toAdd = new ToDo(description, isDone);
                        break;
                    case "D":
                        toAdd = new Deadline(description, LocalDateTime.parse(taskComponents[3], DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")), isDone);
                        break;
                    case "E":
                        toAdd = new Event(description, LocalDateTime.parse(taskComponents[3], DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")), isDone);
                        break;
                    default:
                        throw new IllegalArgumentException("Saved file contains incorrect format");
                }
                tasks.add(toAdd);
            }
        } catch (IOException e) {
            throw e;
        }
        return tasks;
    }

    /**
     * Writes all the <code>Task</code> inside <code>tasks</code> into the destined file.
     *
     * @throws DukeException If failed to write the tasks into the destined file.
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(savedFilePath);
        for (Task task : tasks.all()) {
            fw.write(task.simplifiedTaskString() + "\n");
        }
        fw.close();
    }
}
