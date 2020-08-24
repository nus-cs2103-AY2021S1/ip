import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDos;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Made to store and retrieve the data in duke.txt
 * A storage object will have a particular filepath
 * from which it will read and write the tasklist to
 */
public class Storage {
    /**
     * Filepath refers to the path of the file which we will interact with
     */
    private String filePath;
    /**
     * The tasklist which will contain the data from the file and which will
     * be updated based on user input
     */
    private TaskList list;
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Gets the filepath and uses this filepath to find the file which
     * initialize the tasklist.
     * @param filepath A string to enable us to access the file to get the tasklist
     */
    public Storage (String filepath) {
        this.filePath = filepath;
        try {
            FileReader file = new FileReader(filepath);
            BufferedReader reader = new BufferedReader(file);
            String line;
            while ((line = reader.readLine()) != null){
                tasks.add(getTask(line));
            }
            file.close();
        } catch (IOException e){
            tasks = new ArrayList<>();
        }
    }

    /**
     * Reads the file at the given path and parses the Strings inside into task objects
     * which can be added to the tasklist
     * @param line
     * @return A Task object which could be a todo, deadline or event
     */
    private Task getTask(String line){
        Task task;
        if (line.charAt(1) == 'T'){
            task = new ToDos(line.substring(6));
        } else if (line.charAt(1) == 'D'){
            int index = line.indexOf("|");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime date = LocalDateTime.parse(line.substring(index + 1).trim(), formatter);
            task = new Deadline(line.substring(6, index), date);
        } else {
            int index = line.indexOf("|");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime date = LocalDateTime.parse(line.substring(index + 1).trim(), formatter);
            task = new Event(line.substring(6, index), date);
        }
        if (line.charAt(4) == '✓'){
            task.updateStatus();
        }
        return task;
    }

    public TaskList load(){
        this.list = new TaskList(this.tasks) ;
        return this.list;
    }

    /**
     * Writes the tasklist into the file at the filepath of this storage object in a format which
     * can be easily parsed when the chatbot is rerun. If the file or folder doesn't exist then,
     * a new file will be made
     */
    public void save(){
        try {
            String folderPath = "Data";
            File directory = new File(folderPath);
            if (!directory.isDirectory()){
                File folder = new File(folderPath);
                if (!folder.mkdir()){
                    System.out.println("cannot make a folder");
                }
            }
            File file = new File(this.filePath);
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(list.save());
            writer.close();
        } catch (IOException e) {
            System.out.println("No File found");
        }
    }
}
