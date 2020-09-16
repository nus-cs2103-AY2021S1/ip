package duke.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import duke.exception.InvalidPathException;
import duke.exception.SaveToStorageErrorException;
import duke.exception.StorageException;
import duke.exception.StorageLoadErrorException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents the local storage of the program and stores the user's task list.
 * Handles file processing of the saved task list.
 */
public class Storage {
    private static final String TEXT_DEADLINE_SEPARATOR = "by: ";
    private static final String TEXT_EVENT_SEPARATOR = "at: ";

    private final Path filePath;

    /**
     * Constructs a new Storage object that handles file processing of the saved task list.
     *
     * @param filePath File path of where the saved task list will be stored.
     */
    public Storage(String filePath) {
        String directory = System.getProperty("user.dir");
        this.filePath = Path.of(directory, filePath);
    }

    /**
     * Saves the task list in a file specified by the file path.
     *
     * @param tasks Task list to be saved as a file.
     * @throws SaveToStorageErrorException If file path is invalid.
     */
    public void save(TaskList tasks) throws SaveToStorageErrorException {
        List<String> taskListText = new ArrayList<>();

        assert tasks.getTotalNumberOfTasks() > 0;
        // Convert each task into a string
        for (int i = 1; i <= tasks.getTotalNumberOfTasks(); i++) {
            Task task = tasks.getTask(i);
            taskListText.add(task.toString());
        }

        try {
            Files.write(filePath, taskListText);
        } catch (IOException e) {
            throw new SaveToStorageErrorException();
        }
    }

    /**
     * Determines if the indicated file path contains a directory.
     *
     * @return true if the file path contains a directory and returns false otherwise.
     */
    private boolean containsFile() {
        File file = new File(String.valueOf(filePath));
        return file.exists();
    }

    /**
     * Creates a new file at the designated filepath if the file does not already exist.
     *
     * @return A new task list that will save the tasks the user adds.
     * @throws InvalidPathException If the designated filepath is invalid.
     */
    private TaskList createNewFile() throws InvalidPathException {
        try {
            // If a directory is specified in the file path
            if (!containsFile()) {
                // Creates the directory to store the task list file
                Files.createDirectories(filePath.getParent());
            }

            // Create a file to save the task list in the specified file path
            Files.createFile(filePath);
            return new TaskList(new ArrayList<>());
        } catch (IOException e) {
            // Invalid file path
            throw new InvalidPathException();
        }
    }

    /**
     * Retrieves the existing task list file saved and returns the saved the task list.
     *
     * @return The saved task list.
     * @throws StorageLoadErrorException If the designated filepath is invalid.
     */
    private TaskList getExistingFile() throws StorageLoadErrorException {
        try {
            List<Task> savedTaskList = readSavedTaskList(Files.readAllLines(filePath));
            return new TaskList(savedTaskList);
        } catch (IOException e) {
            // Unable to load saved task file
            throw new StorageLoadErrorException();
        }
    }

    /**
     * Loads the saved task list from files specified by the file path.
     *
     * @return Previously saved task list.
     * @throws StorageException If there is an invalid file path.
     */
    public TaskList load() throws StorageException {
        boolean hasNoExistingFile = !Files.exists(filePath) || !Files.isRegularFile(filePath);
        // Task list file has not been created (when the user first runs the program)
        if (hasNoExistingFile) {
            return createNewFile();
        }

        assert Files.exists(filePath);
        // Task list file already exists
        return getExistingFile();
    }

    /**
     * Retrieves the task details from the text stored in the task list file.
     * The task details include the type indicator which indicates whether the task is a todo, deadline or event task,
     * as well as the task description.
     *
     * @param task Text of the task stored in the task list file.
     * @return The task details.
     */
    private String[] getTaskDetails(String task) {
        String[] taskDetails = task.split(" ", 2);
        return taskDetails;
    }

    /**
     * Retrieves the task indicator which indicates whether the task is a todo, deadline or event task from the task
     * details.
     *
     * @param taskDetails Task details consisting of the task indicator and the task description.
     * @return The task indicator indicating whether the task is a todo, deadline or event task.
     */
    private String getTaskIndicator(String[] taskDetails) {
        return taskDetails[0];
    }

    /**
     * Retrieves the task description.
     *
     * @param taskDetails Task details consisting of the task indicator and the task description.
     * @return The task description.
     */
    private String getTaskDescription(String[] taskDetails) {
        return taskDetails[1];
    }

    /**
     * Processes the status of the task stored in the task list and returns a Task object with the
     * appropriate status.
     *
     * @param task Task which status is to be processed.
     * @param taskIndicator A tick if the task is done or a cross if the task is not done.
     */
    private static void parseIsDoneStatus(Task task, String taskIndicator) {
        assert !task.hasDoneStatus();
        if (taskIndicator.contains(Task.STATUS_TICK)) {
            task.markAsDone();
        }

        // Else the task is not done. Do nothing
    }

    /**
     * Adds a todo task stored in the task list file to the List of Tasks.
     *
     * @param taskIndicator Indicates the task is a todo task.
     * @param taskDescription Task description.
     * @param savedTaskList List of Tasks containing the tasks stored in the local storage.
     */
    private void addTodoTask(String taskIndicator, String taskDescription, List<Task> savedTaskList) {
        Todo todo = new Todo(taskDescription);

        // Checks if the task stored in the file is marked as done.
        parseIsDoneStatus(todo, taskIndicator);
        savedTaskList.add(todo);
    }

