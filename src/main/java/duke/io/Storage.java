package duke.io;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.DukeException;
import duke.task.Event;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage to store user's task list.
 */
public class Storage {
    
    private String filePath;
    private File file;
    private Scanner sc;
    private Layout layout;

    /**
     * Constructs Storage object.
     * Creates  duke.txt inside a data folder if file or directory not found.
     * 
     * @param filePath Path of data file to store task list.
     */
    public Storage(String filePath) {
        
        //Find text file inside data folder
        this.filePath = filePath;
        file = new File("filePath");
        layout = new Layout();
        
        try {
            sc = new Scanner(file);
            
        } catch (FileNotFoundException e) {
            
            //Create data folder
            file = new File("data");
            file.mkdir();
            try {
                
                //Create text file
                file = new File("data/duke.txt");
                file.createNewFile();
                
                sc = new Scanner(file);
                
            } catch (IOException i) {
                DukeException d = new DukeException("Unable to create file");
                layout.print(d.getMessage());
            }
        }
    }

    /**
     * Load task list from data file and store in ArrayList.
     * 
     * @return ArrayList<Task> initialised with tasks from the previous use.
     */
    public ArrayList<Task> load() {
            Task task;
            ArrayList<Task> tasks = new ArrayList<>();
            
            while(sc.hasNextLine()) {
                String [] arr = sc.nextLine().split(" \\| ");
                String type = arr[0];
                String isDone = arr[1];
                String description = arr[2];
                switch(type) {
                    case "D":
                        String by = arr[3];
                        task = new Deadline(description, by);
                        break;
                    case "E":
                        String eventTime = arr[3];
                        task = new Event(description, eventTime);
                        break;
                    default: //case "T"
                        task = new Todo(description);
                        break;
                }
                if (isDone.equals("0")) {
                    task.markDone();
                }
                tasks.add(task);
            }
        return tasks;
    }

    /**
     * Store updated task list in data file.
     * 
     * @param tasks Tasks to store.
     */
    public void writeFile(ArrayList<Task> tasks) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(filePath);
            String newData = "";
            for(int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                //newData += "\n" + duke.task.toSave();
                newData += i == 0 ? task.toSave() : "\n" + task.toSave();
            }
            fileWriter.write(newData);
            fileWriter.close();
        } catch (IOException e) {
            DukeException d = new DukeException(e.getMessage());
            layout.print(d.getMessage());
        }
    }
}
