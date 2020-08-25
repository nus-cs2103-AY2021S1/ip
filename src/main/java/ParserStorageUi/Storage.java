package ParserStorageUi;
import Exceptions.DukeException;
import Task.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;

public class Storage {

    /** The file path of the folder of duke.txt **/
    private final String filePath;

    /** The object file path for duke.txt **/
    private Path file;

    /**
     * Initializes Storage with filePath as the parameter
     * @param filePath
     */
    public Storage(String filePath){
        this.filePath = filePath;
        initiateFile();
    }

    /** Initiate the file **/
    private void initiateFile(){
        try{
            if (Files.notExists(Paths.get(filePath))){
                Files.createDirectory(Paths.get(filePath));
            }
            if (Files.notExists(Paths.get(filePath + "/duke.txt"))) {
                file = Files.createFile(Paths.get(filePath + "/duke.txt"));
            } else {
                file = Paths.get(filePath + "/duke.txt") ;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /** Load all the tasks from the save file to ArrayList<Task> **/
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> temp = new ArrayList<>();
        try {
            for (String task : Files.readAllLines(file)) {
                String type = task.substring(0, 1);
                String name = task.substring(8);
                int isDone = Integer.parseInt(task.substring(4, 5));
                boolean isTaskDone = isDone == 1;
                switch (type) {
                    case "T":
                        temp.add(new Todo(name, isTaskDone));
                        break;
                    case "D":
                        int indexOfLine = name.indexOf("|");
                        temp.add(new Deadline(name.substring(0, indexOfLine - 1), isTaskDone, name.substring(indexOfLine + 2)));
                        break;
                    case "E":
                        int iOL = name.indexOf("|");
                        temp.add(new Event(name.substring(0, iOL  - 1), isTaskDone, name.substring(iOL + 2)));
                        break;
                }
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        return temp;
    }

    /** Put back all tasks from ArrayList to the save file (duke.txt) **/
    public void putToDatabase(ArrayList<Task> tasks) throws DukeException {
        try {

            FileWriter fw = new FileWriter(this.filePath + "/duke.txt");
            PrintWriter pw = new PrintWriter(fw);
            for (Task task : tasks) {
                if (task instanceof Todo) {
                    pw.println(task.getType() + " | " + task.isDone() + " | " + task.getName());
                } else {
                    pw.println(task.getType() + " | " + task.isDone() + " | " + task.getName() + " | " + task.getEnd());
                }
            }
            pw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