    /**
     * Creates a new Deadline object containing the description and the date of the deadline.
     *
     * @param deadlineDetails Description and the date of the deadline.
     * @return A new Deadline object.
     */
    private Deadline getDeadlineTask(String[] deadlineDetails) {
        String deadlineDescription = deadlineDetails[0];
        String deadlineDate = deadlineDetails[1];
        return new Deadline(deadlineDescription, deadlineDate);
    }

    /**
     * Creates a new Event object containing the description and the date of event.
     *
     * @param eventDetails Description and the date of the event.
     * @return A new Event object.
     */
    private Event getEventTask(String[] eventDetails) {
        String eventDescription = eventDetails[0];
        String eventDate = eventDetails[1];
        return new Event(eventDescription, eventDate);
    }

    /**
     * Processes the text of either a deadline task or an event task stored in the task list file
     * and returns just the description and date of the deadline or event.
     *
     * @param taskText Text of deadline/event task stored in the task list file.
     * @param typeOfTask Indicates if the task is either a deadline task or an event task.
     * @return Description and date of the deadline or event.
     */
    private static String[] parseTaskText(String taskText, String typeOfTask) {
        assert taskText.contains(" (");
        String[] taskDescriptionAndDate = taskText.split(" \\(", 2);

        if (TaskList.TASK_DEADLINE_INDICATOR.equals(typeOfTask)) {
            String[] date = taskDescriptionAndDate[1].split(TEXT_DEADLINE_SEPARATOR);
            taskDescriptionAndDate[1] = date[1].substring(0, date[1].length() - 1);
        }

        if (TaskList.TASK_EVENT_INDICATOR.equals(typeOfTask)) {
            String[] date = taskDescriptionAndDate[1].split(TEXT_EVENT_SEPARATOR);
            taskDescriptionAndDate[1] = date[1].substring(0, date[1].length() - 1);
        }

        return taskDescriptionAndDate;
    }

    /**
     * Adds a task with a deadline stored in the task list file to the List of Tasks.
     *
     * @param taskIndicator Indicates the task is a task with a deadline.
     * @param taskDescription Task description.
     * @param savedTaskList List of Tasks containing the tasks stored in the local storage.
     */
    private void addDeadlineTask(String taskIndicator, String taskDescription, List<Task> savedTaskList) {
        String[] deadlineDetails = parseTaskText(taskDescription, TaskList.TASK_DEADLINE_INDICATOR);
        Deadline deadline = getDeadlineTask(deadlineDetails);

        // Checks if the task stored in the file is marked as done.
        parseIsDoneStatus(deadline, taskIndicator);

        savedTaskList.add(deadline);
    }

    /**
     * Adds an event task stored in the task list file to the List of Tasks.
     *
     * @param taskIndicator Indicates the task is an event task.
     * @param taskDescription Task description.
     * @param savedTaskList List of Tasks containing the tasks stored in the local storage.
     */
    private void addEventTask(String taskIndicator, String taskDescription, List<Task> savedTaskList) {
        String[] eventDetails = parseTaskText(taskDescription, TaskList.TASK_EVENT_INDICATOR);
        Event event = getEventTask(eventDetails);

        // Checks if the task stored in the file is marked as done.
        parseIsDoneStatus(event, taskIndicator);

        savedTaskList.add(event);
    }

    /**
     * Processes the text of the task stored in the task list file.
     *
     * @param taskDetails Task details consisting of the task indicator and the task description.
     * @param savedTaskList List of Tasks containing the tasks stored in the local storage.
     */
    private void readTask(String[] taskDetails, List<Task> savedTaskList) {
        String taskIndicator = getTaskIndicator(taskDetails);
        String taskDescription = getTaskDescription(taskDetails);

        boolean isTodoTask = taskIndicator.contains(TaskList.TASK_TODO_INDICATOR);
        boolean isDeadlineTask = taskIndicator.contains(TaskList.TASK_DEADLINE_INDICATOR);
        boolean isEventTask = taskIndicator.contains(TaskList.TASK_EVENT_INDICATOR);

        if (isTodoTask) {
            addTodoTask(taskIndicator, taskDescription, savedTaskList);
        } else if (isDeadlineTask) {
            addDeadlineTask(taskIndicator, taskDescription, savedTaskList);
        } else if (isEventTask) {
            addEventTask(taskIndicator, taskDescription, savedTaskList);
        }
    }

    /**
     * Retrieves the text stored in the task list file and returns a List of Tasks of the specified
     * tasks in the task list file.
     *
     * @param savedTaskListText Text stored in the task list file.
     * @return List of Tasks containing the specified tasks in the task list file.
     */
    private List<Task> readTasks(List<String> savedTaskListText) {
        List<Task> savedTaskList = new ArrayList<>();

        for (String task: savedTaskListText) {
            // Split the description of each task to obtain the type of task indicator
            String[] taskDetails = getTaskDetails(task);
            readTask(taskDetails, savedTaskList);
        }

        return savedTaskList;
    }

    /**
     * Reads the text stored in the task list file and returns a List of Tasks of the specified
     * tasks in the task list file.
     *
     * @param savedTaskListText Text stored in the task list file.
     * @return List of Tasks containing the specified tasks in the task list file.
     */
    private List<Task> readSavedTaskList(List<String> savedTaskListText) {
        List<Task> savedTaskList = readTasks(savedTaskListText);
        return savedTaskList;
    }
}
