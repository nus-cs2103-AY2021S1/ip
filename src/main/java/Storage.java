import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() throws DukeException {
        File directory = new File("data/");
        if (!directory.exists()){
            directory.mkdir();
        }
        File f = new File(this.filePath);
        if (f.exists()) {
            try {
                TaskList taskList = new TaskList();
                Scanner s = new Scanner(f); // create a Scanner using the File as the source
                while (s.hasNext()) {
                    String taskString = s.nextLine();
                    if (!taskString.isEmpty()) {
                        Parser.addTaskFromFile(taskString, taskList);
                    } else {
                        continue;
                    }
                }
                return taskList;
            } catch (FileNotFoundException fnfe) {
                throw new DukeException(fnfe.getMessage());
            }
        } else { //file f doesn't exist
            try {
                f.createNewFile();
            } catch (IOException e) {
                throw new DukeException(e.getMessage());
            }
            TaskList taskList = new TaskList();
            return taskList;
        }
    }

    public void write(TaskList listToAdd) throws DukeException {
        try {
            FileWriter resetfw = new FileWriter(this.filePath);
            resetfw.write("");
            resetfw.close();

            FileWriter fw = new FileWriter(this.filePath, true);
            for (int i = 0; i < listToAdd.size(); i++) {
                String task = listToAdd.get(i).toString();
                task = task.replace("(", "/");
                task = task.replace(")", "");
                task = task.replace(":", "");
                fw.write("\n" + task);
            }
            fw.close();
        } catch (IOException ioe) {
            throw new DukeException(ioe.getMessage());
        }
    }
}
