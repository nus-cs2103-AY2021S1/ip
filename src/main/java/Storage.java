import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public List<String> load() throws DukeException {
        try {

            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            String task;
            List<String> tasks = new ArrayList<>();
            while ((task = br.readLine()) != null) {
                tasks.add(task.strip());
            }
            br.close();
            fr.close();

            return tasks;

        } catch (IOException e) {
            throw new DukeException("OOPS! I'm sorry, there is an error during initialisation :-(");
        }
    }

    public void newStorage() throws DukeException {
        new File("data/").mkdirs();
        try {
            new File(filepath).createNewFile();
        } catch (IOException ex) {
            throw new DukeException("OOPS! I'm sorry, there is an error during initialisation :-(");
        }
    }

    public void saveTaskListToFile(TaskList taskList) {
        try {
            PrintWriter writer = initialiseWriter();
            String allTasks = taskList.getTaskListForSave();
            writer.print(allTasks);
            writer.close();
        } catch (DukeException e) {
            Ui.printError(e.getMessage());
        }
    }

    public static PrintWriter initialiseWriter() throws DukeException {
        try {
            PrintWriter writer = new PrintWriter("data/TaskList.txt");
            return writer;
        } catch (IOException e) {
            throw new DukeException("OOPS! I'm sorry, there is an error during initialisation :-(");
        }
    }
}
