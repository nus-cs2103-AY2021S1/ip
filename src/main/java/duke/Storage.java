package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;



/**
 * Encapsulates methods and information that relate to file operations
 * and storage of tasks.
 */
public class Storage {

    /** Default directory which will contain the file with the stored tasks. **/
    private static final String DEFAULT_DIRECTORY = "data";
    /** Default path of the file which will store the tasks. **/
    private static final String DEFAULT_FILE_LOCATION = "data/duke.txt";

    /**
     * Retrieves the list of tasks stored in the designated file
     * and stores them in a TaskList object.
     *
     * @return TaskList containing the tasks retrieved from the file.
     */
    public TaskList readFile() {
        List<Task> tasks = new ArrayList<>();
        try {
            File dataDirectory = new File(Storage.DEFAULT_DIRECTORY);
            // make a data directory if the directory does not exist
            dataDirectory.mkdir();

            File dataFile = new File(Storage.DEFAULT_FILE_LOCATION);
            // create an empty file to store the tasks if the file does not exist
            dataFile.createNewFile();
            assert dataFile.exists() : "data file must exist";

            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNextLine()) {
                String[] taskData = scanner.nextLine().split(" \\|");
                if (taskData[0].equals("T")) {
                    Task todo = retrieveTodo(taskData);
                    tasks.add(todo);
                } else if (taskData[0].equals("D")) {
                    Task deadline = retrieveDeadline(taskData);
                    tasks.add(deadline);
                } else if (taskData[0].equals("E")) {
                    Task event = retrieveEvent(taskData);
                    tasks.add(event);
                }
            }
            scanner.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new TaskList(tasks);
    }

    /**
     * Retrieves the todo task from its string representation stored in the designated file.
     *
     * @param todoData String array containing the todo task details.
     * @return Todo task stored in the designated file.
     */
    public Todo retrieveTodo(String[] todoData) {
        Todo todo = new Todo(todoData[2].trim());
        String tag = todoData[3].trim();
        if (todoData[1].equals(" 1")) {
            todo.markAsDone();
        }
        if (!tag.isBlank()) {
            todo.setTaskTag(tag);
        }
        return todo;
    }

    /**
     * Retrieves the deadline task from its string representation stored in the designated file.
     *
     * @param deadlineData String array containing the deadline task details.
     * @return Deadline task stored in the designated file.
     */
    public Deadline retrieveDeadline(String[] deadlineData) {
        String dateTime = deadlineData[4].trim();
        String tag = deadlineData[3].trim();
        String[] dateTimeArray = dateTime.split(" ");
        LocalDate taskDate = LocalDate.parse(dateTimeArray[0]);
        LocalTime taskTime = LocalTime.parse(dateTimeArray[1]);
        Deadline deadline = new Deadline(deadlineData[2].trim(), taskDate, taskTime);
        if (deadlineData[1].equals(" 1")) {
            deadline.markAsDone();
        }
        if (!tag.isBlank()) {
            deadline.setTaskTag(tag);
        }
        return deadline;
    }

    /**
     * Retrieves the event task from its string representation stored in the designated file.
     *
     * @param eventData String array containing the event task details.
     * @return Event task stored in the designated file.
     */
    public Event retrieveEvent(String[] eventData) {
        String dateTime = eventData[4].trim();
        String tag = eventData[3].trim();
        String[] dateTimeArray = dateTime.split(" ");
        LocalDate taskDate = LocalDate.parse(dateTimeArray[0]);
        LocalTime taskTime = LocalTime.parse(dateTimeArray[1]);
        Event event = new Event(eventData[2].trim(), taskDate, taskTime);
        if (eventData[1].equals(" 1")) {
            event.markAsDone();
        }
        if (!tag.isBlank()) {
            event.setTaskTag(tag);
        }
        return event;
    }

    /**
     * Stores the list of tasks, in the TaskList object, into the designated file.
     *
     * @param tasks TaskList object containing a list of tasks to be saved in the designated file.
     * @throws DukeException If the tasks could not be saved into the designated file.
     */
    public void saveToFile(TaskList tasks) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(Storage.DEFAULT_FILE_LOCATION);
            for (int i = 0; i < tasks.getListSize(); i++) {
                Task task = tasks.getTask(i);
                String taskFileString = task.convertTaskToFileString();
                fileWriter.write(taskFileString + "\n");
            }
            fileWriter.close();
        } catch (IOException ex) {
            String error = "The task file could not be updated.";
            throw new DukeException(error);
        }
    }

}
