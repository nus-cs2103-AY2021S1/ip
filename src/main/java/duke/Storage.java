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
        String[] info = encodedline.split("\\|");
        String type = info[0].trim();
        boolean done = Boolean.parseBoolean(info[1]);
        String description = info[2].trim();
        String time = "";
        
        switch (type) {
        case "Todo":
            return new Todo(description, done);
            
        case "Deadline":
            time = info[3].trim();
            return new Deadline (description, time, done);
            
        case "Event":
            time = info[3].trim();
            return new Event(description, time, done);
            
        default:
            throw new DukeException();
        }
    }
}
