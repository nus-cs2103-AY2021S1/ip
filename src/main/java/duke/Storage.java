package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeFileNotFoundException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Takes care of file related operation.
 */
public class Storage {
    private static final String TODO = "T";
    private static final String DEADLINE = "D";
    private static final String EVENT = "E";
    private File file;

    /**
     * Creates a new instance of Storage.
     * Creates a file directory if it is missing.
     * Creates a file if it does not exist.
     *
     * @param filePath Path of the file.
     */
    public Storage(String filePath) {
        try {
            Path directory = Paths.get(filePath).getParent();
            if (!Files.exists(directory)) {
                Files.createDirectory(directory);
            }
            file = new File(filePath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reads the tasks in a file and convert them into an ArrayList.
     *
     * @return ArrayList of Task.
     * @throws DukeFileNotFoundException If file is not found.
     */
    public ArrayList<Task> load() throws DukeFileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] task = sc.nextLine().split("\\|");
                switch (task[0].trim()) {
                case TODO:
                    taskList.add(readTodo(task));
                    break;
                case DEADLINE:
                    taskList.add(readDeadline(task));
                    break;
                case EVENT:
                    taskList.add(readEvent(task));
                    break;
                default:
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeFileNotFoundException();
        }
        return taskList;
    }

    /**
     * Creates a ToDo using the task details.
     *
     * @param task Details of task.
     * @return ToDo.
     */
    private ToDo readTodo(String[] task) {
        ToDo toDo = new ToDo(task[2].trim());
        if (task[1].equals("1")) {
            toDo.doneTask();
        }
        return toDo;
    }

    /**
     * Creates a Deadline using the task details.
     *
     * @param task Details of task.
     * @return Deadline.
     */
    private Deadline readDeadline(String[] task) {
        Deadline deadline = new Deadline(task[2].trim(), task[3].trim());
        if (task[1].equals("1")) {
            deadline.doneTask();
        }
        return deadline;
    }

    /**
     * Creates a Event using the task details.
     *
     * @param task Details of task.
     * @return Event.
     */
    private Event readEvent(String[] task) {
        Event event = new Event(task[2].trim(), task[3].trim());
        if (task[1].equals("1")) {
            event.doneTask();
        }
        return event;
    }

    /**
     * Saves the TaskList to a file.
     *
     * @param taskList ArrayList of Task.
     */
    public void save(ArrayList<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(file.getPath());
            fw.write(iterateTasks(taskList));
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Iterates through the list of tasks and combine them into a string.
     *
     * @param taskList ArrayList of tasks.
     * @return String containing all the task and its details.
     */
    private String iterateTasks(ArrayList<Task> taskList) {
        StringBuilder content = new StringBuilder();
        taskList.forEach(task -> {
            if (task instanceof ToDo) {
                String taskDetails = String.format("T | %d | %s", task.isTaskDone() ? 1 : 0, task.getDescription());
                content.append(taskDetails).append("\n");
            } else if (task instanceof Deadline) {
                String taskDetails = String.format("T | %d | %s |%s",
                        task.isTaskDone() ? 1 : 0, task.getDescription(), ((Deadline) task).getDate());
                content.append(taskDetails).append("\n");
            } else {
                String taskDetails = String.format("T | %d | %s |%s",
                        task.isTaskDone() ? 1 : 0, task.getDescription(), ((Event) task).getAt());
                content.append(taskDetails).append("\n");
            }
        });
        return content.toString();
    }
}
