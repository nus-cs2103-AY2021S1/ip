package main.java.farrell.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Encapsulates behavior for reading and writing to files.
 */
public class DataManager {
    /** The file path to save and load data from */
    final String filePath = "data/data.txt";

    /**
     * Writes the contents of a list of tasks to a file.
     *
     * @param taskList The list of tasks to save.
     * @throws DukeException If a problem is encountered when writing to the file.
     */
    public void save(TaskList taskList) throws DukeException {
        List<Task> tasks = taskList.getAllTasks();
        try {
            File dataFile = new File(filePath);
            dataFile.createNewFile();
            FileWriter fw = new FileWriter(dataFile);
            String stringToWrite = "";
            for (Task task : tasks) {
                switch (task.taskType) {
                case TODO:
                    ToDo todo = (ToDo) task;
                    stringToWrite = todo.taskType.name() + "|" +
                            (todo.isDone ? "true" : "false") + "|" +
                            todo.description;
                    break;
                case EVENT:
                    Event event = (Event) task;
                    stringToWrite = event.taskType.name() + "|" +
                            (event.isDone ? "true" : "false") + "|" +
                            event.description + "|" +
                            event.time.format(DateTimeFormatter.ISO_LOCAL_DATE);
                    break;
                case DEADLINE:
                    Deadline deadline = (Deadline) task;
                    stringToWrite = deadline.taskType.name() + "|" +
                            (deadline.isDone ? "true" : "false") + "|" +
                            deadline.description + "|" +
                            deadline.time.format(DateTimeFormatter.ISO_LOCAL_DATE);
                    break;
                }
                fw.write(stringToWrite + "\n");
            }
            fw.close();
        } catch (IOException exception) {
            throw new DukeException("Could not save file!\nReason: " + exception.getMessage());
        }
    }

    /**
     * Loads task data from a file.
     *
     * @return A list of tasks.
     * @throws DukeException If a problem is encountered when opening the file
     */
    public TaskList load() throws DukeException {
        File dataFile = new File(filePath);

        if(!dataFile.exists()) {
            return new TaskList();
        }

        try {
            Scanner sc = new Scanner(dataFile);
            List<Task> taskList = new ArrayList<>();

            while (sc.hasNextLine()) {
                String dataLine = sc.nextLine();
                String[] data = dataLine.split("\\|");
                TaskType taskType = TaskType.enumFromString(data[0]);
                try {
                    switch (taskType) {
                    case TODO:
                        taskList.add(new ToDo(data[2], Boolean.parseBoolean(data[1])));
                        break;
                    case EVENT:
                        taskList.add(new Event(data[2], Boolean.parseBoolean(data[1]), LocalDate.parse(data[3])));
                        break;
                    case DEADLINE:
                        taskList.add(new Deadline(data[2], Boolean.parseBoolean(data[1]), LocalDate.parse(data[3])));
                        break;
                    default:
                        throw new DukeException("Invalid data format provided!");
                    }
                } catch (IndexOutOfBoundsException exception) {
                    throw new DukeException("Not enough data provided!");
                }

            }
            return new TaskList(taskList);
        } catch (FileNotFoundException exception) {
            throw new DukeException("File could not be loaded!");
        }
    }
}
