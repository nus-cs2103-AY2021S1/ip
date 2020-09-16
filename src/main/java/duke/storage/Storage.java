package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;


public class Storage {
    private static String dukeFilePath;

    /**
     * Check whether duke.txt exists and read lines from that file.
     *
     * @param taskList TaskList that manages tasks.
     */
    public static void loadFromFile(TaskList taskList) throws DukeException {
        try {
            File file = setupStorage();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String row = sc.nextLine();
                String[] data = row.split(" {2}");
                memoryProcessor(data, taskList);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Error loading file from storage.");
        }
    }

    /**
     * Returns file for storage.
     *
     * @return File.
     * @throws DukeException Duke exception.
     */
    public static File setupStorage() throws DukeException {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();

        Path path = Paths.get(s, "data", "duke.txt");
        File file = new File(String.valueOf(path));
        try {
            if (!file.exists()) {
                File directory = new File(file.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("Error loading from storage");
        }
        return file;
    }


    /**
     * Adds the tasks from data to taskList.
     *
     * @param data Task info in data array.
     * @param taskList TaskList that manages tasks.
     */
    public static void memoryProcessor(String[] data, TaskList taskList) throws DukeException {
        switch (data[0]) {
        case "T":
            ToDo todo = new ToDo(data[2]);
            if (data[1].equals("0")) {
                todo.markAsDone();
            }
            if (data.length == 4) {
                String tag = data[3];
                todo.addTag(tag);
            }
            taskList.addTask(todo);
            break;
        case "D":
            Deadline deadline = new Deadline(data[2], data[3]);
            if (data[1].equals("0")) {
                deadline.markAsDone();
            }
            if (data.length == 5) {
                String tag = data[4];
                deadline.addTag(tag);
            }
            taskList.addTask(deadline);
            break;
        case "E":
            Event event = new Event(data[2], data[3]);
            if (data[1].equals("0")) {
                event.markAsDone();
            }
            if (data.length == 5) {
                String tag = data[4];
                event.addTag(tag);
            }
            taskList.addTask(event);
            break;
        default:
            throw new DukeException("Error loading from storage");
        }
    }

    /**
     * Stores the newly updated taskList to duke.txt.
     *
     * @param taskList TaskList that manages tasks.
     */
    public static void writeToFile(TaskList taskList) throws DukeException {
        try {
            File file = setupStorage();
            FileWriter writer = new FileWriter(file);
            for (Task task : taskList.getTaskList()) {
                String[] data = task.taskToArray();
                if (data.length == 3) {
                    writer.write(data[0] + "  " + data[1] + "  " + data[2] + System.lineSeparator());
                } else if (data.length == 4) {
                    writer.write(data[0] + "  " + data[1] + "  " + data[2] + "  " + data[3] + System.lineSeparator());
                } else if (data.length == 5) { // have tag
                    writer.write(data[0] + "  " + data[1] + "  " + data[2] + "  "
                            + data[3] + "  " + data[4] + System.lineSeparator());
                }
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Error loading from storage");
        } catch (DukeException e) {
            throw e;
        }
    }
}
