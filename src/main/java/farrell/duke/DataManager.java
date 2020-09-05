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
    private final String fileName = "data.txt";

    private final String directory = "data/";

    /**
     * Writes the contents of a list of tasks to a file.
     *
     * @param taskList The list of tasks to save.
     * @throws DukeException If a problem is encountered when writing to the file.
     */
    public void save(TaskList taskList) throws DukeException {
        List<Task> tasks = taskList.getAllTasks();
        try {
            File dataDirectory = new File(directory);
            if(!dataDirectory.exists()) {
                dataDirectory.mkdir();
            }

            File dataFile = new File(directory + fileName);
            dataFile.createNewFile();

            FileWriter fw = new FileWriter(dataFile);
            for (Task task : tasks) {
                String data = convertTaskToData(task);
                fw.write(data + "\n");
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
        File dataFile = new File(directory + fileName);

        if (!dataFile.exists()) {
            return new TaskList();
        }

        try {
            Scanner sc = new Scanner(dataFile);
            List<Task> taskList = new ArrayList<>();

            while (sc.hasNextLine()) {
                String dataLine = sc.nextLine();
                Task task = convertDataToTask(dataLine);
                taskList.add(task);
            }
            return new TaskList(taskList);
        } catch (FileNotFoundException exception) {
            throw new DukeException("File could not be loaded!");
        }
    }

    private String convertTaskToData(Task task) throws DukeException {
        switch (task.getTaskType()) {
        case TODO:
            ToDo todo = (ToDo) task;
            return convertTodoToData(todo);
        case EVENT:
            Event event = (Event) task;
            return convertEventToData(event);
        case DEADLINE:
            Deadline deadline = (Deadline) task;
            return convertDeadlineToData(deadline);
        default:
            throw new DukeException("Invalid Task Type Encountered.");
        }
    }

    private String convertTodoToData(ToDo todo) {
        return todo.getTaskType().name() + "|"
                + (todo.isDone() ? "true" : "false") + "|"
                + todo.getDescription();
    }

    private String convertEventToData(Event event) {
        return event.getTaskType().name() + "|"
                + (event.isDone() ? "true" : "false") + "|"
                + event.getDescription() + "|"
                + event.getTime().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    private String convertDeadlineToData(Deadline deadline) {
        return deadline.getTaskType().name() + "|"
                + (deadline.isDone() ? "true" : "false") + "|"
                + deadline.getDescription() + "|"
                + deadline.getTime().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    private Task convertDataToTask(String dataLine) throws DukeException {
        String[] data = dataLine.split("\\|");
        TaskType taskType = TaskType.enumFromString(data[0]);
        try {
            switch (taskType) {
            case TODO:
                return new ToDo(data[2], Boolean.parseBoolean(data[1]));
            case EVENT:
                return new Event(data[2],
                        Boolean.parseBoolean(data[1]),
                        LocalDate.parse(data[3]));
            case DEADLINE:
                return new Deadline(data[2],
                        Boolean.parseBoolean(data[1]),
                        LocalDate.parse(data[3]));
            default:
                throw new DukeException("Invalid data format provided!");
            }
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException("Not enough data provided!");
        }
    }
}
