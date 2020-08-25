package tasks;

import exceptions.DukeIOException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class to perform reading and writing operations on the task list itself.
 */
public class TaskIOParser {
    private final File saveFile;
    private static final String SAVEFILE = "save.txt";
    TaskIOParser(String path){
        Path taskFile = Paths.get(path,"src","save");
        this.saveFile = new File(taskFile.toString());
    }

    /**
     * Load tasks from a text file into memory
     * @return A read text file of tasks to Tasklist
     * @throws DukeIOException
     */
    public List<Task> loadTaskList() throws DukeIOException {
        List<Task> tasks = new ArrayList<>();
        try{
            Scanner sc = new Scanner(this.saveFile.toPath().resolve(SAVEFILE));
            String currentLine = "";
            String[] spl;
            while (sc.hasNext()) {
                currentLine = sc.nextLine();
                spl = currentLine.split(Task.SEP);
                switch (spl[0]){
                    case "T":
                        tasks.add(new ToDo(spl[1]));
                        break;
                    case "D":
                        tasks.add(new Deadline(spl[1],spl[2]));
                        break;
                    case "E":
                        tasks.add(new Event(spl[1],spl[2]));
                        break;
                }
            }
            return tasks;
        }catch (IOException fileException ){
            throw new DukeIOException("Oops we couldnt read any file," +
                    " hence we will start from a new save file");
        }
        
    }

    /**
     * For initialising new TaskList
     * @return returns a new List<Task>
     */
    public List<Task> loadNewTaskList(){
        return new ArrayList<>();
    }
    public void writeTask(List<Task> taskList) throws DukeIOException{
        if (!saveFile.exists()){
            try {
                Files.createDirectory(Path.of(saveFile.getPath()));
                Files.createFile(Path.of(saveFile.getPath()).resolve(SAVEFILE));
            }catch (IOException e){
                throw new DukeIOException("Could not save the file due to directory not created");
            }
        }
        
        try{
            FileWriter fw = new FileWriter(Path.of(saveFile.getPath()).resolve(SAVEFILE).toFile());
            String linesep = System.lineSeparator(); 
            for (Task t : taskList){
                System.out.println(t);
                fw.write(t.saveTask());
                fw.write(linesep);
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeIOException(e.toString());
        }

    }
}
