package duke.storage;

import duke.command.Parser;
import duke.exception.InvalidPathException;
import duke.exception.SaveToStorageErrorException;
import duke.exception.StorageException;
import duke.exception.StorageLoadErrorException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the local storage of the program and stores the user's task list. 
 * Handles file processing of the saved task list.
 */
public class Storage {
    private static final String DEADLINE_TEXT_SEPARATOR = "by: ";
    private static final String EVENT_TEXT_SEPARATOR = "at: ";
    
    private Path filePath;

    /**
     * Creates a new Storage object that handles file processing of the saved task list.
     * 
     * @param filePath File path of where the saved task list will be stored.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Saves the task list in a file specified by the file path.
     * 
     * @param tasks Task list to be saved as a file.
     * @param ui User interface to display if an error occurs when saving the task list.
     * @throws SaveToStorageErrorException If file path is invalid.
     */
    public void save(TaskList tasks, Ui ui) throws SaveToStorageErrorException {
        List<String> taskListText = new ArrayList<>();
        
        // Convert each task into a string
        for (int i = 0; i < tasks.totalNumberOfTasks(); i++) {
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
     * Loads the saved task list from files specified by the file path.
     * 
     * @return Previously saved task list.
     * @throws StorageException If there is an invalid file path.
     */
    public TaskList load() throws StorageException {
        // Task list file has not been created (when the user first runs the program)
        if (!Files.exists(filePath) || !Files.isRegularFile(filePath)) {
            try {
                // If a a directory is specified in the file path
                if (containsDirectory()) {
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
            
        // Task list file already exists
        } else {
            try {
                List<Task> savedTaskList = readSavedTaskList(Files.readAllLines(filePath));
                return new TaskList(savedTaskList);
            } catch (IOException e) {
                // Unable to load saved task file
                throw new StorageLoadErrorException();
            }   
        }
    }

    /**
     * Reads the text stored in the task list file and returns a List of Tasks of the specified 
     * tasks in the task list file.
     * 
     * @param savedTaskListText Text stored in the task list file.
     * @return List of Tasks containg the specified tasks in the task list file.
     */
    private List<Task> readSavedTaskList(List<String> savedTaskListText) {
        List<Task> savedTaskList = new ArrayList<>();
        
        for (String task: savedTaskListText) {
            // Split the description of each task to obtain the type of task indicator
            String[] taskDetails = task.split(" ", 2);
            
            String taskIndicator = taskDetails[0];
            String taskDescription = taskDetails[1];

            if (taskIndicator.contains(TaskList.TODO_INDICATOR)) {
                Todo todo = new Todo(taskDescription);

                // Checks if the task stored in the file is marked as done.
                parseIsDoneStatus(todo, taskIndicator);

                savedTaskList.add(todo);
            
            } else if (taskIndicator.contains(TaskList.DEADLINE_INDICATOR)) {
                String[] deadlineDetails = parseTaskText(taskDescription, TaskList.DEADLINE_INDICATOR);
                
                String deadlineDescription = deadlineDetails[0];
                String deadlineDate = deadlineDetails[1];
                
                Deadline deadline = new Deadline(deadlineDescription, deadlineDate);

                // Checks if the task stored in the file is marked as done.
                parseIsDoneStatus(deadline, taskIndicator);
                
                savedTaskList.add(deadline);
            
            } else if (taskIndicator.contains(TaskList.EVENT_INDICATOR)) {
                String[] eventDetails = parseTaskText(taskDescription, TaskList.EVENT_INDICATOR);

                String eventDescription = eventDetails[0];
                String eventDate = eventDetails[1];
                
                Event event = new Event(eventDescription, eventDate);

                // Checks if the task stored in the file is marked as done.
                parseIsDoneStatus(event, taskIndicator);
                
                savedTaskList.add(event);
            }
        }
        
        return savedTaskList;
    }

    /**
     * Determines if the indicated file path contains a directory.
     * 
     * @return true if the file path contains a directory and returns false otherwise.
     */
    private boolean containsDirectory() {
        return filePath.toString().contains("\\");
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
        String[] taskDescriptionAndDate = taskText.split(" \\(", 2);
        
        if (TaskList.DEADLINE_INDICATOR.equals(typeOfTask)) {
            String[] date = taskDescriptionAndDate[1].split(DEADLINE_TEXT_SEPARATOR);
            taskDescriptionAndDate[1] = date[1].substring(0, date[1].length() -1);
        }

        if (TaskList.EVENT_INDICATOR.equals(typeOfTask)) {
            String[] date = taskDescriptionAndDate[1].split(EVENT_TEXT_SEPARATOR);
            taskDescriptionAndDate[1] = date[1].substring(0, date[1].length() -1);
        }

        return taskDescriptionAndDate;
    }

    /**
     * Processes the status of the task stored in the task list and returns a Task object with the 
     * appropriate status.
     * 
     * @param task Task which status is to be processed.
     * @param taskIndicator A tick if the task is done or a cross if the task is not done.
     */
    private static void parseIsDoneStatus(Task task, String taskIndicator) {
        if (taskIndicator.contains(Task.TICK)) {
            task.markAsDone();
        }
        
        // Else the task is not done. Do nothing
    }
}
