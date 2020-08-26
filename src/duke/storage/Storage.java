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

public class Storage {
    private static final String DEADLINE_TEXT_SEPARATOR = "by: ";
    private static final String EVENT_TEXT_SEPARATOR = "at: ";
    private Path filePath;
    
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }
    
    public void save(TaskList tasks, Ui ui) throws SaveToStorageErrorException {
        List<String> taskListText = new ArrayList<>();
        
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
    
    public TaskList load() throws StorageException {
        if (!Files.exists(filePath) || !Files.isRegularFile(filePath)) {
            try {
                if (containsDirectory()) {
                    Files.createDirectories(filePath.getParent());
                }
                
                Files.createFile(filePath);
                return new TaskList(new ArrayList<>());
            } catch (IOException e) {
                throw new InvalidPathException();
            }
        } else {
            try {
                List<Task> savedTaskList = readSavedTaskList(Files.readAllLines(filePath));
                return new TaskList(savedTaskList);
            } catch (IOException e) {
                throw new StorageLoadErrorException();
            }   
        }
    }
    
    private List<Task> readSavedTaskList(List<String> savedTaskListText) {
        List<Task> savedTaskList = new ArrayList<>();
        
        for (String task: savedTaskListText) {
            String[] taskDetails = task.split(" ", 2);
            
            String taskIndicator = taskDetails[0];
            String taskDescription = taskDetails[1];

            if (taskIndicator.contains(TaskList.TODO_INDICATOR)) {
                Todo todo = new Todo(taskDescription);

                parseIsDoneStatus(todo, taskIndicator);

                savedTaskList.add(todo);
            
            } else if (taskIndicator.contains(TaskList.DEADLINE_INDICATOR)) {
                String[] deadlineDescriptionAndDate = parseTaskText(taskDescription, TaskList.DEADLINE_INDICATOR);
                
                String deadlineDescription = deadlineDescriptionAndDate[0];
                String deadlineDate = deadlineDescriptionAndDate[1];
                
                Deadline deadline = new Deadline(deadlineDescription, deadlineDate);

                parseIsDoneStatus(deadline, taskIndicator);


                savedTaskList.add(deadline);
            
            } else if (taskIndicator.contains(TaskList.EVENT_INDICATOR)) {
                String[] eventDescriptionAndDate = parseTaskText(taskDescription, TaskList.EVENT_INDICATOR);

                String eventDescription = eventDescriptionAndDate[0];
                String eventDate = eventDescriptionAndDate[1];
                
                Event event = new Event(eventDescription, eventDate);
                
                parseIsDoneStatus(event, taskIndicator);
                
                savedTaskList.add(event);
            }
        }
        
        return savedTaskList;
    }
    
    private boolean containsDirectory() {
        return filePath.toString().contains("\\");
    }

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

    private static void parseIsDoneStatus(Task task, String taskIndicator) {
        if (taskIndicator.contains(Task.TICK)) {
            task.markAsDone();
        }
    }
}
