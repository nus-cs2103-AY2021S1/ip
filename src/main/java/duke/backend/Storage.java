package duke.backend;

import duke.task.Task;
import duke.task.TaskList;

import java.io.*;
import java.util.List;

public class Storage {
    private final File file;
    private final String filePath;
    
    public Storage(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            createFile(file);
        }
        this.filePath = filePath;
        this.file = file;
    }
    
    public void createFile(File file){
        try {
            String dir = file.getParent();
            File dirFile = new File(dir);
            dirFile.mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public BufferedReader load() throws FileNotFoundException {
        FileReader f = new FileReader(this.file);
        return new BufferedReader(f);
    }
    
    public void save(TaskList taskList) {
        try {
            boolean deleted = file.delete();
            if (deleted) createFile(file);
            FileWriter fw = new FileWriter(file);
            List<Task> tl = taskList.getTaskList();
            for (Task t : tl) {
                fw.write(t.getParsedTask());
            }   
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e);
        }
    }
    
}