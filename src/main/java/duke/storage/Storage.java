package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Retrieves data from the database and write new data to it.
 */
public class Storage {
    private String filePath;

    /**
     * Creats a new <code>Storage</code> object.
     * @param filePath The path of the file that this object interacts with
     */
    public Storage(String filePath) {
        assert filePath != null : "file path cannot be null";
        this.filePath = filePath;
    }

    /**
     * Read the data from the file at the file path.
     * @return An <code>ArrayList</code> containing the <code>Task</code>s in the database
     * @throws DukeException If there is any unexpected error in the loading process
     */
    public ArrayList<Task> load() throws DukeException {
        assert filePath != null : "file path cannot be null";
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            readFile(file, tasks);
        } catch (FileNotFoundException e) {
            try {
                createFile(file);
            } catch (IOException e1) {
                throw new DukeException("Cannot read file");
            }
        }
        return tasks;
    }

    private void createFile(File file) throws IOException {
        String directoryPath = filePath.substring(0, filePath.length() - 8);
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        file.createNewFile();
    }

    private void readFile(File file, ArrayList<Task> tasks) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String nextTask = sc.nextLine();
            String[] splitted = nextTask.split("\\s+", 3);
            String type = splitted[0];
            boolean isDone = splitted[1].equals("D");
            Task newTask;

            if (type.equals("T")) {
                String description = splitted[2];
                newTask = new ToDo(description);
            } else {
                int timeIdx = splitted[2].indexOf(" /time");
                String description = splitted[2].substring(0, timeIdx);
                String time = splitted[2].substring(timeIdx + 7);
                if (type.equals("D")) {
                    newTask = new Deadline(description, time);
                } else {
                    newTask = new Event(description, time);
                }
            }

            if (isDone) {
                newTask.markAsDone();
            }

            tasks.add(newTask);
        }
        sc.close();
    }

    /**
     * Writes the data to the file at the file path.
     * @param tasks The data to be written
     */
    public void write(TaskList tasks) throws DukeException {
        assert tasks != null : "task list cannot be null";
        assert filePath != null : "file path cannot be null";
        try {
            File f = new File(filePath);
            clearOldData(f);
            writeFile(tasks);
        } catch (IOException e) {
            throw new DukeException("Cannot write file");
        }
    }

    private void clearOldData(File file) throws IOException {
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
    }

    private void writeFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String type;
            String description;
            String time;
            String status;
            description = task.getDescription();
            status = task.isDone() ? "D" : "ND";
            time = task.getTime().equals("") ? "" : "/time " + task.getTime();
            if (task instanceof ToDo) {
                type = "T";
            } else if (task instanceof Deadline) {
                type = "D";
            } else {
                type = "E";
            }
            String dataPresentation = type + " " + status + " " + description + " " + time + "\n";
            fw.write(dataPresentation);
        }
        fw.close();
    }
}
