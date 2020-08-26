import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private Path filePath;
    
    Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }
    
    public void save(TaskList tasks, Ui ui) throws SaveToStorageErrorException {
        List<String> taskListText = new ArrayList<>();
        
        for (int i = 0; i < tasks.totalNumberOfTasks(); i++) {
            Task task = tasks.getTask(i);
            taskListText.add(task.toString());
        }
        
        try {
            System.out.println(filePath);
            Files.write(filePath, taskListText);
            ui.showSuccessfullySavedMessage();
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

                Parser.parseIsDoneStatus(todo, taskIndicator);

                savedTaskList.add(todo);
            
            } else if (taskIndicator.contains(TaskList.DEADLINE_INDICATOR)) {
                String[] deadlineDescriptionAndDate = Parser.parseTaskText(taskDescription, TaskList.DEADLINE_INDICATOR);
                
                String deadlineDescription = deadlineDescriptionAndDate[0];
                String deadlineDate = deadlineDescriptionAndDate[1];
                
                Deadline deadline = new Deadline(deadlineDescription, deadlineDate);

                Parser.parseIsDoneStatus(deadline, taskIndicator);


                savedTaskList.add(deadline);
            
            } else if (taskIndicator.contains(TaskList.EVENT_INDICATOR)) {
                String[] eventDescriptionAndDate = Parser.parseTaskText(taskDescription, TaskList.EVENT_INDICATOR);

                String eventDescription = eventDescriptionAndDate[0];
                String eventDate = eventDescriptionAndDate[1];
                
                Event event = new Event(eventDescription, eventDate);
                
                Parser.parseIsDoneStatus(event, taskIndicator);
                
                savedTaskList.add(event);
            }
        }
        
        return savedTaskList;
    }
    
    private boolean containsDirectory() {
        return filePath.toString().contains("\\");
    }
}
