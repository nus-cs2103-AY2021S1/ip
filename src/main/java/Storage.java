import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    private String path; 
    
    public Storage() {
        this.path = "./data/duke.txt";
    }

    public void createFile() {
        try {
            
            File file = new File(path);
            file.getParentFile().mkdirs();
            file.createNewFile();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Task> loadData() {
        String savedTask;
        ArrayList<Task> tasks = new ArrayList<>();
        
        try {
            File existingData = new File(path);
            Scanner readExistingData = new Scanner(existingData);
            
            while (readExistingData.hasNextLine()) {
                savedTask = readExistingData.nextLine();
                String[] taskParts = savedTask.split("\\s\\|\\s");

                if (taskParts[0].contains("T")) {
                    
                    Task newToDoTask = new ToDo(taskParts[2].trim());
                    if (taskParts[1].contains("1")) {
                        newToDoTask.markAsDone();
                    }
                    tasks.add(newToDoTask);
                    
                    
                } else if (taskParts[0].contains("D")) {
                    
                    Task newDeadlineTask = new Deadline(taskParts[2], taskParts[3]);
                    if (taskParts[1].contains("1")) {
                        newDeadlineTask.markAsDone();
                    }
                    tasks.add(newDeadlineTask);
                    
                } else if (taskParts[0].contains("E")) {
                    
                    Task newEventTask = new Event(taskParts[2], taskParts[3]);
                    if (taskParts[1].contains("1")) {
                        newEventTask.markAsDone();
                    }
                    tasks.add(newEventTask);
                    
                }
                
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return tasks;
        
    }
    
    public void writeToFile(ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            for (int i = 0; i < tasks.size(); i++) {
                String task = tasks.get(i).toStringInFile() + "\n";
                fileWriter.write(task);
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
