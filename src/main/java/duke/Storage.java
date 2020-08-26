package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encapsulates data storage into hard disk.
 */
public class Storage {
    File file;

    /**
     * Instantiates a Storage object.
     *
     * @param filepath Directory to the file.
     */
    public Storage(String filepath) {
        this.file = new File(filepath);
    }

    /**
     * Loads the tasks that were saved in the hard drive previously.
     *
     * @return ArrayList of existing tasks.
     * @throws DukeException If error occurs while reading the file.
     */
    ArrayList<Task> loadTasksFromFile() throws DukeException {
        try {
            if (file.exists()) {
                ArrayList<Task> tasksList = new ArrayList<>();
                Scanner sc = new Scanner(file);

                while (sc.hasNextLine()) {
                    String[] data = sc.nextLine().split(" / ", 4);

                    String taskType = data[0];
                    boolean isDone = data[1].equals("1");
                    String description = data[2];

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyy, hh:mm a");

                    if (taskType.equals("T")) {
                        tasksList.add(new Todo(description, isDone));
                    } else if (taskType.equals("E")) {
                        String dateTime = data[3];
                        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
                        tasksList.add(new Event(description, isDone, localDateTime));
                    } else if (taskType.equals("D")) {
                        String dateTime = data[3];
                        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
                        tasksList.add(new Deadline(description, isDone, localDateTime));
                    } else {
                        throw new DukeException("Error reading tasks from file.");
                    }
                }
                return tasksList;
            } else {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return new ArrayList<>();
            }
        } catch (IOException e) {
            throw new DukeException("Error loading tasks from file.");
        }
    }

    /**
     * Writes tasks to file storage.
     *
     * @param tasks TaskList containing the ArrayList of tasks to be written.
     * @throws DukeException If an error occurs while saving the task.
     */
    void saveTasksToFile(TaskList tasks) throws DukeException {
        FileWriter fileWriter;

        try {
            fileWriter = new FileWriter(file);
            for (Task task : tasks.getTasksList()) {
                if (task instanceof Todo) {
                    fileWriter.write("T" + " / " + (task.getIsDone() ? "1" : "0") + " / " + task.getDescription() + System.lineSeparator());
                } else if (task instanceof Event) {
                    fileWriter.write("E" + " / " + (task.getIsDone() ? "1" : "0") + " / " + task.getDescription() + " / " + ((Event) task).formatDateTime() + System.lineSeparator());
                } else if (task instanceof Deadline){
                    fileWriter.write("D" + " / " + (task.getIsDone() ? "1" : "0") + " / " + task.getDescription() + " / " + ((Deadline) task).formatDateTime() + System.lineSeparator());
                } else {
                    throw new DukeException("Error saving task to file.");
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Error occurred while saving the tasks.");
        }
    }

}
