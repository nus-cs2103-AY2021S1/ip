package clippy.storage;

import clippy.exception.CorruptedFileException;
import clippy.exception.NoSavedFileException;
import clippy.exception.UnableToCreateSaveFileException;

import clippy.task.Task;
import clippy.task.TaskGenerator;
import clippy.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents an object that deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a storage object with a specified file path to the save file of the program.
     * 
     * @param filePath file path to the save file of the program.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks stored in the save file at the specified file path if file exists and is not corrupted.
     * 
     * @return ArrayList of Tasks retrieved from the save file.
     * @throws CorruptedFileException If format of data in save file is corrupted.
     * @throws UnableToCreateSaveFileException If program is unable to create save file due to IO errors.
     * @throws NoSavedFileException If the save file does not exist.
     */
    public ArrayList<Task> load() throws CorruptedFileException, UnableToCreateSaveFileException, 
            NoSavedFileException {
        try {
            ArrayList<Task> savedTaskList = new ArrayList<>();
            
            String folderPath = getFolderPath();
            assert !folderPath.isBlank() : "Folder path is blank";
            
            File folder = new File(folderPath);
            
            if (!folder.exists()) {
                folder.mkdir();
            }
            assert folder.exists() : "Folder is not created before attempting to save a file in it";
            
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

    /**
     * Saves the tasks in the TaskList in the current Clippy session onto the save file by overwriting the save file.
     * 
     * @param tasks TaskList in the current Clippy session.
     */
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
            
            int numOfTasks = tasks.getSize();
            
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
