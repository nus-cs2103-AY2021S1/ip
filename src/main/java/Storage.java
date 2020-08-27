import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    
    Storage(String filePath) {
        this.filePath = filePath;
    }
    
    public ArrayList<Task> load() throws CorruptedFileException, UnableToCreateSaveFileException, NoSavedFileException {
        try {
            ArrayList<Task> savedTaskList = new ArrayList<>();
            String folderPath = getFolderPath();
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdir();
            }
            File saveFile = new File(filePath);
            Scanner fileReader = new Scanner(saveFile);
            while (fileReader.hasNextLine()) {
                String taskData = fileReader.nextLine();
                Task currTask = TaskGenerator.generateTask(taskData);
                savedTaskList.add(currTask);
            }
            return savedTaskList;
        } catch (FileNotFoundException e) {
            File saveFile = new File(filePath);
            try {
                saveFile.createNewFile();
            } catch (IOException ex) {
                throw new UnableToCreateSaveFileException();
            }
            throw new NoSavedFileException();
        }
    }
    
    public void save(TaskList tasks) {
        try {
            File saveFile = new File(filePath);
            File folder = new File(getFolderPath());
            if (!folder.exists()) {
                folder.mkdir();
            }
            if (!saveFile.exists()) {
                saveFile.createNewFile();
            }
            BufferedWriter bfWriter = new BufferedWriter(new FileWriter(saveFile));
            
            int numOfTasks = tasks.size();
            
            for (int i = 1; i <= numOfTasks; i++) {
                Task task = tasks.getTask(i);
                bfWriter.write(task.generateSaveFileData());
                bfWriter.newLine();
            }
            bfWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private String getFolderPath() {
        int indexOfLastSlash = 0;
        for (int i = filePath.length() - 1; i >= 0; i--) {
            if (filePath.charAt(i) == '/') {
                indexOfLastSlash = i;
                break;
            }
        }
        return filePath.substring(0, indexOfLastSlash);
    }
}
