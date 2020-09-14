package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeInvalidStorageException;
import duke.lists.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Storage to write data to the data file or retrieve data from the file
 */
public class Storage {
    
    private final String filePath;

    /**
     * Constructor for storage class.
     * Creates a new file with the specified filePath if it does not exist
     * 
     * @param filePath filePath to store data
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        try {
            File file = new File(filePath);
            file.getParentFile().mkdir();
            file.createNewFile();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    
    /**
     * Loads the tasks that have been stored in the data file
     * 
     * @return List of tasks
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File("data/data.txt");
            Scanner diskScanner = new Scanner(file);
            
            while (diskScanner.hasNext()) {
                String nextLine = diskScanner.nextLine();
                tasks.add(decodeTask(nextLine));
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            
        } catch (DukeInvalidStorageException e) {
            System.out.println(e.getExceptionMessage());
            tasks = new ArrayList<>();
        }
        return tasks;
    }

    /**
     * Stores tasks in the data file
     * 
     * @param taskList List of tasks to be stored
     */
    public void writeDataToFile(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < taskList.size(); i++) {
                String write = encodeTask (taskList.getTask(i));
                fw.write (write);
            }
            fw.close();
            
        } catch (IOException e) {
            System.out.println ("Something went wrong: " + e.getMessage());
        }
    }

    private String encodeTask(Task task) {
        String encodedTask = "";
        encodedTask += (task.getTypeOfTask() + " | ");
        encodedTask += (task.getDone() + " | ");
        encodedTask += (task.getDescription());
        if (!task.getTypeOfTask().equals("Todo")) {
            encodedTask += (" | " + task.getTime());
        }
        encodedTask += "\n";
        return encodedTask;
    }
    
    
    private Task decodeTask (String encodedLine) throws DukeInvalidStorageException {
        String[] parts = encodedLine.split("\\|");
        String typeOfTask = parts[0].trim();
        boolean isDone = Boolean.parseBoolean(parts[1].trim());
        String description = parts[2].trim();
        
        switch (typeOfTask) {
        case "Todo":
            return new Todo(description, isDone);
        case "Deadline":
            String deadline = parts[3].trim();
            return new Deadline (description, deadline, isDone);
        case "Event":
            String at = parts[3].trim();
            return new Event(description, at, isDone);
        default:
            throw new DukeInvalidStorageException("Storage tasks could not be retrieved");
        }
    }
}
