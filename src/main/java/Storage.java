import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDos;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {
    private String filePath;
    private TaskList list;
    private ArrayList<Task> tasks = new ArrayList<>();
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

    public void save(){
        try {
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
