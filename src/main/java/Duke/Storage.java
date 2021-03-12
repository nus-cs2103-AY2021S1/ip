package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * Represents a Storage where the tasks of the user are stored in.
 */
public class Storage {
    private File mainTasksFile;
    private File archivedTasksFile;

    /**
     * Creates a Storage instance containing the filepath leading to it.
     *
     * @param unarchivedTasksFilePath Filepath leading to Storage.
     */
    public Storage(String unarchivedTasksFilePath, String archivedTasksFilePath) {
        this.mainTasksFile = new File(unarchivedTasksFilePath);
        this.archivedTasksFile = new File(archivedTasksFilePath);
    }

    /**
     * Loads the data from the file in the filepath into an ArrayList.
     *
     * @return An ArrayList containing data of the user's previous tasks inputted into Duke.
     * @throws DukeException Thrown when it couldn't locate the file in the specified file path.
     */
    public ArrayList<Task> load(boolean isArchive) throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File fileToLoadFrom;
            if (isArchive) {
                fileToLoadFrom = this.archivedTasksFile;
            } else {
                fileToLoadFrom = this.mainTasksFile;
            }

            fileToLoadFrom.getParentFile().mkdirs();
            fileToLoadFrom.createNewFile();

            Scanner loadedData = new Scanner(fileToLoadFrom);
            while (loadedData.hasNextLine()) {
                String[] taskParts = loadedData.nextLine().split("~");
                assert taskParts.length >= 3 : "Data format of database invalid";
                String identifier = taskParts[0];
                String desc = taskParts[2];
                LocalDateTime timing = taskParts.length == 3 ? null : LocalDateTime.parse(taskParts[3]);
                boolean isDone = Boolean.parseBoolean(taskParts[1]);
                Task savedTask;

                if (identifier.equals("T")) {
                    savedTask = new Todo(desc, isDone);
                } else if (identifier.equals("D")) {
                    savedTask = new Deadline(desc, timing, isDone);
                } else {
                    savedTask = new Event(desc, timing, isDone);
                }

                tasks.add(savedTask);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    /**
     * Adds the task in a recognized format into the Storage file.
     *
     * @param task Task to be added in storage file.
     * @throws DukeException thrown when there is an IOException thrown by FileWriter.
     */
    public void append(Task task, boolean isArchive) throws DukeException {
        File fileToLoadFrom;
        if (isArchive) {
            fileToLoadFrom = this.archivedTasksFile;
        } else {
            fileToLoadFrom = this.mainTasksFile;
        }

        try {
            FileWriter fw = new FileWriter(fileToLoadFrom.getPath(), true);
            fw.write(task.getSavingFormat() + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new DukeException((e.getMessage()));
        }
    }

    /**
     * Overwrite the existing data in the Storage with data from the Tasks present in the TaskList.
     *
     * @param todos TaskList containing Tasks for overwriting the data in Storage.
     * @throws DukeException Thrown when FileWrite throws an IOException.
     */
    public void overwrite(TaskList todos, boolean isArchive) throws DukeException {
        File fileToLoadFrom;
        if (isArchive) {
            fileToLoadFrom = this.archivedTasksFile;
        } else {
            fileToLoadFrom = this.mainTasksFile;
        }

        try {
            FileWriter fw = new FileWriter(fileToLoadFrom.getPath());
            Task todo;

            for (int i = 0; i < todos.size(); i++) {
                todo = todos.get(i);
                fw.write(todo.getSavingFormat() + System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
