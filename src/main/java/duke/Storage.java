package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    
    private final String filePath;
    
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks that have been stored in the data file
     * 
     * @return List of tasks
     * @throws DukeException If file is not found
     */
    public ArrayList<Task> load() throws DukeException{
        
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File f = new File("data/data.txt");
            Scanner diskScanner = new Scanner(f);
                    
            while (diskScanner.hasNext()) {
                String next = diskScanner.nextLine();
                tasks.add(decodeTask(next));
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        
        return tasks;
    }

    /**
     * Stores tasks in the data file
     * 
     * @param list List of tasks to be stored
     */
    public void writeToDataFile(TaskList list) {
        try {
            FileWriter fw = new FileWriter(filePath);
            
            for (int i = 0; i < list.size(); i++) {
                String write = encodeTask (list.get(i));
                fw.write (write);
            }
            fw.close();
            
        } catch (IOException e) {
            System.out.println ("Something went wrong: " + e.getMessage());
        }
    }

    private String encodeTask(Task task) {
        String encodedTask = "";
        encodedTask += (task.getType() + " | ");
        encodedTask += (task.getDone() + " | ");
        encodedTask += (task.getDescription());
        
        if ((task.getType().equals("Event")) || (task.getType().equals("Deadline"))) {
            encodedTask += (" | " + task.getTime());
        }
        
        encodedTask += "\n";
        return encodedTask;
    }
    
    
    private Task decodeTask (String encodedline) throws DukeException {
        String[] parts = encodedline.split("\\|");
        String type = parts[0].trim();
        boolean isDone = Boolean.parseBoolean(parts[1]);
        String description = parts[2].trim();
        String time = "";
        
        switch (type) {
        case "Todo":
            return new Todo(description, isDone);
            
        case "Deadline":
            time = parts[3].trim();
            return new Deadline (description, time, isDone);
            
        case "Event":
            time = parts[3].trim();
            return new Event(description, time, isDone);
            
        default:
            throw new DukeException();
        }
    }
}
